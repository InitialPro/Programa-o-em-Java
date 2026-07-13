package Codigo_Fonte;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AlunoDAO dao = new AlunoDAO();

        // 1. Inserindo 3 alunos
        System.out.println("--- Inserindo Alunos ---");
        dao.inserir(new Aluno("MAT001", "Lucas Pereira", 1));
        dao.inserir(new Aluno("MAT002", "Ana Beatriz", 2));
        dao.inserir(new Aluno("MAT003", "Carlos Mendes", 1));
        System.out.println("Alunos inseridos no banco.\n");

        // 2. Listando todos os alunos
        System.out.println("--- Lista de Alunos (Após Inserção) ---");
        List<Aluno> listaInicial = dao.listarTodos();
        for (Aluno a : listaInicial) {
            System.out.println(a);
        }
        System.out.println();

        // 3. Removendo um aluno pela matrícula
        System.out.println("--- Removendo aluno de matrícula MAT002 ---");
        dao.remover("MAT002");
        System.out.println();

        // 4. Listando novamente para verificar a exclusão
        System.out.println("--- Lista de Alunos (Após Remoção) ---");
        List<Aluno> listaFinal = dao.listarTodos();
        for (Aluno a : listaFinal) {
            System.out.println(a);
        }

        // Fecha a conexão com o banco
        dao.fechar();
    }
}