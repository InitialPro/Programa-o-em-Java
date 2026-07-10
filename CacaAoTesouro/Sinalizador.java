package CacaAoTesouro;

/**
 * Monitor do sistema. Responsável por sincronizar a largada de todos os exploradores.
 * Garante que nenhuma thread entre na ilha antes do líder dar a ordem.
 */
public class Sinalizador {
    private boolean sinalAberto = false;

    /**
     * Método onde as threads ficam "dormindo" aguardando a liberação.
     * O uso do 'synchronized' garante que apenas uma thread por vez acesse o método para verificar o sinal.
     * 
     * @param nomeExplorador O nome da thread/explorador que está aguardando.
     * @throws InterruptedException Se a thread for interrompida enquanto aguarda.
     */
    public synchronized void aguardarSinal(String nomeExplorador) throws InterruptedException {
        System.out.println(nomeExplorador + " no acampamento aguardando a largada...");
        
        // Loop de verificação (boa prática com wait): se o sinal não está aberto, a thread dorme.
        while (!sinalAberto) {
            wait(); // A thread pausa a execução aqui e solta a trava do objeto
        }
    }

    /**
     * Método acionado pelo líder (a thread principal) para liberar a expedição.
     * Altera a variável de controle e acorda todas as threads que estão presas no wait().
     */
    public synchronized void darSinal() {
        System.out.println("\n[SINAL VERDE] O líder liberou a expedição! Corram!\n");
        sinalAberto = true; // Muda o estado para que as threads passem no while() do método anterior
        notifyAll(); // Desperta todas as threads que estavam aguardando
    }
}