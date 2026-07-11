package SistemaRelatorioFinanceiro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        // 1. Criando a lista de relatórios
        List<Relatorio> listaRelatorios = Arrays.asList(
            new Relatorio(1, "Faturamento Q1", 15000.50),
            new Relatorio(2, "Despesas TI", 4200.00),
            new Relatorio(3, "Folha de Pagamento", 25300.75),
            new Relatorio(4, "Logística e Transportes", 8900.20),
            new Relatorio(5, "Marketing Digital", 3100.00)
        );

        System.out.println("--- PARTE 1: ExecutorService com Callable & Future ---");
        
        // Criando um pool de threads fixo (3 threads trabalhando simultaneamente)
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Double>> resultadosFutures = new ArrayList<>();

        // Submetendo as tarefas para o executor
        for (Relatorio r : listaRelatorios) {
            ProcessadorRelatorio tarefa = new ProcessadorRelatorio(r);
            Future<Double> future = executor.submit(tarefa);
            resultadosFutures.add(future);
        }

        // Coletando os resultados (o .get() bloqueia a thread principal até a tarefa terminar)
        double totalProcessadoExecutor = 0;
        try {
            for (int i = 0; i < resultadosFutures.size(); i++) {
                double valor = resultadosFutures.get(i).get();
                System.out.printf("Resultado Future recebido para Relatório %d: R$ %.2f%n", (i + 1), valor);
                totalProcessadoExecutor += valor;
            }
            System.out.printf("Total processado pelo ExecutorService: R$ %.2f%n%n", totalProcessadoExecutor);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Erro ao processar relatórios: " + e.getMessage());
        } finally {
            // Sempre desligue o executor para liberar os recursos do sistema
            executor.shutdown();
        }

        System.out.println("--- PARTE 2: Fork/Join Pool (Soma em paralelo) ---");
        
        // Criando o ForkJoinPool e chamando a nossa tarefa recursiva
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SomaRelatorios tarefaSomaTotal = new SomaRelatorios(listaRelatorios, 0, listaRelatorios.size());
        
        double totalSomaParalela = forkJoinPool.invoke(tarefaSomaTotal);
        System.out.printf("Resultado final da soma paralela (Fork/Join): R$ %.2f%n%n", totalSomaParalela);


        System.out.println("--- PARTE 3: ScheduledExecutorService ---");
        
        // Criando um agendador com 1 thread
        ScheduledExecutorService agendador = Executors.newScheduledThreadPool(1);
        
        // Agenda uma tarefa de auditoria para rodar a cada 2 segundos, após um atraso inicial de 1 segundo
        System.out.println("Iniciando serviço de auditoria agendada...");
        ScheduledFuture<?> tarefaAgendada = agendador.scheduleAtFixedRate(() -> {
            System.out.println("[Auditoria] Verificando integridade dos relatórios processados...");
        }, 1, 2, TimeUnit.SECONDS);

        // Para não deixar o programa rodando para sempre no console, vamos parar o agendador após 7 segundos
        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\nEncerrando o sistema...");
        tarefaAgendada.cancel(true);
        agendador.shutdown();
    }
}