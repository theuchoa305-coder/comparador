package com.example.comparador.mapper;

import org.springframework.stereotype.Component;
import com.example.comparador.dto.ProdutoDTO;
import com.example.comparador.model.Produto;

@Component
public class ProdutoMapper {
    
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
