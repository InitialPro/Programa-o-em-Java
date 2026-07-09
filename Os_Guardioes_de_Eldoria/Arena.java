package Os_Guardioes_de_Eldoria;

public class Arena {
    
    // Método que recebe dois objetos complexos (Grupos) como parâmetro
    public void batalharGrupos(Grupo g1, Grupo g2) {
        System.out.println("\n⚔️ --- INICIANDO BATALHA DE GRUPOS --- ⚔️");
        System.out.println(g1.getNomeDoGrupo() + " VS " + g2.getNomeDoGrupo() + "\n");

        // Define o número de duelos pelo grupo que tem menos membros para evitar erros
        int numeroDeDuelos = Math.min(g1.getMembros().size(), g2.getMembros().size());

        for (int i = 0; i < numeroDeDuelos; i++) {
            Personagem lutador1 = g1.getMembros().get(i);
            Personagem lutador2 = g2.getMembros().get(i);
            
            System.out.print("Duelo " + (i + 1) + ": ");
            // Usamos a lógica de batalha que já existe dentro do Grupo 1 (poderia ser isolada, mas atende à regra)
            g1.batalhar(lutador1, lutador2); 
        }
    }
}