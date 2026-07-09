public class Mago extends Personagem {
    
    // Construtor utilizando o 'super' para acessar a superclasse
    public Mago(String nome, String classe, int nivel, int pontosDeVida, double poderBase) {
        super(nome, classe, nivel, pontosDeVida, poderBase);
    }

    // Implementação da habilidade específica
    @Override
    public void usarHabilidade() {
        System.out.println("Ação: O Mago " + getNome() + " conjura uma tempestade arcana devastadora!");
    }
}