package Gerenciamento_de_Colaboradores;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // 1. Criando a lista de colaboradores (Polimorfismo)
        ArrayList<Colaborador> equipe = new ArrayList<>();

        Colaborador dev1 = new Desenvolvedor("Alice", 101, 4000.0);
        Colaborador dev2 = new Desenvolvedor("Bob", 102, 4500.0);
        Colaborador gerente = new Gerente("Carlos", 201, 10000.0);

        equipe.add(dev1);
        equipe.add(dev2);
        equipe.add(gerente);

        System.out.println("=== OPERAÇÕES DE ROTINA (POLIMORFISMO) ===");
        System.out.println();
        // 2. Iterando sobre a equipe
        int indice = 0; // Variável para controlar a posição na lista

        for (Colaborador c : equipe) {
            double percentualAumento;

            // Verifica a posição (índice) para definir a porcentagem
            if (indice == 0) {
                percentualAumento = 15.0; // Primeiro colaborador (Alice)
            } else if (indice == 1) {
                percentualAumento = 12.0; // Segundo colaborador (Bob)
            } else {
                percentualAumento = 10.0; // Do terceiro em diante (Carlos, etc.)
            }

            c.aumentarSalario(percentualAumento); // Aumento de 10%
            c.executarTarefa();      // Comportamento muda dependendo do objeto real
            c.exibirDados();
            System.out.println();

            indice++;
        }

        System.out.println("=== AUDITORIA DO GERENTE (INTERFACE) ===");
        // 3. Acessando métodos da interface via casting
        // A variável 'gerente' foi declarada como Colaborador, então precisamos garantir
        // que ela seja tratada como Auditavel para usar os métodos específicos.
        if (gerente instanceof Auditavel) {
            Auditavel gerenteAuditavel = (Auditavel) gerente;
            gerenteAuditavel.registrarAtividade("Aprovação do orçamento de TI.");
            gerenteAuditavel.registrarAtividade("Reunião de feedback trimestral.");
            gerenteAuditavel.auditar();
        }

        System.out.println("\n=== ATRIBUIÇÃO DE BÔNUS (CASTING E VISIBILIDADE) ===");
        // 4. Casting para Gerente para acessar o método protegido atribuirBonus()
        if (gerente instanceof Gerente) {
            Gerente g = (Gerente) gerente; 
            g.atribuirBonus(2500.0); // Valor arbitrário
            
            System.out.println("\nDados do Gerente após o bônus:");
            g.exibirDados();
        }
    }
}