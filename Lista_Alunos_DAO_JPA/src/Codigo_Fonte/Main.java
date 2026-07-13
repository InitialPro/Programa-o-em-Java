package Codigo_Fonte;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AlunoDAO dao = new AlunoDAO();

        System.out.println("--- Teste 1: Inserindo aluno válido ---");
        Aluno a1 = new Aluno("MAT001", "Lucas Pereira", 1);
        dao.inserir(a1);

        System.out.println("\n--- Teste 2: Tentando inserir aluno com mesma matrícula (Duplicidade) ---");
        Aluno a2 = new Aluno("MAT001", "Outro Nome", 2);
        dao.inserir(a2); // O DAO deve imprimir o erro no console

        System.out.println("\n--- Teste 3: Tentando inserir aluno nulo ---");
        dao.inserir(null); // O DAO deve bloquear

        System.out.println("\n--- Teste 4: Listando alunos cadastrados ---");
        List<Aluno> lista = dao.listarTodos();
        for (Aluno a : lista) {
            System.out.println(a);
        }

        System.out.println("\n--- Teste 5: Removendo aluno de matrícula MAT001 ---");
        dao.remover("MAT001");

        System.out.println("\n--- Teste 6: Listando alunos após a remoção (Status atual) ---");
        List<Aluno> listaFinal = dao.listarTodos();
        
        if (listaFinal.isEmpty()) {
            System.out.println("O banco de dados está vazio.");
        } else {
            for (Aluno a : listaFinal) {
                System.out.println(a);
            }
        }

        // Sempre fechar a conexão ao final
        dao.fechar();
        System.out.println("\nPrograma finalizado.");
    }
}