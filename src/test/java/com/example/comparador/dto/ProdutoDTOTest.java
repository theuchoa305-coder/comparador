package com.example.comparador.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProdutoDTOTest {

    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private ProdutoDTO criarProdutoValido() {
        ProdutoDTO produto = new ProdutoDTO();
        produto.setNome("Notebook Dell");
        produto.setImagemUrl("http://exemplo.com/notebook.jpg");
        produto.setDescricao("Um notebook de alto desempenho para uso profissional");
        produto.setPreco(4500.00);
        produto.setClassificacao(4.5);
        produto.setEspecificacoes(new HashMap<>() {{
            put("Processador", "Intel i7");
            put("RAM", "16GB");
        }});
        return produto;
    }

    @Test
    public void deveValidarProdutoCorreto() {
        ProdutoDTO produto = criarProdutoValido();
        Set<ConstraintViolation<ProdutoDTO>> violations = validator.validate(produto);
        assertTrue(violations.isEmpty(), "Produto válido não deve gerar violações");
    }

    @Test
    public void deveValidarNomeObrigatorio() {
        ProdutoDTO produto = criarProdutoValido();
        produto.setNome(""); // inválido

        Set<ConstraintViolation<ProdutoDTO>> violations = validator.validate(produto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("obrigatório")));
    }

    @Test
    public void deveValidarPrecoPositivo() {
        ProdutoDTO produto = criarProdutoValido();
        produto.setPreco(-100.0); // inválido

        Set<ConstraintViolation<ProdutoDTO>> violations = validator.validate(produto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("maior que zero")));
    }

    @Test
    public void deveValidarDescricaoMinima() {
        ProdutoDTO produto = criarProdutoValido();
        produto.setDescricao("curta"); // menos que 10 caracteres

        Set<ConstraintViolation<ProdutoDTO>> violations = validator.validate(produto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("10 e 500")));
    }

    @Test
    public void deveValidarClassificacaoPositivaOuZero() {
        ProdutoDTO produto = criarProdutoValido();
        produto.setClassificacao(-1.0); // inválido

        Set<ConstraintViolation<ProdutoDTO>> violations = validator.validate(produto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("maior ou igual a zero")));
    }
}
