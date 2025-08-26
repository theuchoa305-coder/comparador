package com.example.comparador.model;

import java.util.Map;

public class Produto {
    private String nome;
    private String imagemUrl;
    private String descricao;
    private double preco;
    private double classificacao;
    private Map<String, String> especificacoes;

    public Produto() {
    }

    public Produto(String nome, String imagemUrl, String descricao, double preco, double classificacao,
            Map<String, String> especificacoes) {
        this.nome = nome;
        this.imagemUrl = imagemUrl;
        this.descricao = descricao;
        this.preco = preco;
        this.classificacao = classificacao;
        this.especificacoes = especificacoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public Map<String, String> getEspecificacoes() {
        return especificacoes;
    }

    public void setEspecificacoes(Map<String, String> especificacoes) {
        this.especificacoes = especificacoes;
    }
}
