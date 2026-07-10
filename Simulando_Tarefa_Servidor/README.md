Para consolidar exceções e processamento paralelo em Java, desenvolva um sistema que simule tarefas em um servidor. Para tal, siga o passo a passo:

 

Crie a classe Tarefa com atributos id e descricao.
 
Implemente o método executar(), que imprime a descrição, aguarda 1 segundo e lança TarefaInvalidaException se a descrição for nula ou vazia.
 
Crie a exceção TarefaInvalidaException estendendo Exception.
 
Crie duas classes Runnable: 
 
ProcessadorRapido (prioridade máxima) 
 
ProcessadorLento (prioridade mínima) 
 

Cada uma recebe uma tarefa e tratando exceções.

 

No main, crie pelo menos quatro tarefas, configure prioridades e daemon, inicialize as threads e imprima seus estados.

 

Objetivo: praticar threads, prioridades, ciclo de vida e exceções personalizadas.