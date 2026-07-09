public class ContaCorrente extends Conta {

    public ContaCorrente(String titular, int numero, double saldo) {
        super(titular, numero, saldo);
    }

    @Override
    public void sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Erro: Valor de saque inválido.");
            return;
        }

        if (valor <= getSaldo()) {
            double novoSaldo = getSaldo() - valor;
            setSaldo(novoSaldo);
            System.out.println("Saque de R$ " + valor + " realizado com sucesso na Conta Corrente " + getNumero());
        } else {
            System.out.println("Erro: Saldo insuficiente na Conta Corrente " + getNumero() + " para o saque de R$ " + valor);
        }
    }
}