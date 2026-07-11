package SistemaRelatorioFinanceiro;

/**
 * Classe imutável que representa um relatório financeiro.
 * Uma vez criada, seus dados não podem ser alterados.
 */
public final class Relatorio {
    private final int id;
    private final String descricao;
    private final double valor;

    /**
     * Construtor padrão para inicializar todos os campos obrigatórios.
     */
    public Relatorio(int id, String descricao, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    // Getters
    public int getId() { return id; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }

    @Override
    public String toString() {
        return String.format("Relatório #%d (%s) - R$ %.2f", id, descricao, valor);
    }
}