package com.example.comparador.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.comparador.dto.ProdutoDTO;
import com.example.comparador.mapper.ProdutoMapper;
import com.example.comparador.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "API para gerenciamento de produtos")
public class ProdutoController {
    
    private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
    private final ProdutoService service;
    private final ProdutoMapper mapper;

    public ProdutoController(ProdutoService service, ProdutoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    @Operation(
        summary = "Lista todos os produtos",
        description = "Retorna uma lista com todos os produtos disponíveis para comparação"
    )
    public ResponseEntity<List<ProdutoDTO>> listar() {
        try {
            logger.info("Iniciando listagem de produtos");
            List<ProdutoDTO> produtos = service.listarProdutos().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
            logger.info("Produtos carregados com sucesso. Total: {}", produtos.size());
            return ResponseEntity.ok(produtos);
        } catch (Exception e) {
            logger.error("Erro ao carregar produtos: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao carregar produtos: " + e.getMessage());
        }
    }

    @GetMapping("/status")
    @Operation(
        summary = "Verifica o status da API",
        description = "Retorna informações sobre o status atual da API"
    )
    public ResponseEntity<Map<String, String>> status() {
        logger.info("Verificando status da aplicação");
        Map<String, String> response = Map.of(
            "status", "online",
            "timestamp", LocalDateTime.now().toString(),
            "version", "1.0.0"
        );
        logger.info("Status da aplicação: {}", response);
        return ResponseEntity.ok(response);
    }
}
