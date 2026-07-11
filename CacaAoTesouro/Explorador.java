package CacaAoTesouro;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

/**
 * Classe base que define um explorador. Implementa Callable para poder 
 * retornar um valor (Double) e lançar exceções diretamente para o ExecutorService.
 */
public abstract class Explorador implements Callable<Double> {
    protected String nome;
    protected String especialidade;
    
    protected Missao missao;
    protected Semaphore semaforo;
    protected Sinalizador sinalizador;

    /**
     * Construtor base para instanciar o explorador com suas dependências de concorrência.
     * 
     * @param nome Nome do explorador.
     * @param especialidade O tipo de explorador (Rastreador, Saqueador, etc).
     * @param missao A missão imutável designada a ele.
     * @param semaforo O controle de limite de pessoas na trilha.
     * @param sinalizador O monitor de largada.
     */
    public Explorador(String nome, String especialidade, Missao missao, Semaphore semaforo, Sinalizador sinalizador) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.missao = missao;
        this.semaforo = semaforo;
        this.sinalizador = sinalizador;
    }

    /**
     * Valida os dados da missão utilizando recursos nativos da API do Java para evitar 
     * o uso de estruturas if/else, forçando exceções quando os dados são inválidos.
     * 
     * @throws TarefaInvalidaException Se a missão for nula ou a descrição for vazia.
     */
    protected void validarMissao() throws TarefaInvalidaException {
        try {
            java.util.Objects.requireNonNull(missao);
            java.util.Objects.requireNonNull(missao.getDescricao());
            missao.getDescricao().charAt(0); // Tenta ler a primeira letra para validar se não está vazio
        } catch (NullPointerException | StringIndexOutOfBoundsException e) {
            throw new TarefaInvalidaException("Missão corrompida designada para: " + nome);
        }
    }

    /**
     * Método abstrato onde cada subclasse definirá sua própria fórmula matemática 
     * e tempo de simulação para gerar os pontos.
     * 
     * @return Os pontos obtidos na missão.
     * @throws InterruptedException Se a thread for interrompida durante o Thread.sleep().
     */
    protected abstract Double calcularPontos() throws InterruptedException;

    /**
     * Método principal executado pelo ExecutorService. 
     * Orquestra a espera do sinal, a validação, a disputa pelo semáforo e a execução.
     * 
     * @return O total de pontos calculados pela subclasse.
     * @throws Exception Caso ocorra erro de validação ou interrupção.
     */
    @Override
    public Double call() throws Exception { 
        // 1. Aguarda a liberação do líder (Monitor)
        sinalizador.aguardarSinal(nome); 
        
        // 2. Valida os dados ANTES de tentar ocupar a trilha
        validarMissao(); 
        
        System.out.println(nome + " na trilha aguardando permissão de exploração...");
        
        // 3. Tenta pegar uma das permissões do semáforo. Bloqueia se estiver cheio.
        semaforo.acquire(); 
        
        try {
            // 4. Executa a lógica de cálculo definida na subclasse
            return calcularPontos();
        } finally {
            // 5. Garante que a permissão seja devolvida, mesmo se houver erro
            semaforo.release(); 
        }
    }
}