package com.example.comparador.mapper;

import com.example.comparador.dto.ProdutoDTO;
import com.example.comparador.model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoMapperTest {

    private ProdutoMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ProdutoMapper();
    }

    @Test
    void deveConverterEntityParaDTO() {
        Produto produto = new Produto();
        produto.setNome("Notebook Dell");
        produto.setImagemUrl("http://exemplo.com/notebook.jpg");
        produto.setDescricao("Notebook de alto desempenho");
        produto.setPreco(4500.0);
        produto.setClassificacao(4.8);
        produto.setEspecificacoes(Map.of("RAM", "16GB", "Processador", "Intel i7"));

        ProdutoDTO dto = mapper.toDTO(produto);

        assertNotNull(dto);
        assertEquals("Notebook Dell", dto.getNome());
        assertEquals("http://exemplo.com/notebook.jpg", dto.getImagemUrl());
        assertEquals("Notebook de alto desempenho", dto.getDescricao());
        assertEquals(4500.0, dto.getPreco());
        assertEquals(4.8, dto.getClassificacao());
        assertEquals("16GB", dto.getEspecificacoes().get("RAM"));
    }

    @Test
    void deveConverterDTOParaEntity() {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setNome("Smartphone Samsung");
        dto.setImagemUrl("http://exemplo.com/smartphone.jpg");
        dto.setDescricao("Celular com câmera tripla");
        dto.setPreco(2500.0);
        dto.setClassificacao(4.5);
        dto.setEspecificacoes(Map.of("Tela", "6.5 polegadas", "Bateria", "5000mAh"));

        Produto entity = mapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals("Smartphone Samsung", entity.getNome());
        assertEquals("http://exemplo.com/smartphone.jpg", entity.getImagemUrl());
        assertEquals("Celular com câmera tripla", entity.getDescricao());
        assertEquals(2500.0, entity.getPreco());
        assertEquals(4.5, entity.getClassificacao());
        assertEquals("5000mAh", entity.getEspecificacoes().get("Bateria"));
    }

    @Test
    void deveRetornarNullQuandoEntradaForNull() {
        assertNull(mapper.toDTO(null));
        assertNull(mapper.toEntity(null));
    }
}
