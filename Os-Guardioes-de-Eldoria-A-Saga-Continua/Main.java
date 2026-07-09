public class Main {
    public static void main(String[] args) {
        // Criação de um Mago programaticamente
        Mago mago = new Mago("Eldor", "Mago do Fogo", 10, 150, 85.5);
        
        // Criação de um Guerreiro programaticamente
        Guerreiro guerreiro = new Guerreiro("Thogar", "Guerreiro Defensor", 12, 250, 70.0);

        // Exibindo os dados e habilidades do Mago
        System.out.println("--- Personagem 1 ---");
        System.out.println(mago.toString());
        mago.usarHabilidade();

        System.out.println(); // Linha em branco para separar

        // Exibindo os dados e habilidades do Guerreiro
        System.out.println("--- Personagem 2 ---");
        System.out.println(guerreiro.toString());
        guerreiro.usarHabilidade();
    }
}