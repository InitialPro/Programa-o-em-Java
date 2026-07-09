// Importação necessária para a interface Comparable funcionar nativamente
public abstract class Personagem implements Comparable<Personagem> {
    
    protected String nome;
    protected String classe;
    protected int nivel;
    protected int pontosDeVida;
    protected double poderBase;

    public Personagem(String nome, String classe, int nivel, int pontosDeVida, double poderBase) {
        this.nome = nome;
        this.classe = classe;
        this.nivel = nivel;
        this.pontosDeVida = pontosDeVida;
        this.poderBase = poderBase;
    }

    // --- GETTERS NECESSÁRIOS PARA A LÓGICA DE COMBATE ---
    public String getNome() { return nome; }
    public String getClasse() { return classe; }
    public int getNivel() { return nivel; }
    public double getPoderBase() { return poderBase; }

    public void exibirStatus() {
        System.out.println("Nome: " + nome + " | Classe: " + classe + " | Nível: " + nivel);
    }

    public abstract void usarHabilidadeEspecial();

    // --- DESAFIO EXTRA: Ensinando o Java a ordenar os personagens pelo Nível ---
    @Override
    public int compareTo(Personagem outroPersonagem) {
        // Ordena de forma decrescente (do maior nível para o menor)
        return Integer.compare(outroPersonagem.getNivel(), this.nivel);
    }
}