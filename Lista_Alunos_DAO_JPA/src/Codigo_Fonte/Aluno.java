package Codigo_Fonte;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Aluno {
    
    @Id
    private String matricula;
    private String nome;
    private int ano;

    // Construtor vazio exigido pelo JPA
    public Aluno() {
    }

    public Aluno(String matricula, String nome, int ano) {
        this.matricula = matricula;
        this.nome = nome;
        this.ano = ano;
    }

    // Getters e Setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    // Facilita a exibição no console
    @Override
    public String toString() {
        return "Aluno [Matrícula: " + matricula + ", Nome: " + nome + ", Ano: " + ano + "]";
    }
}