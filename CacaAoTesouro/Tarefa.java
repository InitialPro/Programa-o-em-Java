package CacaAoTesouro;

/**
 * Classe que representa a tarefa a ser executada na ilha.
 * É marcada como 'final' para ser imutável, garantindo que seja "Thread-Safe".
 * Uma vez criada, nenhuma thread consegue alterar seus valores.
 */
public final class Tarefa {
    private final String id;
    private final String descricao;

    /**
     * Construtor para inicializar a tarefa imutável.
     * @param id Identificador único da tarefa.
     * @param descricao O que o explorador precisa fazer.
     */
    public Tarefa(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    /**
     * Retorna o ID da tarefa.
     */
    public String getId() {
        return id;
    }

    /**
     * Retorna a descrição da tarefa.
     */
    public String getDescricao() {
        return descricao;
    }
}