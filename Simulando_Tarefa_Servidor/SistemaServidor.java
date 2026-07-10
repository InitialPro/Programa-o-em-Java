package Simulando_Tarefa_Servidor;

public class SistemaServidor {
    public static void main(String[] args) {
        System.out.println("=== Iniciando Sistema Servidor ===\n");

        // Criando as tarefas (a tarefa 3 tem descrição vazia para testar a exceção)
        Tarefa t1 = new Tarefa(1, "Atualizar banco de dados principal");
        Tarefa t2 = new Tarefa(2, "Sincronizar arquivos de log");
        Tarefa t3 = new Tarefa(3, ""); // Forçando o erro
        Tarefa t4 = new Tarefa(4, "Enviar emails em lote");

        // Criando os processadores (Runnables)
        ProcessadorRapido pr1 = new ProcessadorRapido(t1);
        ProcessadorLento pl1 = new ProcessadorLento(t2);
        ProcessadorRapido pr2 = new ProcessadorRapido(t3);
        ProcessadorLento pl2 = new ProcessadorLento(t4);

        // Instanciando as Threads
        Thread thread1 = new Thread(pr1, "Thread-Rapida-1");
        Thread thread2 = new Thread(pl1, "Thread-Lenta-1");
        Thread thread3 = new Thread(pr2, "Thread-Rapida-2");
        Thread thread4 = new Thread(pl2, "Thread-Lenta-2");

        // Configurando prioridades
        thread1.setPriority(Thread.MAX_PRIORITY); // Prioridade 10
        thread3.setPriority(Thread.MAX_PRIORITY); // Prioridade 10
        
        thread2.setPriority(Thread.MIN_PRIORITY); // Prioridade 1
        thread4.setPriority(Thread.MIN_PRIORITY); // Prioridade 1

        // Configurando a thread 4 como Daemon
        thread4.setDaemon(true);

        // Imprimindo estados ANTES da inicialização
        System.out.println("Estado de " + thread1.getName() + " antes do start: " + thread1.getState());
        System.out.println("Estado de " + thread2.getName() + " antes do start: " + thread2.getState());
        System.out.println("A " + thread4.getName() + " é Daemon? " + thread4.isDaemon());
        System.out.println("\n--- Disparando as Threads ---\n");

        // Iniciando as threads
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        // Imprimindo estados APÓS a inicialização
        System.out.println("Estado de " + thread1.getName() + " após o start: " + thread1.getState());
        System.out.println("Estado de " + thread2.getName() + " após o start: " + thread2.getState());
        System.out.println("-----------------------------\n");
    }
}