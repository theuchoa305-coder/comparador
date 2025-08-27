package com.example.comparador.exception;

/**
 * Exceção customizada para erros de negócio.
 * <p>
 * Permite lançar erros controlados que o cliente pode receber de forma clara
 * sem vazar informações internas.
 * </p>
 */
public class BusinessException extends RuntimeException {

    /**
     * Construtor com mensagem de erro.
     *
     * @param message mensagem descritiva do erro de negócio
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * Construtor com mensagem de erro e causa.
     *
     * @param message mensagem descritiva do erro
     * @param cause causa original da exceção
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
