package SimuladorDePedidos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class FilaPedidos {
    private final Queue<Pedido> fila = new LinkedList<>();
    
    // Semáforo para controlar o limite máximo da fila
    private final Semaphore vagas;
    // Semáforo para avisar os consumidores que existem pedidos disponíveis
    private final Semaphore pedidosDisponiveis;

    public FilaPedidos(int capacidade) {
        this.vagas = new Semaphore(capacidade);
        this.pedidosDisponiveis = new Semaphore(0); // Começa zerado, pois não há pedidos
    }

    /**
     * Adiciona um pedido à fila. Se a fila estiver cheia, a thread bloqueia.
     */
    public void adicionarPedido(Pedido pedido) throws InterruptedException {
        vagas.acquire(); // Decrementa uma vaga. Se for 0, a thread (produtor) dorme.
        
        // Monitor: Garante que apenas uma thread manipule a estrutura LinkedList por vez
        synchronized (this) {
            fila.add(pedido);
            System.out.println("[PRODUTOR] Adicionou: " + pedido);
        }
        
        pedidosDisponiveis.release(); // Sinaliza aos consumidores que há um novo pedido
    }

    /**
     * Remove e retorna um pedido da fila. Se vazia, a thread bloqueia.
     */
    public Pedido processarPedido() throws InterruptedException {
        pedidosDisponiveis.acquire(); // Decrementa um pedido. Se for 0, a thread (consumidor) dorme.
        
        Pedido pedido;
        // Monitor: Bloqueia a fila enquanto remove o elemento
        synchronized (this) {
            pedido = fila.poll();
        }
        
        vagas.release(); // Sinaliza aos produtores que abriu uma nova vaga na fila
        return pedido;
    }
}