package Simulando_Tarefa_Servidor;

public class Tarefa {
    private int id;
    private String descricao;

    public Tarefa(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    // Método que imprime, aguarda e valida
    public void executar() throws TarefaInvalidaException {
        System.out.println("[Tarefa " + id + "] Descrição lida: " + descricao);
        
        try {
            // Aguarda 1 segundo
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("A tarefa " + id + " foi interrompida durante a espera.");
            Thread.currentThread().interrupt(); // Restaura o estado de interrupção
        }

        // Lança exceção se for nula ou vazia
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new TarefaInvalidaException("Falha na Tarefa " + id + ": A descrição é nula ou vazia.");
        }
        
        System.out.println("[Tarefa " + id + "] Concluída com sucesso!");
    }
}