package Controle_de_Conta_Bancaria;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Criando a coleção (ArrayList)
        ArrayList<Conta> agenciaDigital = new ArrayList<>();

        // Adicionando Contas Correntes
        agenciaDigital.add(new ContaCorrente("Ana Silva", 1001, 500.0));
        agenciaDigital.add(new ContaCorrente("Bruno Costa", 1002, 150.0));

        // Adicionando Contas Poupanças
        agenciaDigital.add(new ContaPoupanca("Carlos Souza", 2001, 1000.0));
        agenciaDigital.add(new ContaPoupanca("Daniela Lima", 2002, 300.0));

        System.out.println("=== PROCESSANDO OPERAÇÕES DA AGÊNCIA ===");

        // Iterando pela lista e aplicando o polimorfismo dinâmico
        for (Conta conta : agenciaDigital) {
            
            // 1. Mostrar estado inicial
            conta.exibirInformacoes();

            // 2. Realizar um depósito arbitrário de R$ 200
            System.out.println("\n-> Executando operação de depósito:");
            conta.depositar(200.0);

            // 3. Tentar realizar um saque de R$ 155
            // Nota: R$ 155 vai funcionar na corrente, mas vai falhar na poupança por não ser múltiplo de 10!
            System.out.println("\n-> Tentando realizar saque de R$ 155.00:");
            conta.sacar(155.0);

            // 4. Mostrar estado final após as movimentações
            System.out.println("\n-> Estado final da conta após movimentações:");
            conta.exibirInformacoes();
            System.out.println("\n====================================");
        }
    }
}