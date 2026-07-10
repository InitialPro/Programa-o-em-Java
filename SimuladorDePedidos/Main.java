package SimuladorDePedidos;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Cria uma fila com capacidade para apenas 3 pedidos simultâneos
        FilaPedidos fila = new FilaPedidos(3);

        // Instancia os runnables
        OperadorRapido rapido = new OperadorRapido(fila);
        OperadorCuidadoso cuidadoso = new OperadorCuidadoso(fila);

        // Cria as threads
        Thread threadRapida = new Thread(rapido);
        Thread threadCuidadosa = new Thread(cuidadoso);

        // Define prioridades: O rápido ganha mais tempo de CPU do escalonador
        threadRapida.setPriority(Thread.MAX_PRIORITY);
        threadCuidadosa.setPriority(Thread.MIN_PRIORITY);

        // Configura como Daemon: as threads morrem quando a main() terminar
        threadRapida.setDaemon(true);
        threadCuidadosa.setDaemon(true);

        // Inicia as threads consumidoras
        threadRapida.start();
        threadCuidadosa.start();

        // A thread principal atua como Produtor
        System.out.println("=== Iniciando Simulação ===");
        for (int i = 1; i <= 10; i++) {
            Pedido pedido = new Pedido(i, "Item " + i, i * 10.50);
            fila.adicionarPedido(pedido);
            
            // Pausa pequena para a produção não ser instantânea
            Thread.sleep(300);
        }

        // Aguarda um pouco para dar tempo aos operadores terminarem a fila
        // antes de a thread principal (e o programa) encerrar.
        System.out.println("=== Produtor finalizou os envios, aguardando limpeza da fila... ===");
        Thread.sleep(5000);
        
        System.out.println("=== Sistema encerrado ===");
    }
}
