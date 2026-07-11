package Sistema_Relatorio_Financeiro;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Tarefa responsável por processar um único relatório de forma concorrente.
 * Retorna o valor do relatório processado ao final da execução.
 */
public class ProcessadorRelatorio implements Callable<Double> {
    private final Relatorio relatorio;

    public ProcessadorRelatorio(Relatorio relatorio) {
        this.relatorio = relatorio;
    }

    /**
     * Método que executa o processamento do relatório.
     * Simula uma carga de trabalho pesada usando Thread.sleep.
     * 
     * @return O valor financeiro do relatório processado.
     */
    @Override
    public Double call() throws Exception {
        String threadName = Thread.currentThread().getName();
        System.out.println("[" + threadName + "] Iniciando processamento do " + relatorio);
        
        // Simula o tempo de processamento (ex: acesso a banco, cálculos complexos)
        TimeUnit.MILLISECONDS.sleep(1500); 
        
        System.out.println("[" + threadName + "] Finalizado " + relatorio.getDescricao());
        return relatorio.getValor();
    }
}