package Simulando_Tarefa_Servidor;

public class ProcessadorRapido implements Runnable {
    private Tarefa tarefa;

    public ProcessadorRapido(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    @Override
    public void run() {
        System.out.println("-> Iniciando Processador Rápido para a Tarefa " + tarefa.getId());
        try {
            tarefa.executar();
        } catch (TarefaInvalidaException e) {
            System.err.println("ERRO (Processador Rápido): " + e.getMessage());
        }
    }
}