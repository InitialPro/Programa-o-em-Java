package Gerenciamento_de_Colaboradores;

import java.util.ArrayList;

public class Gerente extends Colaborador implements Auditavel {
    private ArrayList<String> atividades;

    public Gerente(String nome, int matricula, double salario) {
        super(nome, matricula, salario);
        this.atividades = new ArrayList<>(); // Inicializando a lista
    }

    @Override
    public void executarTarefa() {
        System.out.println(getNome() + " está coordenando uma reunião com a equipe.");
    }

    // Implementações exclusivas da interface Auditavel
    @Override
    public void registrarAtividade(String atividade) {
        atividades.add(atividade);
        System.out.println("Atividade registrada: " + atividade);
    }

    @Override
    public void auditar() {
        System.out.println("\n--- Auditoria de Atividades do Gerente " + getNome() + " ---");
        if (atividades.isEmpty()) {
            System.out.println("Nenhuma atividade registrada no momento.");
        } else {
            for (int i = 0; i < atividades.size(); i++) {
                System.out.println((i + 1) + ". " + atividades.get(i));
            }
        }
        System.out.println("-------------------------------------------------");
    }
}