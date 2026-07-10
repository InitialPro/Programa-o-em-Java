package CacaAoTesouro;

public abstract class Explorador {
    protected String nome;
    protected String tipo;
    protected int prioridade;
    protected String tarefa;

    public Explorador(String nome, String tipo, int prioridade, String tarefa) {
        this.nome = nome;
        this.tipo = tipo;
        this.prioridade = prioridade;
        this.tarefa = tarefa;
    }

    public String getNome() {
        return nome;
    }

    public int getPrioridade() {
        return prioridade;
    }

    // Método que as subclasses deverão implementar
    public abstract void executarTarefa() throws TarefaInvalidaException;
    
    // Método auxiliar que valida sem usar o "if" tradicional (respeitando a regra do desafio)
    protected void validarTarefa() throws TarefaInvalidaException {
        try {
            // Se for nulo, lança NullPointerException automaticamente
            java.util.Objects.requireNonNull(tarefa);
            // Se estiver vazia, forçamos um erro pegando um caractere que não existe
            tarefa.charAt(0); 
        } catch (NullPointerException | StringIndexOutOfBoundsException e) {
            throw new TarefaInvalidaException("Tarefa inválida para " + nome);
        }
    }
}