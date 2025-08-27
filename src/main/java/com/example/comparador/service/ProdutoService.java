package com.example.comparador.service;

import com.example.comparador.model.Produto;
import com.example.comparador.repository.ProdutoFileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável por manipular os produtos.
 * <p>
 * Essa classe abstrai a lógica de negócio relacionada a produtos,
 * utilizando o {@link ProdutoFileRepository} para buscar os dados do arquivo JSON.
 * </p>
 */
@Service
public class ProdutoService {

    private final ProdutoFileRepository produtoRepository;

    /**
     * Injeta a dependência do repositório responsável por carregar os produtos do JSON.
     *
     * @param produtoRepository repositório de produtos baseado em arquivo
     */
    public ProdutoService(ProdutoFileRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    /**
     * Lista todos os produtos disponíveis.
     *
     * @return lista de produtos carregados do arquivo JSON
     */
    public List<Produto> listarProdutos() {
        return produtoRepository.buscarTodos();
    }

    /**
     * Busca um produto pelo nome.
     *
     * @param nome nome do produto
     * @return produto correspondente ou null caso não encontrado
     */
    public Produto buscarPorNome(String nome) {
        return listarProdutos()
                .stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }
}
