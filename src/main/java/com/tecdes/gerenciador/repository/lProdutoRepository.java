package com.tecdes.gerenciador.repository;

import com.tecdes.gerenciador.model.Produto;
import com.tecdes.gerenciador.repository.IProdutoRepository;
import java.util.List;
import java.util.ArrayList;

public interface IProdutoRepository {
    Produto save(Produto produto);
    Produto findById(Integer id);
    List<Produto> findAll();
    Produto update(Produto produto);
    boolean delete(Integer id);
}
