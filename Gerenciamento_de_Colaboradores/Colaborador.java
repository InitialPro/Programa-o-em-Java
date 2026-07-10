package Gerenciamento_de_Colaboradores;


public abstract class Colaborador {
    private String nome;
    private int matricula;
    private double salario;

    public Colaborador(String nome, int matricula, double salario) {
        this.nome = nome;
        this.matricula = matricula;
        this.salario = salario;
    }

    // Getters
    public String getNome() { return nome; }
    public int getMatricula() { return matricula; }
    public double getSalario() { return salario; }

    // Método aumentar Salário
    public void aumentarSalario(double percentual){
        if (percentual > 0) {
            double salarioAntigo = salario;
            double aumento = salario * (percentual / 100);
            salario += aumento;
            System.out.println("Salário de " + salarioAntigo + " aumento em " + percentual + 
                              "%. Novo Salário: R$ " + String.format("%.2f", salario));
        }else{
            System.out.println("Erro: Percentual de aumento deve ser positivo!");
        }
    }

    // Método abstrato: as subclasses são obrigadas a implementar
    public abstract void executarTarefa();

    // Método de exibição
    public void exibirDados() {
        System.out.printf("Nome: %s | Matrícula: %d | Salário: R$ %.2f%n", nome, matricula, salario);
    }

    // Método protegido: visível apenas no mesmo pacote ou em subclasses
    protected void atribuirBonus(double valor) {
        if (valor > 0) {
            this.salario += valor;
            System.out.println("Bônus de R$ " + valor + " aplicado com sucesso a " + this.nome + ".");
        } else{
            System.out.println("Valor de bonus deve ser positivo!");
        }
    }
}