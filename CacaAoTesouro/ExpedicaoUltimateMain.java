package CacaAoTesouro;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Tarefa recursiva utilizada pelo ForkJoinPool para somar os pontos utilizando a 
 * estratégia de Divisão e Conquista (Work-Stealing Algorithm).
 */
class SomaPontos extends RecursiveTask<Double> {
    private final double[] pontos;
    private final int inicio;
    private final int fim;
    private static final int LIMITE = 2; // Tamanho máximo da porção do array que será somada diretamente

    /**
     * Construtor da tarefa de soma.
     * 
     * @param pontos Array contendo as pontuações coletadas.
     * @param inicio Índice inicial da porção do array a ser processada.
     * @param fim Índice final da porção do array a ser processada.
     */
    public SomaPontos(double[] pontos, int inicio, int fim) {
        this.pontos = pontos;
        this.inicio = inicio;
        this.fim = fim;
    }

    /**
     * Executa a lógica de divisão e conquista. 
     * Se o pedaço do array for pequeno, soma. Se for grande, divide no meio.
     * 
     * @return A soma da porção delegada a esta tarefa.
     */
    @Override
    protected Double compute() {
        if ((fim - inicio) <= LIMITE) {
            // Caso base: array pequeno, faz a soma sequencial
            double soma = 0;
            for (int i = inicio; i < fim; i++) { 
                soma += pontos[i]; 
            }
            return soma;
        } else {
            // Caso recursivo: divide o trabalho em duas novas subtarefas
            int meio = (inicio + fim) / 2;
            SomaPontos metadeEsquerda = new SomaPontos(pontos, inicio, meio);
            SomaPontos metadeDireita = new SomaPontos(pontos, meio, fim);
            
            // Submete a metade esquerda assincronamente para outra thread do pool
            metadeEsquerda.fork(); 
            
            // Computa a metade direita na thread atual e junta (join) com o resultado da esquerda
            return metadeDireita.compute() + metadeEsquerda.join();
        }
    }
}

/**
 * Classe principal que orquestra todo o sistema.
 */
public class ExpedicaoUltimateMain {
    
    /**
     * Método de entrada do programa. Configura os recursos, despacha as threads 
     * para o ExecutorService e, ao final, consolida os pontos no ForkJoinPool.
     */
    public static void main(String[] args) {
        System.out.println("=== EXPEDIÇÃO ULTIMATE: Concorrência e Paralelismo em Java ===\n");

        // --- 1. PREPARAÇÃO DOS MONITORES ---
        Semaphore semaforo = new Semaphore(2, true); // Semáforo restrito a 2 vagas simultâneas, fila justa (true)
        Sinalizador sinalizador = new Sinalizador();

        // --- 2. PREPARAÇÃO DAS MISSÕES ---
        Missao m1 = new Missao("M1", "Caverna do Dragão", 3);
        Missao m2 = new Missao("M2", "Templo Perdido", 2);
        Missao m3 = new Missao("M3", "Floresta Negra", 1);
        Missao m4 = new Missao("M4", "", 3); // Missão propositalmente inválida para testar exceções

        // --- 3. PREPARAÇÃO DAS EQUIPES ---
        Rastreador alice = new Rastreador("Alice", m1, semaforo, sinalizador);
        Saqueador bob = new Saqueador("Bob", m2, semaforo, sinalizador);
        Rastreador charlie = new Rastreador("Charlie", m3, semaforo, sinalizador);
        Saqueador clara = new Saqueador("Clara", m4, semaforo, sinalizador);

        // --- 4. PREPARAÇÃO DO EXECUTOR SERVICE ---
        // Cria um pool fixo de 4 threads para evitar recriar threads do zero na memória
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<Double>> futures = new ArrayList<>();

        System.out.println(">> Submetendo missões ao Pool de Threads...");
        
        // Submete os objetos Callable ao pool, que retorna um objeto Future como "promessa" do resultado
        futures.add(executor.submit(alice));
        futures.add(executor.submit(bob));
        futures.add(executor.submit(charlie));
        futures.add(executor.submit(clara));

        // --- 5. LARGADA DA EXPEDIÇÃO ---
        try {
            Thread.sleep(1500); // Aguarda um momento simulando a arrumação dos equipamentos
            sinalizador.darSinal(); // Desperta todas as threads presas no Monitor
        } catch (InterruptedException e) { 
            e.printStackTrace(); 
        }

        // --- 6. COLETA E TRATAMENTO DOS RESULTADOS ---
        double[] arrayPontos = new double[4];
        
        for (int i = 0; i < futures.size(); i++) {
            try {
                // future.get() bloqueia a execução da Main até que a thread específica termine e retorne o Double
                arrayPontos[i] = futures.get(i).get(); 
            } catch (ExecutionException e) {
                // Captura a nossa TarefaInvalidaException que foi encapsulada pelo Callable
                System.err.println("\n[ALERTA DE SISTEMA] Falha na missão: " + e.getCause().getMessage());
                arrayPontos[i] = 0.0; // Punição: Explorador que falha ganha 0 pontos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // Regra vital: Desligar o ExecutorService para liberar os recursos e permitir que o programa termine
        executor.shutdown();

        // --- 7. CONSOLIDAÇÃO COM FORK/JOIN ---
        System.out.println("\n>> Contabilidade Final com Fork/Join...");
        
        // Instanciação clássica sem o try()
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SomaPontos tarefaSoma = new SomaPontos(arrayPontos, 0, arrayPontos.length);
        
        // invoke() submete a tarefa raiz ao pool e aguarda o resultado consolidado
        Double pontuacaoTotal = forkJoinPool.invoke(tarefaSoma);
        
        System.out.println("SOMA TOTAL DA EQUIPE: " + pontuacaoTotal + " pontos.");
        
        // Desliga o pool manualmente
        forkJoinPool.shutdown();

    }
}