package Os_Guardioes_de_Eldoria_A_Saga_Continua;

public class Guerreiro extends Personagem {
    
    public Guerreiro(String nome, String classe, int nivel, int pontosDeVida, double poderBase) {
        super(nome, classe, nivel, pontosDeVida, poderBase);
    }

    @Override
    public void usarHabilidadeEspecial() {
        System.out.println("Habilidade: " + getNome() + " avança com um ataque devastador!");
    }
}