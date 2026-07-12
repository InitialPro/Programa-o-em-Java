package Super_Trunfo;

/**
 * Classe que representa a entidade Aluno (nossa carta do Super Trunfo).
 */
public class Aluno {
    private String matricula;
    private String nome;
    private int anoEntrada;
    private String raridade;

    // Construtor vazio
    public Aluno() {}

    /**
     * Construtor com parâmetros. 
     * A raridade é calculada automaticamente com base na letra inicial da matrícula.
     */
    public Aluno(String matricula, String nome, int anoEntrada) {
        this.matricula = matricula;
        this.nome = nome;
        this.anoEntrada = anoEntrada;
        this.raridade = calcularRaridade(matricula);
    }

    /**
     * Método que calcula a raridade da carta baseado na primeira letra da matrícula.
     * Regra: A = Lendária, B = Épica, C = Rara, Outras = Comum.
     */
    private String calcularRaridade(String matricula) {
        if (matricula == null || matricula.isEmpty()) return "Comum";
        
        char inicial = matricula.toUpperCase().charAt(0);
        switch (inicial) {
            case 'A': return "Lendária";
            case 'B': return "Épica";
            case 'C': return "Rara";
            default:  return "Comum";
        }
    }

    // Getters e Setters
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { 
        this.matricula = matricula; 
        this.raridade = calcularRaridade(matricula);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getAnoEntrada() { return anoEntrada; }
    public void setAnoEntrada(int anoEntrada) { this.anoEntrada = anoEntrada; }

    public String getRaridade() { return raridade; }
    public void setRaridade(String raridade) { this.raridade = raridade; }
}