public class Main {
    public static void main(String[] args) {
        
        // 1. Criando os grupos
        Grupo ordemDaLuz = new Grupo("Ordem da Luz");
        Grupo sombrasDeEldoria = new Grupo("Sombras de Eldoria");

        // 2. Criando personagens com diferentes níveis e poderes base
        Guerreiro g1 = new Guerreiro("Arthus", 10, 150, 25.0); // Poder = 250
        Mago m1 = new Mago("Elenara", 12, 100, 30.0);         // Poder = 360
        Guerreiro g2 = new Guerreiro("Garrosh", 15, 200, 20.0); // Poder = 300
        Mago m2 = new Mago("Kael", 8, 80, 28.0);             // Poder = 224

        // 3. Povoando os grupos
        ordemDaLuz.adicionarPersonagem(m1); // Elenara
        ordemDaLuz.adicionarPersonagem(g1); // Arthus

        sombrasDeEldoria.adicionarPersonagem(g2); // Garrosh
        sombrasDeEldoria.adicionarPersonagem(m2); // Kael

        // 4. Testando a ordenação (Desafio Extra)
        ordemDaLuz.ordenarPorNivel();
        sombrasDeEldoria.ordenarPorNivel();
        
        // Listando após ordenação
        ordemDaLuz.listarPersonagens();
        sombrasDeEldoria.listarPersonagens();

        // 5. Testando a Batalha em Grupo (Desafio Extra)
        Arena coliseu = new Arena();
        coliseu.batalharGrupos(ordemDaLuz, sombrasDeEldoria);
    }
}