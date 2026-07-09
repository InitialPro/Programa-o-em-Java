import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grupo {
    private String nomeDoGrupo;
    // Encapsulamento da coleção
    private List<Personagem> membros;

    public Grupo(String nomeDoGrupo) {
        this.nomeDoGrupo = nomeDoGrupo;
        this.membros = new ArrayList<>(); // Inicializando a lista vazia
    }

    public String getNomeDoGrupo() {
        return nomeDoGrupo;
    }

    public List<Personagem> getMembros() {
        return membros;
    }

    public void adicionarPersonagem(Personagem p) {
        membros.add(p);
    }

    public void listarPersonagens() {
        System.out.println("\n🛡️ Grupo: " + nomeDoGrupo + " 🛡️");
        for (Personagem p : membros) {
            p.exibirStatus();
        }
    }

    // Método bônus para usar a ordenação implementada no Personagem
    public void ordenarPorNivel() {
        Collections.sort(membros);
    }

    // Lógica de duelo simples
    public void batalhar(Personagem a, Personagem b) {
        double poderTotalA = a.getNivel() * a.getPoderBase();
        double poderTotalB = b.getNivel() * b.getPoderBase();

        if (poderTotalA > poderTotalB) {
            System.out.println(a.getClasse() + " " + a.getNome() + " venceu! Poder total: " + poderTotalA);
        } else if (poderTotalB > poderTotalA) {
            System.out.println(b.getClasse() + " " + b.getNome() + " venceu! Poder total: " + poderTotalB);
        } else {
            System.out.println("Empate épico entre " + a.getNome() + " e " + b.getNome() + "!");
        }
    }
}