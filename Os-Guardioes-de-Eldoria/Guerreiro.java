public class Guerreiro extends Personagem {
    
    // O construtor do Guerreiro já define a "classe" automaticamente no super()
    public Guerreiro(String nome, int nivel, int pontosDeVida, double poderBase) {
        super(nome, "Guerreiro", nivel, pontosDeVida, poderBase);
    }

    // Sobrescrevendo o comportamento abstrato
    @Override
    public void usarHabilidadeEspecial() {
        System.out.println("⚔️ " + nome + " avança e ataca com sua Espada Flamejante!");
    }
}