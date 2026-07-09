package Os_Guardioes_de_Eldoria_A_Saga_Continua;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== BEM-VINDOS AO REINO DE ELDORIA ===");
        
        // Polimorfismo Dinâmico: Uma lista de "Personagens" que guarda vários subtipos
        ArrayList<Personagem> herois = new ArrayList<>();
        
        // Cadastrando múltiplos personagens (2 Magos e 2 Guerreiros)
        herois.add(new Mago("Eldoran", "Mago", 7, 60, 18.0));
        herois.add(new Guerreiro("Arthemis", "Guerreiro", 8, 120, 25.0));
        herois.add(new Mago("Lyra", "Mago das Sombras", 10, 50, 30.0));
        herois.add(new Guerreiro("Kael", "Guerreiro Defensor", 7, 150, 20.0));

        // Iterando sobre a lista demonstrando polimorfismo dinâmico
        int contador = 1;
        for (Personagem heroi : herois) {
            System.out.println("--- Herói " + contador + " ---");
            System.out.println(heroi.toString());
            
            // O Java descobre sozinho qual versão do método rodar
            heroi.usarHabilidadeEspecial();
            
            // Verificação de Tipos usando instanceof
            if (heroi instanceof Mago) {
                System.out.println("O personagem " + heroi.getNome() + " é um Mago de nível " + heroi.getNivel() + ".");
            } else if (heroi instanceof Guerreiro) {
                System.out.println("O personagem " + heroi.getNome() + " é um Guerreiro de nível " + heroi.getNivel() + ".");
            }
            
            System.out.println(); // Linha em branco para separar
            contador++;
        }

        // Demonstração de Comparação usando equals()
        Personagem heroi1 = herois.get(0); // Eldoran
        Personagem heroi2 = herois.get(1); // Arthemis
        
        if (heroi1.equals(heroi2)) {
            System.out.println("Comparação: " + heroi1.getNome() + " e " + heroi2.getNome() + " são iguais.");
        } else {
            System.out.println("Comparação: " + heroi1.getNome() + " e " + heroi2.getNome() + " são diferentes.");
        }
    }
}