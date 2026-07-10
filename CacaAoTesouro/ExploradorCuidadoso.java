package CacaAoTesouro;

public class ExploradorCuidadoso extends Explorador implements Runnable {

    public ExploradorCuidadoso(String nome, int prioridade, String tarefa) {
        super(nome, "Cuidadoso", prioridade, tarefa);
    }

    @Override
    public void executarTarefa() throws TarefaInvalidaException {
        validarTarefa(); // Lança exceção se for nula ou vazia
        System.out.println("Explorador: " + nome + " | Tipo: " + tipo + " | Status: Mapeando " + tarefa + " com cautela e atenção.");
    }

    @Override
    public void run() {
        try {
            executarTarefa();
        } catch (TarefaInvalidaException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}