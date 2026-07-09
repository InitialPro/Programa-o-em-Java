package Os_Guardioes_de_Eldoria_A_Saga_Continua;

import java.util.Objects;

public abstract class Personagem {
    private String nome;
    private String classe;
    private int nivel;
    private int pontosDeVida;
    private double poderBase;

    public Personagem(String nome, String classe, int nivel, int pontosDeVida, double poderBase) {
        this.nome = nome;
        this.classe = classe;
        setNivel(nivel);
        setPontosDeVida(pontosDeVida);
        this.poderBase = poderBase;
    }

    public abstract void usarHabilidadeEspecial();

    // Retornamos ao formato em bloco para bater com o exemplo de saída do Nível Mestre
    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
               "Classe: " + classe + "\n" +
               "Nível: " + nivel + "\n" +
               "Pontos de Vida: " + pontosDeVida + "\n" +
               "Poder Base: " + poderBase;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Personagem outro = (Personagem) obj;
        return Objects.equals(nome, outro.nome) && Objects.equals(classe, outro.classe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, classe);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getClasse() { return classe; }
    public void setClasse(String classe) { this.classe = classe; }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { 
        if (nivel < 0) {
            throw new IllegalArgumentException("O nível não pode ser negativo!");
        }
        this.nivel = nivel; 
    }

    public int getPontosDeVida() { return pontosDeVida; }
    public void setPontosDeVida(int pontosDeVida) { 
        if (pontosDeVida < 0) {
            throw new IllegalArgumentException("Os pontos de vida não podem ser negativos!");
        }
        this.pontosDeVida = pontosDeVida; 
    }

    public double getPoderBase() { return poderBase; }
    public void setPoderBase(double poderBase) { this.poderBase = poderBase; }
}