package CacaAoTesouro;

import java.util.ArrayList;

public class CacaAoTesouroMain {
    public static void main(String[] args) {
        System.out.println("=== Iniciando a Expedição na Ilha Misteriosa! ===\n");

        // 1. Criar os exploradores
        ExploradorRapido alice = new ExploradorRapido("Alice", Thread.MAX_PRIORITY, "a caverna escura");
        ExploradorCuidadoso bob = new ExploradorCuidadoso("Bob", Thread.NORM_PRIORITY, "a floresta densa");
        ExploradorRapido charlie = new ExploradorRapido("Charlie", Thread.MIN_PRIORITY, "as ruínas antigas");
        
        // Clara recebe uma tarefa vazia para demonstrar a exceção personalizada
        ExploradorCuidadoso clara = new ExploradorCuidadoso("Clara", Thread.NORM_PRIORITY, ""); 

        // 2. Criar as Threads e configurar as prioridades
        Thread threadAlice = new Thread(alice);
        threadAlice.setPriority(alice.getPrioridade());

        Thread threadBob = new Thread(bob);
        threadBob.setPriority(bob.getPrioridade());

        Thread threadCharlie = new Thread(charlie);
        threadCharlie.setPriority(charlie.getPrioridade());

        Thread threadClara = new Thread(clara);
        threadClara.setPriority(clara.getPrioridade());

        // 3. Organizar Threads em um ArrayList conforme exigido
        ArrayList<Thread> expedicao = new ArrayList<>();
        expedicao.add(threadAlice);
        expedicao.add(threadBob);
        expedicao.add(threadCharlie);
        expedicao.add(threadClara);

        // 4. Iniciar todas as threads em paralelo usando repetição (permitido no main)
        for (Thread explorador : expedicao) {
            explorador.start();
        }
        
        System.out.println(">> Todos os exploradores foram despachados!\n");
    }
}