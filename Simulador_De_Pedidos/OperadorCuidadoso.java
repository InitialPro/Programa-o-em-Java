package Simulador_De_Pedidos;

public class OperadorCuidadoso implements Runnable {
    private final FilaPedidos fila;

    public OperadorCuidadoso(FilaPedidos fila) {
        this.fila = fila;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Pedido p = fila.processarPedido();
                
                // Simula um processamento demorado e meticuloso
                Thread.sleep(2000); 
                
                // Bloco synchronized para evitar concorrência no console
                synchronized (System.out) {
                    System.out.println("🐢 [CUIDADOSO] Finalizou: " + p.getDescricao());
                }
            }
        } catch (InterruptedException e) {
            System.out.println("🐢 Operador Cuidadoso interrompido.");
        }
    }
}