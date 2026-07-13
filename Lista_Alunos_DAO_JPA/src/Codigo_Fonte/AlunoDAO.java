package Codigo_Fonte;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AlunoDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public AlunoDAO() {
        // Inicializa a fábrica de entidades com a unidade definida no persistence.xml
        this.emf = Persistence.createEntityManagerFactory("AlunosPU");
        this.em = emf.createEntityManager();
    }

    /**
     * Insere um aluno no banco de dados com validações de nulos e duplicatas.
     */
    public void inserir(Aluno aluno) {
        // 1. Bloqueio para valores nulos
        if (aluno == null || aluno.getMatricula() == null || aluno.getNome() == null) {
            System.err.println("Erro: Não é possível inserir um aluno com dados nulos.");
            return;
        }

        try {
            // 2. Busca para verificar se a matrícula já existe
            Aluno existente = em.find(Aluno.class, aluno.getMatricula());
            
            if (existente != null) {
                // 3. Verificação com equals (ou comparação de matrícula)
                System.err.println("Erro: O aluno com matrícula " + aluno.getMatricula() + " já existe no sistema!");
            } else {
                em.getTransaction().begin();
                em.persist(aluno);
                em.getTransaction().commit();
                System.out.println("Aluno " + aluno.getNome() + " inserido com sucesso.");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro inesperado ao inserir aluno: " + e.getMessage());
        }
    }

    /**
     * Remove um aluno pelo ID.
     */
    public void remover(String matricula) {
        try {
            em.getTransaction().begin();
            Aluno aluno = em.find(Aluno.class, matricula);
            
            if (aluno != null) {
                em.remove(aluno);
                em.getTransaction().commit();
                System.out.println("Aluno de matrícula " + matricula + " removido com sucesso!");
            } else {
                System.err.println("Erro: Aluno não encontrado para remoção.");
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao remover aluno: " + e.getMessage());
        }
    }

    /**
     * Lista todos os alunos cadastrados.
     */
    public List<Aluno> listarTodos() {
        try {
            return em.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao listar alunos: " + e.getMessage());
            return null;
        }
    }

    /**
     * Fecha os recursos do banco de dados ao finalizar o programa.
     */
    public void fechar() {
        em.close();
        emf.close();
    }
}