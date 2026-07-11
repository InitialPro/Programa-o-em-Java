package Simulador_De_Pedidos;

public final class Pedido {
    // Atributos finais garantem que não podem ser modificados após a inicialização
    private final int id;
    private final String descricao;
    private final double valor;

    /**
     * Construtor que inicializa o estado do objeto imutável.
     */
    public Pedido(int id, String descricao, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    // Apenas Getters (sem Setters, preservando a imutabilidade)
    public int getId() { return id; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }

    @Override
    public String toString() {
        return String.format("Pedido #%d (%s) - R$ %.2f", id, descricao, valor);
    }
}