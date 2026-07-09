package Gerenciamento_de_Colaboradores;

public class Desenvolvedor extends Colaborador {

    public Desenvolvedor(String nome, int matricula, double salario) {
        super(nome, matricula, salario);
    }

    @Override
    public void executarTarefa() {
        System.out.println(getNome() + " está codificando uma nova funcionalidade.");
    }
}