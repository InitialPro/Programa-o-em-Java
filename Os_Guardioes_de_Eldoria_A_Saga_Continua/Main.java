package Os_Guardioes_de_Eldoria_A_Saga_Continua;

public class Main {
    public static void main(String[] args) {
        Mago mago = new Mago("Eldor", "Mago do Fogo", 10, 150, 85.5);
        Guerreiro guerreiro = new Guerreiro("Thogar", "Guerreiro Defensor", 12, 250, 70.0);

        System.out.println("--- Exibição dos Personagens ---");
        System.out.println(mago);
        mago.usarHabilidadeEspecial(); // CHAMADA CORRIGIDA
        
        System.out.println(guerreiro);
        guerreiro.usarHabilidadeEspecial(); // CHAMADA CORRIGIDA
        System.out.println();

        System.out.println("--- Teste de Validações ---");
        try {
            System.out.println("Tentando aplicar dano fatal (-50) no HP do Guerreiro...");
            guerreiro.setPontosDeVida(-50);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro bloqueado pelo sistema: " + e.getMessage());
        }
        System.out.println();

        System.out.println("--- Teste de Comparação (equals) ---");
        Mago magoClone = new Mago("Eldor", "Mago do Fogo", 99, 999, 500.0);
        
        System.out.println("Mago 1: " + mago.toString());
        System.out.println("Mago 2 (Clone): " + magoClone.toString());
        System.out.println("Mago 1 é a mesma entidade que Mago 2? " + mago.equals(magoClone));
    }
}