package Equipe_Empresa;

import java.util.Objects;

public abstract class Funcionario {
    private String nome;
    private String identificador;
    private double salario;

    // Construtor
    public Funcionario(String nome, String identificador, double salario) {
        this.nome = nome;
        this.identificador = identificador;
        setSalario(salario); // Chama o setter para aproveitar a regra de validação
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        if (salario < 0) {
            System.out.println("-> ALERTA: Carlos " + this.nome + " tem o salário negativo: " + salario);
        } else {
            this.salario = salario;
        }
    }

    // Método abstrato que será implementado pelas subclasses
    public abstract double calcularBonus();

    // Sobrescrita do toString
    @Override
    public String toString() {
        return "Funcionário: " + nome + " | ID: " + identificador + " | Salário: R$ " + String.format("%.2f", salario);
    }

    // Sobrescrita do equals usando o identificador
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Funcionario that = (Funcionario) obj;
        return Objects.equals(identificador, that.identificador);
    }

    // Sobrescrita do hashCode usando o identificador
    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }
}