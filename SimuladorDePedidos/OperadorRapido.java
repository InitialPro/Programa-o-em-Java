package SimuladorDePedidos;

public class OperadorRapido implements Runnable {
    private final FilaPedidos fila;

    public OperadorRapido(FilaPedidos fila) {
        this.fila = fila;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Pedido p = fila.processarPedido();
                
                // Simula um processamento muito rápido
                Thread.sleep(500); 
                
                // Bloco synchronized para garantir que o print saia inteiro no console
                synchronized (System.out) {
                    System.out.println("⚡ [RÁPIDO] Finalizou: " + p.getDescricao());
                }
            }
        } catch (InterruptedException e) {
            System.out.println("⚡ Operador Rápido interrompido.");
        }
    }
}