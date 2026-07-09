package Os_Guardioes_de_Eldoria;

public class Mago extends Personagem {
    
    public Mago(String nome, int nivel, int pontosDeVida, double poderBase) {
        super(nome, "Mago", nivel, pontosDeVida, poderBase);
    }

    @Override
    public void usarHabilidadeEspecial() {
        System.out.println("🔮 " + nome + " conjura uma massiva Tempestade Arcana!");
    }
}