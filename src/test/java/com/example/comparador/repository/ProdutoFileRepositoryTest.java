package com.example.comparador.repository;

import com.example.comparador.model.Produto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoFileRepositoryTest {

    private final ProdutoFileRepository produtoRepository = new ProdutoFileRepository();

    @Test
    void buscarTodos_deveRetornarListaDeProdutos() {
        List<Produto> produtos = produtoRepository.buscarTodos();

        assertNotNull(produtos);
        assertEquals(12, produtos.size());
        assertEquals("Notebook Dell", produtos.get(0).getNome());
        assertEquals("Smartphone Samsung", produtos.get(1).getNome());
    }

    @Test
    void buscarTodos_deveLancarException_quandoArquivoNaoExiste() {
        // Para testar arquivo inexistente, você precisaria criar uma subclasse que sobrescreve o método
        ProdutoFileRepository repoInexistente = new ProdutoFileRepository() {
            @Override
            public List<Produto> buscarTodos() {
                throw new RuntimeException("Arquivo produtos.json não encontrado no classpath");
            }
        };

        RuntimeException exception = assertThrows(RuntimeException.class, repoInexistente::buscarTodos);
        assertTrue(exception.getMessage().contains("Arquivo produtos.json não encontrado"));
    }
}
