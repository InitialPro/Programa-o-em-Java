package CacaAoTesouro;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Classe principal do sistema que inicializa a simulação, orquestra as dependências
 * e dispara as threads em paralelo.
 */
public class CacaAoTesouroIntegradaMain {
    
    /**
     * Ponto de entrada do programa.
     */
    public static void main(String[] args) {
        System.out.println("=== Expedição Integrada: Novato + Aventureiro ===\n");

        // --- 1. INSTANCIAÇÃO DE RECURSOS COMPARTILHADOS ---
        // Semáforo com '2' vagas simultâneas na trilha.
        // O parâmetro 'true' ativa o modo "Fair" (Justo), mantendo uma fila por ordem de chegada.
        Semaphore semaforo = new Semaphore(2, true); 
        
        // Monitor que gerencia a largada
        Sinalizador sinalizador = new Sinalizador();

        // --- 2. CRIAÇÃO DAS TAREFAS (Imutáveis) ---
        Tarefa t1 = new Tarefa("T1", "a caverna escura");
        Tarefa t2 = new Tarefa("T2", "a floresta densa");
        Tarefa t3 = new Tarefa("T3", "as ruínas antigas");
        Tarefa t4 = new Tarefa("T4", ""); // Descrição vazia para forçar o erro na validação

        // --- 3. CRIAÇÃO DOS RUNNABLES (Exploradores) ---
        // Injetando as tarefas, prioridades, o semáforo e o monitor em cada um.
        ExploradorRapido alice = new ExploradorRapido("Alice", Thread.MAX_PRIORITY, t1, semaforo, sinalizador);
        ExploradorCuidadoso bob = new ExploradorCuidadoso("Bob", Thread.NORM_PRIORITY, t2, semaforo, sinalizador);
        ExploradorRapido charlie = new ExploradorRapido("Charlie", Thread.MIN_PRIORITY, t3, semaforo, sinalizador);
        ExploradorCuidadoso clara = new ExploradorCuidadoso("Clara", Thread.NORM_PRIORITY, t4, semaforo, sinalizador);

        // --- 4. PREPARAÇÃO DAS THREADS ---
        ArrayList<Thread> expedicao = new ArrayList<>();
        
        Thread tAlice = new Thread(alice); tAlice.setPriority(alice.getPrioridade());
        Thread tBob = new Thread(bob); tBob.setPriority(bob.getPrioridade());
        Thread tCharlie = new Thread(charlie); tCharlie.setPriority(charlie.getPrioridade());
        Thread tClara = new Thread(clara); tClara.setPriority(clara.getPrioridade());

        expedicao.add(tAlice);
        expedicao.add(tBob);
        expedicao.add(tCharlie);
        expedicao.add(tClara);

        // --- 5. DISPARO INICIAL (Start) ---
        // Todas as threads são iniciadas aqui, mas elas pararão na barreira do Sinalizador
        for (Thread t : expedicao) {
            t.start();
        }

        // --- 6. LIBERAÇÃO DA LARGADA (NotifyAll) ---
        try {
            // O líder (esta thread main) gasta 2 segundos arrumando o acampamento...
            Thread.sleep(2000);
            
            // ...e então dá o sinal verde para que todas as threads iniciem a disputa pelo Semáforo!
            sinalizador.darSinal(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}