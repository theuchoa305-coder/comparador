package com.example.comparador.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    private Produto produto;
    private Map<String, String> especificacoes;

    @BeforeEach
    void setUp() {
        especificacoes = new HashMap<>();
        especificacoes.put("Cor", "Preto");
        especificacoes.put("Tamanho", "M");

        produto = new Produto("Camiseta", "http://imagem.com/camiseta.jpg", 
                              "Camiseta de algodão", 99.90, 4.5, especificacoes);
    }

    @Test
    void testGetNome() {
        assertEquals("Camiseta", produto.getNome());
    }

    @Test
    void testSetNome() {
        produto.setNome("Calça");
        assertEquals("Calça", produto.getNome());
    }

    @Test
    void testGetImagemUrl() {
        assertEquals("http://imagem.com/camiseta.jpg", produto.getImagemUrl());
    }

    @Test
    void testSetImagemUrl() {
        produto.setImagemUrl("http://imagem.com/calca.jpg");
        assertEquals("http://imagem.com/calca.jpg", produto.getImagemUrl());
    }

    @Test
    void testGetDescricao() {
        assertEquals("Camiseta de algodão", produto.getDescricao());
    }

    @Test
    void testSetDescricao() {
        produto.setDescricao("Calça jeans");
        assertEquals("Calça jeans", produto.getDescricao());
    }

    @Test
    void testGetPreco() {
        assertEquals(99.90, produto.getPreco());
    }

    @Test
    void testSetPreco() {
        produto.setPreco(149.90);
        assertEquals(149.90, produto.getPreco());
    }

    @Test
    void testGetClassificacao() {
        assertEquals(4.5, produto.getClassificacao());
    }

    @Test
    void testSetClassificacao() {
        produto.setClassificacao(3.8);
        assertEquals(3.8, produto.getClassificacao());
    }

    @Test
    void testGetEspecificacoes() {
        assertEquals(especificacoes, produto.getEspecificacoes());
    }

    @Test
    void testSetEspecificacoes() {
        Map<String, String> novasEspecificacoes = new HashMap<>();
        novasEspecificacoes.put("Material", "Algodão");
        produto.setEspecificacoes(novasEspecificacoes);
        assertEquals(novasEspecificacoes, produto.getEspecificacoes());
    }
}
