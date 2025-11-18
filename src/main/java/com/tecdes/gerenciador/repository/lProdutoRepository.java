package com.tecdes.gerenciador.repository;

import java.util.List;

/**
 * Interface que define as operações CRUD para Produto
 */
public interface IProdutoRepository {
    Produto save(Produto produto);
    Produto findById(Integer id);
    List<Produto> findAll();
    Produto update(Produto produto);
    boolean delete(Integer id);
}
