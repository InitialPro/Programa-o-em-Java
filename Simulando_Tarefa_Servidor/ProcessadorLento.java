package Simulando_Tarefa_Servidor;

public class ProcessadorLento implements Runnable {
    private Tarefa tarefa;

    public ProcessadorLento(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    @Override
    public void run() {
        System.out.println("-> Iniciando Processador Lento para a Tarefa " + tarefa.getId());
        try {
            tarefa.executar();
        } catch (TarefaInvalidaException e) {
            System.err.println("ERRO (Processador Lento): " + e.getMessage());
        }
    }
}