package Caca_Ao_Tesouro;

import java.util.concurrent.Semaphore;

/**
 * Subclasse de Explorador especializada em recuperar grandes tesouros.
 * Executa tarefas mais devagar, mas com um multiplicador de pontos base muito maior.
 */
public class Saqueador extends Explorador {

    /**
     * Construtor do Saqueador.
     * Repassa o tipo "Saqueador" direto para a classe pai.
     */
    public Saqueador(String nome, Missao missao, Semaphore semaforo, Sinalizador sinalizador) {
        super(nome, "Saqueador", missao, semaforo, sinalizador);
    }

    /**
     * Simula a execução da missão pelo saqueador (2.5 segundos) e calcula os pontos.
     * 
     * @return Os pontos obtidos (Base 100 * Dificuldade).
     * @throws InterruptedException Se a thread for interrompida na simulação de tempo.
     */
    @Override
    protected Double calcularPontos() throws InterruptedException {
        System.out.println(" -> " + nome + " (Cuidadoso) explorando: " + missao.getDescricao());
        
        // Simula a lentidão por conta da carga pesada e cuidado extremo
        Thread.sleep(2500); 
        
        double pontos = 100.0 * missao.getDificuldade();
        System.out.println(" <- " + nome + " concluiu o resgate! (+" + pontos + " pontos) e liberou a trilha.");
        
        return pontos;
    }
}