package com.example.comparador.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApiErrorTest {

    private ApiError apiError;
    private LocalDateTime timestamp;
    private List<String> details;

    @BeforeEach
    void setUp() {
        timestamp = LocalDateTime.now();
        details = Arrays.asList("Erro 1", "Erro 2");

        apiError = new ApiError(timestamp, 400, "Bad Request", "Dados inválidos", "/api/test", details);
    }

    @Test
    void testGetters() {
        assertEquals(timestamp, apiError.getTimestamp());
        assertEquals(400, apiError.getStatus());
        assertEquals("Bad Request", apiError.getError());
        assertEquals("Dados inválidos", apiError.getMessage());
        assertEquals("/api/test", apiError.getPath());
        assertEquals(details, apiError.getDetails());
    }

    @Test
    void testSetters() {
        LocalDateTime newTimestamp = LocalDateTime.now().plusDays(1);
        List<String> newDetails = Arrays.asList("Erro 3");

        apiError.setTimestamp(newTimestamp);
        apiError.setStatus(404);
        apiError.setError("Not Found");
        apiError.setMessage("Recurso não encontrado");
        apiError.setPath("/api/novo");
        apiError.setDetails(newDetails);

        assertEquals(newTimestamp, apiError.getTimestamp());
        assertEquals(404, apiError.getStatus());
        assertEquals("Not Found", apiError.getError());
        assertEquals("Recurso não encontrado", apiError.getMessage());
        assertEquals("/api/novo", apiError.getPath());
        assertEquals(newDetails, apiError.getDetails());
    }

    @Test
    void testBuilder() {
        ApiError builtError = ApiError.builder()
                .timestamp(timestamp)
                .status(500)
                .error("Internal Server Error")
                .message("Falha no servidor")
                .path("/api/error")
                .details(details)
                .build();

        assertEquals(timestamp, builtError.getTimestamp());
        assertEquals(500, builtError.getStatus());
        assertEquals("Internal Server Error", builtError.getError());
        assertEquals("Falha no servidor", builtError.getMessage());
        assertEquals("/api/error", builtError.getPath());
        assertEquals(details, builtError.getDetails());
    }
}
