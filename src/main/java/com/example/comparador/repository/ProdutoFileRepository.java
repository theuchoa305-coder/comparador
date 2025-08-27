package com.example.comparador.repository;

import com.example.comparador.model.Produto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;

/**
 * Repositório responsável por acessar os dados dos produtos a partir de um arquivo JSON.
 * <p>
 * Essa classe isola a lógica de leitura do arquivo "produtos.json" no classpath,
 * convertendo-o em uma lista de objetos {@link Produto}.
 * </p>
 */
@Repository
public class ProdutoFileRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoFileRepository.class);

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Lê o arquivo "produtos.json" do classpath e converte para uma lista de produtos.
     *
     * @return lista de produtos
     */
    public List<Produto> buscarTodos() {
        logger.debug("Lendo arquivo produtos.json");

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("produtos.json")) {
            if (inputStream == null) {
                logger.error("Arquivo produtos.json não encontrado no classpath");
                throw new RuntimeException("Arquivo produtos.json não encontrado no classpath");
            }
            return mapper.readValue(inputStream, new TypeReference<List<Produto>>() {});
        } catch (Exception e) {
            logger.error("Erro ao carregar produtos: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao carregar produtos: " + e.getMessage(), e);
        }
    }
}
