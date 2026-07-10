package CacaAoTesouro;

/**
 * Classe de exceção personalizada para lidar com erros de validação das tarefas.
 * Herda de Exception, tornando-a uma exceção "checked" (exige bloco try-catch).
 */
public class TarefaInvalidaException extends Exception {
    
    /**
     * Construtor da exceção.
     * @param mensagem A mensagem de erro que será exibida quando a exceção for lançada.
     */
    public TarefaInvalidaException(String mensagem) {
        super(mensagem);
    }
}