package CacaAoTesouro;

/**
 * Monitor que atua como uma barreira de largada para as threads.
 * Utiliza os métodos wait() e notifyAll() para sincronizar o início da expedição.
 */
public class Sinalizador {
    private boolean sinalAberto = false;

    /**
     * Trava a thread atual em estado de espera (WAITING) até que o sinal seja aberto.
     * O 'synchronized' garante que a verificação da variável seja thread-safe.
     * 
     * @param nome O nome do explorador que acabou de chegar ao acampamento.
     * @throws InterruptedException Caso a thread seja interrompida enquanto dorme.
     */
    public synchronized void aguardarSinal(String nome) throws InterruptedException {
        System.out.println(nome + " no acampamento aguardando a largada...");
        
        // Loop while evita o "spurious wakeup" (quando a thread acorda sem motivo na JVM)
        while (!sinalAberto) {
            wait(); // Libera o lock da classe e põe a thread para dormir
        }
    }

    /**
     * Método invocado pela thread principal (Líder) para liberar a barreira.
     * Altera a condição e desperta todas as threads que estavam presas no wait().
     */
    public synchronized void darSinal() {
        System.out.println("\n[SINAL VERDE] O líder liberou a expedição! A corrida começou!\n");
        sinalAberto = true;
        notifyAll(); // Acorda todas as threads em espera
    }
}