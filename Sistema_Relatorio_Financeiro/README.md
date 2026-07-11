Vamos praticar? Para consolidar execução paralela com retorno de resultados e divisão de tarefas, desenvolva um sistema de processamento de relatórios financeiros. Para isso, siga este passo a passo.

1.    Crie a classe Relatorio (imutável) com id, descricao e valor, incluindo construtor e getters.

2.    Crie ProcessadorRelatorio implementando Callable<Double>, processando o relatório, simulando tempo de execução e retornando o valor.

3.    No main, crie uma lista de relatórios, um ExecutorService com thread pool, submeta os relatórios e use Future.get() para exibir resultados.
 

Passo adicional: crie SomaRelatorios com Fork/Join para somar valores em paralelo.
 

Objetivo: praticar ExecutorService, Callable/Future, ScheduledExecutorService e Fork/Join.