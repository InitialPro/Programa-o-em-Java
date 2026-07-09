package Os_Guardioes_de_Eldoria_A_Saga_Continua;

public class Mago extends Personagem {
    
    public Mago(String nome, String classe, int nivel, int pontosDeVida, double poderBase) {
        super(nome, classe, nivel, pontosDeVida, poderBase);
    }

    // NOME CORRIGIDO AQUI
    @Override
    public void usarHabilidadeEspecial() {
        System.out.println("Ação: O Mago " + getNome() + " conjura uma tempestade arcana devastadora!");
    }
}