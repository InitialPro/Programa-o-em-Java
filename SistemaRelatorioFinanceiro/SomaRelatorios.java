package SistemaRelatorioFinanceiro;

import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Tarefa Fork/Join que divide a lista de relatórios recursivamente 
 * para somar seus valores em paralelo.
 */
public class SomaRelatorios extends RecursiveTask<Double> {
    // Limite de relatórios para processar diretamente sem dividir a tarefa
    private static final int LIMITE = 2; 
    private final List<Relatorio> relatorios;
    private final int inicio;
    private final int fim;

    public SomaRelatorios(List<Relatorio> relatorios, int inicio, int fim) {
        this.relatorios = relatorios;
        this.inicio = inicio;
        this.fim = fim;
    }

    /**
     * Método principal do Fork/Join. Decide se calcula o resultado diretamente
     * ou se divide a tarefa em duas subtarefas menores.
     */
    @Override
    protected Double compute() {
        int quantidade = fim - inicio;

        // Caso base: se a quantidade for menor ou igual ao limite, processa sequencialmente
        if (quantidade <= LIMITE) {
            double soma = 0;
            for (int i = inicio; i < fim; i++) {
                soma += relatorios.get(i).getValor();
            }
            return soma;
        }

        // Caso recursivo: divide o problema ao meio (Fork)
        int meio = inicio + (quantidade / 2);
        
        SomaRelatorios tarefaEsquerda = new SomaRelatorios(relatorios, inicio, meio);
        SomaRelatorios tarefaDireita = new SomaRelatorios(relatorios, meio, fim);

        // Dispara a primeira subtarefa de forma assíncrona
        tarefaEsquerda.fork();
        
        // Executa a segunda subtarefa na thread atual e aguarda a primeira (Join)
        double resultadoDireita = tarefaDireita.compute();
        double resultadoEsquerda = tarefaEsquerda.join();

        // Combina os resultados
        return resultadoEsquerda + resultadoDireita;
    }
}