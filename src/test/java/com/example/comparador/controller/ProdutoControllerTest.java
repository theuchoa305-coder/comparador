package com.example.comparador.controller;

import com.example.comparador.dto.ProdutoDTO;
import com.example.comparador.mapper.ProdutoMapper;
import com.example.comparador.model.Produto;
import com.example.comparador.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService service;

    @MockBean
    private ProdutoMapper mapper;

    @Test
    void deveRetornarListaDeProdutos() throws Exception {
        // Arrange (entidade simulada)
        Produto produtoEntity = new Produto();
        produtoEntity.setNome("Notebook Dell");

        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Notebook Dell");
        produtoDTO.setImagemUrl("http://exemplo.com/notebook.jpg");
        produtoDTO.setDescricao("Um notebook de alto desempenho para uso profissional");
        produtoDTO.setPreco(4500.0);
        produtoDTO.setClassificacao(4.7);
        produtoDTO.setEspecificacoes(Map.of("RAM", "16GB", "Processador", "Intel i7"));

        when(service.listarProdutos()).thenReturn(List.of(produtoEntity));
        when(mapper.toDTO(Mockito.any())).thenReturn(produtoDTO);

        // Act + Assert
        mockMvc.perform(get("/api/v1/produtos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nome", is("Notebook Dell")))
                .andExpect(jsonPath("$[0].preco", is(4500.0)))
                .andExpect(jsonPath("$[0].especificacoes.RAM", is("16GB")));
    }

    @Test
    void deveRetornarErroAoListarProdutos() throws Exception {
        // Arrange - Simula erro no service
        when(service.listarProdutos()).thenThrow(new RuntimeException("Falha no banco de dados"));

        // Act + Assert
        mockMvc.perform(get("/api/v1/produtos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError()); // 500
    }

    @Test
    void deveRetornarStatusDaApi() throws Exception {
        mockMvc.perform(get("/api/v1/produtos/status")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("online")))
                .andExpect(jsonPath("$.version", is("1.0.0")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }
}
