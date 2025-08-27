package com.example.comparador.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe de tratamento global de exceções para a aplicação.
 * <p>
 * Essa classe intercepta exceções lançadas pelos controllers e
 * retorna respostas padronizadas do tipo {@link ApiError}.
 * As exceções inesperadas são registradas no log interno, mas o cliente
 * recebe mensagens genéricas para evitar vazamento de informações sensíveis.
 * </p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Trata exceções de validação de argumentos inválidos (MethodArgumentNotValidException).
     * <p>
     * Constrói uma resposta com status {@link HttpStatus#BAD_REQUEST} (400),
     * contendo detalhes dos campos que falharam na validação.
     * </p>
     *
     * @param ex      a exceção de validação lançada pelo Spring
     * @param request contexto da requisição atual
     * @return {@link ResponseEntity} contendo o {@link ApiError} com detalhes da validação
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de Validação",
                "Erro na validação dos campos",
                request.getDescription(false),
                details
        );

        logger.warn("Erro de validação nos campos: {}", details);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Trata exceções genéricas de tempo de execução (RuntimeException).
     * <p>
     * Constrói uma resposta com status {@link HttpStatus#INTERNAL_SERVER_ERROR} (500),
     * contendo uma mensagem genérica para o cliente. A exceção completa é registrada
     * no log interno para depuração.
     * </p>
     *
     * @param ex      a exceção genérica lançada durante a execução
     * @param request contexto da requisição atual
     * @return {@link ResponseEntity} contendo o {@link ApiError} com mensagem genérica
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException ex, WebRequest request) {
        // Log detalhado para depuração interna
        logger.error("Erro inesperado na aplicação", ex);

        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro Interno",
                "Ocorreu um erro inesperado. Tente novamente mais tarde.",
                request.getDescription(false),
                null
        );

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Trata exceções customizadas de negócio (BusinessException).
     * <p>
     * Permite retornar mensagens controladas para o cliente em casos conhecidos,
     * sem expor detalhes internos.
     * </p>
     *
     * @param ex      a exceção customizada
     * @param request contexto da requisição atual
     * @return {@link ResponseEntity} contendo o {@link ApiError} com a mensagem definida na exceção
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiError> handleBusinessException(BusinessException ex, WebRequest request) {
        logger.warn("Exceção de negócio: {}", ex.getMessage());

        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de Negócio",
                ex.getMessage(),
                request.getDescription(false),
                null
        );

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
