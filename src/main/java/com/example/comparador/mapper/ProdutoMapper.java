package com.example.comparador.mapper;

import org.springframework.stereotype.Component;
import com.example.comparador.dto.ProdutoDTO;
import com.example.comparador.model.Produto;

/**
 * Mapper responsável por converter entre {@link Produto} e {@link ProdutoDTO}.
 * <p>
 * Essa classe fornece métodos para transformar entidades do modelo em DTOs
 * para exposição em API, e também para transformar DTOs recebidos em entidades
 * para persistência ou processamento interno.
 * </p>
 */
@Component
public class ProdutoMapper {
    
    /**
     * Converte uma entidade {@link Produto} em {@link ProdutoDTO}.
     *
     * @param produto a entidade a ser convertida
     * @return o {@link ProdutoDTO} correspondente ou {@code null} se o parâmetro for {@code null}
     */
    public ProdutoDTO toDTO(Produto produto) {
        if (produto == null) {
            return null;
        }

        ProdutoDTO dto = new ProdutoDTO();
        dto.setNome(produto.getNome());
        dto.setImagemUrl(produto.getImagemUrl());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        dto.setClassificacao(produto.getClassificacao());
        dto.setEspecificacoes(produto.getEspecificacoes());
        return dto;
    }

    /**
     * Converte um {@link ProdutoDTO} em entidade {@link Produto}.
     *
     * @param dto o DTO a ser convertido
     * @return a entidade {@link Produto} correspondente ou {@code null} se o parâmetro for {@code null}
     */
    public Produto toEntity(ProdutoDTO dto) {
        if (dto == null) {
            return null;
        }

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setImagemUrl(dto.getImagemUrl());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setClassificacao(dto.getClassificacao());
        produto.setEspecificacoes(dto.getEspecificacoes());
        return produto;
    }
}
