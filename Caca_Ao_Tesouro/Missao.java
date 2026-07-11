package Caca_Ao_Tesouro;

/**
 * Classe imutável que representa uma missão na ilha.
 * A imutabilidade (uso de 'final') é crucial em ambientes multithread para garantir
 * que nenhuma thread altere os dados da missão enquanto outra está lendo.
 */
public final class Missao {
    private final String id;
    private final String descricao;
    private final int dificuldade;

    /**
     * Construtor único para inicializar a missão. 
     * Como a classe é imutável, esta é a única chance de definir seus valores.
     * 
     * @param id Identificador único da missão.
     * @param descricao O que o explorador fará.
     * @param dificuldade Nível de dificuldade (usado no cálculo de pontos).
     */
    public Missao(String id, String descricao, int dificuldade) {
        this.id = id;
        this.descricao = descricao;
        this.dificuldade = dificuldade;
    }

    /**
     * Retorna o ID da missão.
     * 
     * @return String contendo o ID.
     */
    public String getId() { 
        return id; 
    }

    /**
     * Retorna a descrição detalhada da missão.
     * 
     * @return String contendo o local ou ação a ser feita.
     */
    public String getDescricao() { 
        return descricao; 
    }

    /**
     * Retorna o multiplicador de dificuldade da missão.
     * 
     * @return Número inteiro representando a dificuldade.
     */
    public int getDificuldade() { 
        return dificuldade; 
    }
}