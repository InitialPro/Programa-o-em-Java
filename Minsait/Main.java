package Minsait;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Iniciando Cadastro de Funcionários ---\n");

        ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();

        // 1. Criando instâncias de Gerentes e Desenvolvedores
        listaFuncionarios.add(new Gerente("João", "G-001", 8500.00));
        listaFuncionarios.add(new Gerente("Ana", "G-002", 9200.00));
        listaFuncionarios.add(new Desenvolvedor("Maria", "D-001", 5000.00));
        // Testando a validação de salário negativo
        listaFuncionarios.add(new Desenvolvedor("Carlos", "D-002", -1500.00)); 

        System.out.println("\n--- Teste de Igualdade (equals) ---");
        // Criando um funcionário fictício com o mesmo ID da Maria para testar
        Funcionario cloneMaria = new Desenvolvedor("Maria Nova", "D-001", 6000.00);
        Funcionario funcTeste = listaFuncionarios.get(2); // Maria original (ID D-001)

        System.out.println("Comparando " + funcTeste.getNome() + " com " + cloneMaria.getNome() + ":");
        if (funcTeste.equals(cloneMaria)) {
            System.out.println("Resultado: São o mesmo funcionário (Mesmo Identificador).");
        } else {
            System.out.println("Resultado: São funcionários diferentes.");
        }

        System.out.println("\n--- Processamento da Folha e Bônus ---");
        // 2. Iterando sobre a lista
        for (Funcionario f : listaFuncionarios) {
            // Imprime as informações usando toString()
            System.out.println(f.toString());
            
            // Verifica o tipo real utilizando instanceof
            if (f instanceof Gerente) {
                System.out.println("O funcionário " + f.getNome() + " é um Gerente.");
            } else if (f instanceof Desenvolvedor) {
                System.out.println("A/O funcionária(o) " + f.getNome() + " é um(a) Desenvolvedor(a).");
            }

            // Calcula e mostra o bônus de forma polimórfica
            System.out.printf("Bônus a receber: R$ %.2f\n", f.calcularBonus());
            System.out.println("-------------------------------------------------");
        }
    }
}