package com.tecdes.gerenciador.repository;

public interface IClienteRepository {
    Cliente save(Cliente cliente);
    Cliente findById(Integer id);
    List<Cliente> findAll();
    Cliente findByEmail(String email);
    List<Cliente> findByNome(String nome);
    Cliente update(Cliente cliente);
    boolean delete(Integer id);
}