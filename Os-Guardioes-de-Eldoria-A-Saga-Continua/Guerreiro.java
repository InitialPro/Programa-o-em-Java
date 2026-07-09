public class Guerreiro extends Personagem {
    
    // Construtor utilizando o 'super' para acessar a superclasse
    public Guerreiro(String nome, String classe, int nivel, int pontosDeVida, double poderBase) {
        super(nome, classe, nivel, pontosDeVida, poderBase);
    }

    // Implementação da habilidade específica
    @Override
    public void usarHabilidade() {
        System.out.println("Ação: O Guerreiro " + getNome() + " desfere um golpe esmagador com sua espada pesada!");
    }
}