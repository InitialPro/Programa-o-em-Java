package CacaAoTesouro;

import java.util.concurrent.Semaphore;

/**
 * Subclasse de Explorador focada em velocidade.
 * Implementa Runnable para ser executada como uma Thread paralela.
 */
public class ExploradorRapido extends Explorador implements Runnable {

    /**
     * Construtor que repassa os parâmetros para a classe pai (super).
     */
    public ExploradorRapido(String nome, int prioridade, Tarefa tarefa, Semaphore semaforo, Sinalizador sinalizador) {
        super(nome, "Rápido", prioridade, tarefa, semaforo, sinalizador);
    }

    /**
     * Implementação específica da execução da tarefa para um explorador rápido.
     */
    @Override
    public void executarTarefa() {
        System.out.println(" -> Explorador: " + nome + " | Tipo: " + tipo + " | Status: Vasculhando " + tarefa.getDescricao() + " rapidamente!");
    }

    /**
     * Método principal da Thread. Contém o ciclo de vida e a coordenação de concorrência.
     */
    @Override
    public void run() {
        try {
            // 1. Aguarda a liberação do Monitor (Nível Aventureiro)
            sinalizador.aguardarSinal(nome); 
            
            // 2. Valida os dados antes de iniciar o trabalho bruto (Nível Novato)
            validarTarefa(); 

            System.out.println(nome + " aguardando espaço na trilha...");
            
            // 3. Tenta obter uma das permissões do Semáforo. Se estiver cheio, a thread aguarda aqui.
            semaforo.acquire(); 
            
            try {
                // 4. Executa o trabalho específico e simula o tempo gasto (1 segundo)
                executarTarefa(); 
                Thread.sleep(1000); 
                System.out.println(" <- " + nome + " concluiu e abriu espaço na trilha.");
            } finally {
                // 5. LIBERAÇÃO OBRIGATÓRIA: Ocorre no 'finally' para garantir que,
                // mesmo se der erro no try, a vaga no semáforo será devolvida para os outros.
                semaforo.release(); 
            }

        } catch (TarefaInvalidaException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println(nome + " foi interrompido!");
            Thread.currentThread().interrupt(); // Restaura a flag de interrupção da thread
        }
    }
}