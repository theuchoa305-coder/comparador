package com.example.comparador.service;

import com.example.comparador.model.Produto;
import com.example.comparador.repository.ProdutoFileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    private ProdutoFileRepository produtoRepository;
    private ProdutoService produtoService;

    private Produto produto1;
    private Produto produto2;

    @BeforeEach
    void setUp() {
        // Cria mock do repositório
        produtoRepository = Mockito.mock(ProdutoFileRepository.class);
        
        // Inicializa o serviço com o mock
        produtoService = new ProdutoService(produtoRepository);
        
        // Cria alguns produtos de teste
        produto1 = new Produto();
        produto1.setNome("Arroz");

        produto2 = new Produto();
        produto2.setNome("Feijão");
    }

    @Test
    void listarProdutos_deveRetornarTodosProdutos() {
        // Configura o mock para retornar uma lista
        List<Produto> produtosMock = Arrays.asList(produto1, produto2);
        when(produtoRepository.buscarTodos()).thenReturn(produtosMock);

        List<Produto> resultado = produtoService.listarProdutos();

        // Verifica o resultado
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(produto1));
        assertTrue(resultado.contains(produto2));

        // Verifica se o método do repositório foi chamado
        verify(produtoRepository, times(1)).buscarTodos();
    }

    @Test
    void buscarPorNome_deveRetornarProdutoQuandoExistir() {
        // Mock do repositório
        when(produtoRepository.buscarTodos()).thenReturn(Arrays.asList(produto1, produto2));

        Produto resultado = produtoService.buscarPorNome("Feijão");

        assertNotNull(resultado);
        assertEquals("Feijão", resultado.getNome());
    }

    @Test
    void buscarPorNome_deveRetornarNullQuandoNaoExistir() {
        // Mock do repositório
        when(produtoRepository.buscarTodos()).thenReturn(Arrays.asList(produto1, produto2));

        Produto resultado = produtoService.buscarPorNome("Macarrão");

        assertNull(resultado);
    }
}
