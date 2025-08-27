package com.example.comparador.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;
    private WebRequest request;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
        request = mock(WebRequest.class);
        when(request.getDescription(false)).thenReturn("/api/test");
    }

    @Test
    void testHandleValidationErrors() throws Exception {
        // Mock do FieldError
        FieldError fieldError1 = new FieldError("objectName", "campo1", "não pode ser nulo");
        FieldError fieldError2 = new FieldError("objectName", "campo2", "deve ser maior que zero");
        List<FieldError> fieldErrors = Arrays.asList(fieldError1, fieldError2);

        // Mock do BindingResult
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        // Mock da exceção
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        // Chamada do handler
        ResponseEntity<ApiError> response = handler.handleValidationErrors(ex, request);

        // Verificações
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiError apiError = response.getBody();
        assertNotNull(apiError);
        assertEquals(HttpStatus.BAD_REQUEST.value(), apiError.getStatus());
        assertEquals("Erro de Validação", apiError.getError());
        assertEquals("Erro na validação dos campos", apiError.getMessage());
        assertEquals("/api/test", apiError.getPath());
        assertEquals(Arrays.asList("campo1: não pode ser nulo", "campo2: deve ser maior que zero"), apiError.getDetails());
    }

  @Test
    void testHandleRuntimeException() {
    RuntimeException ex = new RuntimeException("Erro inesperado");

    ResponseEntity<ApiError> response = handler.handleRuntimeException(ex, request);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    ApiError apiError = response.getBody();
    assertNotNull(apiError);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), apiError.getStatus());
    assertEquals("Erro Interno", apiError.getError());
    // Atualizar a expectativa para a nova mensagem genérica
    assertEquals("Ocorreu um erro inesperado. Tente novamente mais tarde.", apiError.getMessage());
    assertEquals("/api/test", apiError.getPath());
    assertNull(apiError.getDetails());
}

}
