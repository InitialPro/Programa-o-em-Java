package CacaAoTesouro;

import java.util.concurrent.Semaphore;

/**
 * Classe base abstrata que define os comportamentos comuns a todos os exploradores.
 */
public abstract class Explorador {
    protected String nome;
    protected String tipo;
    protected int prioridade;
    
    protected Tarefa tarefa;
    protected Semaphore semaforo;
    protected Sinalizador sinalizador;

    /**
     * Construtor base para instanciar um explorador com todos os seus recursos.
     */
    public Explorador(String nome, String tipo, int prioridade, Tarefa tarefa, Semaphore semaforo, Sinalizador sinalizador) {
        this.nome = nome;
        this.tipo = tipo;
        this.prioridade = prioridade;
        this.tarefa = tarefa;
        this.semaforo = semaforo;
        this.sinalizador = sinalizador;
    }

    /**
     * Retorna a prioridade que esta thread terá no sistema.
     */
    public int getPrioridade() {
        return prioridade;
    }

    /**
     * Método abstrato que força cada tipo específico de explorador a implementar
     * a sua própria maneira de executar a tarefa.
     */
    public abstract void executarTarefa() throws TarefaInvalidaException;
    
    /**
     * Valida a tarefa sem usar estruturas de decisão (if/else), conforme regra do Nível Novato.
     * Utiliza recursos nativos do Java que lançam exceções automaticamente se os dados forem inválidos.
     * 
     * @throws TarefaInvalidaException Se a tarefa for nula ou sua descrição for vazia.
     */
    protected void validarTarefa() throws TarefaInvalidaException {
        try {
            // Lança NullPointerException se a 'tarefa' ou a 'descricao' forem nulas
            java.util.Objects.requireNonNull(tarefa);
            java.util.Objects.requireNonNull(tarefa.getDescricao());
            
            // Lança StringIndexOutOfBoundsException se a string for vazia ("")
            tarefa.getDescricao().charAt(0); 
            
        } catch (NullPointerException | StringIndexOutOfBoundsException e) {
            // Captura as exceções nativas e as converte na nossa exceção temática de negócio
            throw new TarefaInvalidaException("Tarefa inválida para " + nome);
        }
    }
}