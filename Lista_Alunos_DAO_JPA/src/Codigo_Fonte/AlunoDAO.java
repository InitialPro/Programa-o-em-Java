package Codigo_Fonte;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class AlunoDAO {
    
    private EntityManagerFactory emf;
    private EntityManager em;

    public AlunoDAO() {
        // O nome deve ser idêntico ao "persistence-unit name" definido no persistence.xml
        emf = Persistence.createEntityManagerFactory("AlunosPU");
        em = emf.createEntityManager();
    }

    public void inserir(Aluno aluno) {
        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();
    }

    public List<Aluno> listarTodos() {
        TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a", Aluno.class);
        return query.getResultList();
    }

    public void remover(String matricula) {
        // Busca o aluno primeiro para gerenciá-lo
        Aluno aluno = em.find(Aluno.class, matricula);
        
        if (aluno != null) {
            em.getTransaction().begin();
            em.remove(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno removido com sucesso!");
        } else {
            System.out.println("Aluno não encontrado para a matrícula: " + matricula);
        }
    }

    public void fechar() {
        em.close();
        emf.close();
    }
}