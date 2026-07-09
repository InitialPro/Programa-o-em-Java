public class ContaPoupanca extends Conta {

    public ContaPoupanca(String titular, int numero, double saldo) {
        super(titular, numero, saldo);
    }

    @Override
    public void sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Erro: Valor de saque inválido.");
            return;
        }

        // Regra especial: apenas múltiplos de 10
        if (valor % 10 != 0) {
            System.out.println("Erro na Conta Poupança " + getNumero() + ": Saques permitidos apenas em múltiplos de R$ 10 (ex: 20, 50, 100).");
            return;
        }

        if (valor <= getSaldo()) {
            double novoSaldo = getSaldo() - valor;
            setSaldo(novoSaldo);
            System.out.println("Saque de R$ " + valor + " realizado com sucesso na Conta Poupança " + getNumero());
        } else {
            System.out.println("Erro: Saldo insuficiente na Conta Poupança " + getNumero() + " para o saque de R$ " + valor);
        }
    }
}