package CacaAoTesouro;

import java.util.concurrent.Semaphore;

/**
 * Subclasse de Explorador focada em cautela e segurança.
 * Demora mais tempo para concluir as tarefas.
 */
public class ExploradorCuidadoso extends Explorador implements Runnable {

    public ExploradorCuidadoso(String nome, int prioridade, Tarefa tarefa, Semaphore semaforo, Sinalizador sinalizador) {
        super(nome, "Cuidadoso", prioridade, tarefa, semaforo, sinalizador);
    }

    /**
     * Implementação específica para o explorador cuidadoso, com mensagem temática.
     */
    @Override
    public void executarTarefa() {
        System.out.println(" -> Explorador: " + nome + " | Tipo: " + tipo + " | Status: Mapeando " + tarefa.getDescricao() + " com extrema cautela.");
    }

    /**
     * Método principal da Thread, seguindo o mesmo fluxo seguro de concorrência.
     */
    @Override
    public void run() {
        try {
            sinalizador.aguardarSinal(nome); // Bloqueia até o NotifyAll() do líder
            validarTarefa(); // Validação sem if/else

            System.out.println(nome + " aguardando espaço na trilha...");
            semaforo.acquire(); // Obtém permissão do semáforo
            
            try {
                executarTarefa();
                Thread.sleep(3000); // O cuidadoso demora mais: 3 segundos
                System.out.println(" <- " + nome + " concluiu em segurança e abriu espaço na trilha.");
            } finally {
                semaforo.release(); // Devolve a permissão sempre
            }

        } catch (TarefaInvalidaException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println(nome + " foi interrompido!");
            Thread.currentThread().interrupt();
        }
    }
}