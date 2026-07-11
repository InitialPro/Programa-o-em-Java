package Caca_Ao_Tesouro;

/**
 * Exceção personalizada (Checked Exception) para validar as missões.
 * Garante que erros de dados não passem despercebidos durante a execução paralela.
 */
public class TarefaInvalidaException extends Exception {
    
    /**
     * Construtor da exceção.
     * 
     * @param mensagem A mensagem de erro que explica o motivo da falha na missão.
     *                 Esta mensagem será resgatada mais tarde pelo Future.get().
     */
    public TarefaInvalidaException(String mensagem) {
        super(mensagem);
    }
}