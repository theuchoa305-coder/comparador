package com.example.comparador.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.comparador.dto.ProdutoDTO;
import com.example.comparador.mapper.ProdutoMapper;
import com.example.comparador.model.Produto;
import com.example.comparador.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller responsável por expor endpoints da API relacionados a produtos.
 * <p>
 * Essa classe fornece métodos para listar produtos e verificar o status da API.
 * Utiliza {@link ProdutoService} para acesso aos dados e {@link ProdutoMapper}
 * para conversão de {@link com.example.comparador.model.Produto} para {@link ProdutoDTO}.
 * </p>
 */
@RestController
@RequestMapping("/api/v1/produtos")
@Tag(name = "Produtos", description = "API para gerenciamento de produtos")
public class ProdutoController {
    
    private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
    private final ProdutoService service;
    private final ProdutoMapper mapper;

    /**
     * Construtor da controller.
     *
     * @param service serviço responsável pelo acesso e manipulação dos produtos
     * @param mapper  responsável pela conversão de entidades para DTOs
     */
    public ProdutoController(ProdutoService service, ProdutoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Lista todos os produtos disponíveis para comparação.
     *
     * @return {@link ResponseEntity} contendo a lista de produtos em JSON
     */
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

    /**
     * Busca um produto específico pelo nome.
     *
     * @param nome nome do produto
     * @return {@link ResponseEntity} com o produto encontrado ou 404 se não existir
     */
    @GetMapping("/{nome}")
    @Operation(
        summary = "Busca produto por nome",
        description = "Retorna os detalhes de um produto específico pelo nome"
    )
    public ResponseEntity<ProdutoDTO> buscarPorNome(@PathVariable String nome) {
        logger.info("Buscando produto pelo nome: {}", nome);
        Produto produto = service.buscarPorNome(nome);
        if (produto == null) {
            logger.warn("Produto não encontrado: {}", nome);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.toDTO(produto));
    }

    /**
     * Retorna informações sobre o status atual da API.
     *
     * @return {@link ResponseEntity} contendo um {@link Map} com informações de status
     */
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
