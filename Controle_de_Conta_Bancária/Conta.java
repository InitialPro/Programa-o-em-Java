public abstract class Conta {
    private String titular;
    private int numero;
    private double saldo;

    // Construtor
    public Conta(String titular, int numero, double saldo) {
        this.titular = titular;
        this.numero = numero;
        this.saldo = saldo;
    }

    // Método comum para todas as contas
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Depósito de R$ " + valor + " realizado com sucesso na conta " + numero);
        } else {
            System.out.println("Erro: O valor do depósito deve ser positivo.");
        }
    }

    // Método abstrato - cada subclasse dita sua própria regra
    public abstract void sacar(double valor);

    // Método para exibir informações
    public void exibirInformacoes() {
        System.out.println("------------------------------------");
        System.out.println("Titular: " + titular);
        System.out.println("Número da Conta: " + numero);
        System.out.println("Saldo Atual: R$ " + saldo);
    }

    // Getters e Setters para manter o encapsulamento
    public String getTitular() {
        return titular;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    // Método auxiliar para as subclasses alterarem o saldo após validação interna
    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}