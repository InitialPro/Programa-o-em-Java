package Super_Trunfo.src;

import java.util.List;
import java.util.Scanner;

/**
 * Classe principal que interage com o usuário, exibindo menus, formatando as cartas 
 * em ASCII e gerenciando as batalhas.
 */
public class SuperTrunfoJDBC {

    private static Scanner scanner = new Scanner(System.in);
    private static AlunoDAO dao = new AlunoDAO();

    public static void main(String[] args) {
        int opcao = 0;
        
        System.out.println("==================================================");
        System.out.println("  BEM-VINDO AO SUPER TRUNFO - EDIÇÃO ESTUDANTES!  ");
        System.out.println("==================================================");

        while (opcao != 6) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Cadastrar Carta (Aluno)");
            System.out.println("2. Listar Cartas");
            System.out.println("3. Atualizar Carta");
            System.out.println("4. Deletar Carta");
            System.out.println("5. BATALHA DE CARTAS!");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = 0;
            }

            switch (opcao) {
                case 1: cadastrar(); break;
                case 2: listar(); break;
                case 3: atualizar(); break;
                case 4: deletar(); break;
                case 5: batalhar(); break;
                case 6: System.out.println("Encerrando o jogo... Até a próxima!"); break;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    /**
     * Interage com o usuário para criar um novo aluno e envia ao DAO.
     */
    private static void cadastrar() {
        System.out.print("\nDigite a Matrícula (Ex: A202301): ");
        String matricula = scanner.nextLine();
        System.out.print("Digite o Nome do Aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o Ano de Entrada (Ex: 2021): ");
        int ano = Integer.parseInt(scanner.nextLine());

        Aluno aluno = new Aluno(matricula, nome, ano);
        dao.cadastrarAluno(aluno);
    }

    /**
     * Recupera todos os alunos do DAO e imprime na tela formatado como uma carta ASCII.
     */
    private static void listar() {
        List<Aluno> alunos = dao.listarTodos();
        if (alunos.isEmpty()) {
            System.out.println("\nNenhuma carta cadastrada no seu deck ainda.");
            return;
        }

        System.out.println("\n--- SEU DECK DE CARTAS ---");
        for (Aluno a : alunos) {
            imprimirCarta(a);
        }
    }

    /**
     * Atualiza dados básicos do aluno (Nome e Ano de Entrada).
     */
    private static void atualizar() {
        System.out.print("\nDigite a Matrícula da carta que deseja atualizar: ");
        String matricula = scanner.nextLine();
        System.out.print("Digite o novo Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o novo Ano de Entrada: ");
        int ano = Integer.parseInt(scanner.nextLine());

        Aluno aluno = new Aluno(matricula, nome, ano);
        dao.atualizarAluno(aluno);
    }

    /**
     * Deleta um aluno do banco via DAO.
     */
    private static void deletar() {
        System.out.print("\nDigite a Matrícula da carta que deseja destruir: ");
        String matricula = scanner.nextLine();
        dao.deletarAluno(matricula);
    }

    /**
     * Implementa a lógica básica de batalha do Super Trunfo.
     * Regra da batalha: O atributo em disputa é a Experiência (Menor Ano de Entrada = Mais Veterano = Vence).
     */
    private static void batalhar() {
        System.out.println("\n⚔️ --- HORA DA BATALHA --- ⚔️");
        System.out.print("Digite a matrícula do Jogador 1: ");
        String mat1 = scanner.nextLine();
        System.out.print("Digite a matrícula do Jogador 2: ");
        String mat2 = scanner.nextLine();

        Aluno a1 = dao.buscarPorMatricula(mat1);
        Aluno a2 = dao.buscarPorMatricula(mat2);

        if (a1 == null || a2 == null) {
            System.out.println("❌ Uma das matrículas (ou ambas) não existe. Batalha cancelada!");
            return;
        }

        System.out.println("\n[ Carta 1 ]");
        imprimirCarta(a1);
        System.out.println("        VS");
        System.out.println("[ Carta 2 ]");
        imprimirCarta(a2);

        // Regra: Menor ano de entrada ganha (Aluno mais veterano)
        System.out.println("\nAtributo disputado: Experiência (Ano de Entrada Mais Antigo)");
        
        if (a1.getAnoEntrada() < a2.getAnoEntrada()) {
            System.out.println("🏆 JOGADOR 1 (" + a1.getNome() + ") VENCEU!");
        } else if (a2.getAnoEntrada() < a1.getAnoEntrada()) {
            System.out.println("🏆 JOGADOR 2 (" + a2.getNome() + ") VENCEU!");
        } else {
            System.out.println("⚖️ EMPATE! Ambos entraram no mesmo ano!");
        }
    }

    /**
     * Formata a exibição do aluno no terminal imitando uma carta real de Super Trunfo.
     */
    private static void imprimirCarta(Aluno aluno) {
        System.out.println("===================================");
        System.out.println("|       SUPER TRUNFO - ALUNO      |");
        System.out.println("===================================");
        System.out.printf("| Nome: %-25s |\n", aluno.getNome());
        System.out.printf("| Matrícula: %-20s |\n", aluno.getMatricula());
        System.out.printf("| Ano de Entrada: %-15d |\n", aluno.getAnoEntrada());
        System.out.printf("| Raridade: %-21s |\n", aluno.getRaridade());
        System.out.println("===================================\n");
    }
}