package CacaAoTesouro;

public class ExploradorRapido extends Explorador implements Runnable {

    public ExploradorRapido(String nome, int prioridade, String tarefa) {
        super(nome, "Rápido", prioridade, tarefa);
    }

    @Override
    public void executarTarefa() throws TarefaInvalidaException {
        validarTarefa(); // Lança exceção se for nula ou vazia
        System.out.println("Explorador: " + nome + " | Tipo: " + tipo + " | Status: Vasculhando " + tarefa + " rapidamente!");
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