package com.example.comparador.service;

import com.example.comparador.model.Produto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class ProdutoService {
    private final ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(ProdutoService.class);
    
    public List<Produto> listarProdutos() throws Exception {
        logger.debug("Iniciando carregamento do arquivo produtos.json");
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("produtos.json")) {
            if (inputStream == null) {
                logger.error("Arquivo produtos.json não encontrado no classpath");
                throw new RuntimeException("Arquivo produtos.json não encontrado no classpath");
            }
            try {
                List<Produto> produtos = mapper.readValue(inputStream, new TypeReference<List<Produto>>() {});
                logger.debug("Arquivo produtos.json carregado com sucesso. {} produtos encontrados", produtos.size());
                return produtos;
            } catch (Exception e) {
                logger.error("Erro ao fazer parse do arquivo produtos.json: {}", e.getMessage(), e);
                throw new RuntimeException("Erro ao fazer parse do arquivo produtos.json: " + e.getMessage(), e);
            }
        } catch (Exception e) {
            logger.error("Erro ao ler arquivo produtos.json: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao ler arquivo produtos.json: " + e.getMessage(), e);
        }
    }
}
