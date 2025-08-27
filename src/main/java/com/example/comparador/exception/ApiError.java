package com.example.comparador.exception;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Representa uma resposta de erro padronizada da API.
 * <p>
 * Essa classe é usada para encapsular informações sobre erros ocorridos
 * durante o processamento de requisições, incluindo status HTTP, mensagem,
 * detalhes de validação e contexto da requisição.
 * </p>
 */
public class ApiError {

    /** Timestamp do momento em que o erro ocorreu */
    private LocalDateTime timestamp;

    /** Código de status HTTP associado ao erro */
    private int status;

    /** Tipo de erro ou título resumido do erro */
    private String error;

    /** Mensagem detalhada do erro */
    private String message;

    /** Caminho da requisição que gerou o erro */
    private String path;

    /** Lista de detalhes adicionais do erro, como mensagens de validação */
    private List<String> details;

    /**
     * Construtor padrão.
     */
    public ApiError() {
    }

    /**
     * Construtor completo.
     *
     * @param timestamp momento do erro
     * @param status código HTTP do erro
     * @param error título resumido do erro
     * @param message mensagem detalhada do erro
     * @param path caminho da requisição
     * @param details detalhes adicionais do erro
     */
    public ApiError(LocalDateTime timestamp, int status, String error, String message, String path, List<String> details) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    /**
     * Inicia a construção de um {@link ApiError} usando o padrão Builder.
     *
     * @return nova instância do {@link ApiErrorBuilder}
     */
    public static ApiErrorBuilder builder() {
        return new ApiErrorBuilder();
    }

    /**
     * Builder para criação de {@link ApiError} de forma fluente.
     */
    public static class ApiErrorBuilder {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;
        private List<String> details;

        public ApiErrorBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ApiErrorBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ApiErrorBuilder error(String error) {
            this.error = error;
            return this;
        }

        public ApiErrorBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ApiErrorBuilder path(String path) {
            this.path = path;
            return this;
        }

        public ApiErrorBuilder details(List<String> details) {
            this.details = details;
            return this;
        }

        /**
         * Constroi a instância de {@link ApiError} com os valores configurados.
         *
         * @return nova instância de {@link ApiError}
         */
        public ApiError build() {
            return new ApiError(timestamp, status, error, message, path, details);
        }
    }
}
