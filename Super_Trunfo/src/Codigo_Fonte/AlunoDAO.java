package Super_Trunfo.src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar a conexão e as operações de CRUD no banco Apache Derby.
 */
public class AlunoDAO {
    // URL de conexão com o Derby (cria o banco 'banco_supertrunfo' automaticamente se não existir)
    private static final String URL = "jdbc:derby:Super_Trunfo/banco_supertrunfo;create=true";

    /**
     * Construtor do DAO. Ao ser instanciado, ele tenta criar a tabela 'alunos'
     * caso ela ainda não exista no banco de dados.
     */
    public AlunoDAO() {
        criarTabelaSeNaoExistir();
    }

    /**
     * Obtém a conexão com o banco de dados.
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /**
     * Executa o DDL para criar a tabela de alunos.
     * Ignora o erro se a tabela já existir (SQLState X0Y32 no Derby).
     */
    private void criarTabelaSeNaoExistir() {
        String sql = "CREATE TABLE alunos ("
                   + "matricula VARCHAR(20) PRIMARY KEY, "
                   + "nome VARCHAR(100), "
                   + "ano_entrada INT, "
                   + "raridade VARCHAR(50))";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            // O código X0Y32 indica que a tabela já existe no Apache Derby
            if (!e.getSQLState().equals("X0Y32")) {
                e.printStackTrace();
            }
        }
    }

    /**
     * CREATE - Cadastra uma nova carta (aluno) no banco de dados.
     */
    public void cadastrarAluno(Aluno aluno) {
        String sql = "INSERT INTO alunos (matricula, nome, ano_entrada, raridade) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, aluno.getMatricula());
            pstmt.setString(2, aluno.getNome());
            pstmt.setInt(3, aluno.getAnoEntrada());
            pstmt.setString(4, aluno.getRaridade());
            pstmt.executeUpdate();
            System.out.println("✅ Aluno cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao cadastrar (A matrícula pode já existir).");
        }
    }

    /**
     * READ - Retorna todos os alunos cadastrados no banco.
     */
    public List<Aluno> listarTodos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM alunos";
        try (Connection conn = getConnection(); 
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setNome(rs.getString("nome"));
                aluno.setAnoEntrada(rs.getInt("ano_entrada"));
                aluno.setRaridade(rs.getString("raridade"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    /**
     * UPDATE - Atualiza o nome e ano de entrada de um aluno existente.
     */
    public void atualizarAluno(Aluno aluno) {
        String sql = "UPDATE alunos SET nome = ?, ano_entrada = ? WHERE matricula = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, aluno.getNome());
            pstmt.setInt(2, aluno.getAnoEntrada());
            pstmt.setString(3, aluno.getMatricula());
            
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("✅ Aluno atualizado com sucesso!");
            } else {
                System.out.println("❌ Aluno não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * DELETE - Remove um aluno do banco pela matrícula.
     */
    public void deletarAluno(String matricula) {
        String sql = "DELETE FROM alunos WHERE matricula = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, matricula);
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("✅ Aluno removido do jogo!");
            } else {
                System.out.println("❌ Aluno não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Busca um aluno específico pela matrícula para ser usado na batalha.
     */
    public Aluno buscarPorMatricula(String matricula) {
        String sql = "SELECT * FROM alunos WHERE matricula = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, matricula);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Aluno aluno = new Aluno();
                    aluno.setMatricula(rs.getString("matricula"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setAnoEntrada(rs.getInt("ano_entrada"));
                    aluno.setRaridade(rs.getString("raridade"));
                    return aluno;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}