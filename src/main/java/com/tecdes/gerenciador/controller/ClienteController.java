package com.tecdes.gerenciador.controller;

public class ClienteController {
    private IClienteRepository clienteRepository;
    
    public ClienteController() {
        this.clienteRepository = new ClienteRepository();
    }
    
    /**
     * Cadastra um novo cliente com validações
     */
    public Cliente cadastrarCliente(String nome, String telefone, String email) {
        // Validações
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório");
        }
        
        if (email != null && !email.isEmpty()) {
            // Validação simples de email
            if (!email.contains("@")) {
                throw new IllegalArgumentException("Email inválido");
            }
            
            // Verifica se email já existe
            Cliente clienteExistente = clienteRepository.findByEmail(email);
            if (clienteExistente != null) {
                throw new IllegalArgumentException("Já existe um cliente cadastrado com este email");
            }
        }
        
        Cliente cliente = new Cliente(nome.trim(), telefone, email);
        return clienteRepository.save(cliente);
    }
    
    /**
     * Busca cliente por ID
     */
    public Cliente buscarPorId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        
        return clienteRepository.findById(id);
    }
    
    /**
     * Lista todos os clientes
     */
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }
    
    /**
     * Busca clientes por nome
     */
    public List<Cliente> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome para busca é obrigatório");
        }
        
        return clienteRepository.findByNome(nome.trim());
    }
    
    /**
     * Atualiza um cliente existente
     */
    public Cliente atualizarCliente(Integer id, String nome, String telefone, String email) {
        Cliente clienteExistente = clienteRepository.findById(id);
        if (clienteExistente == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        
        // Validação de email único (se foi alterado)
        if (email != null && !email.equals(clienteExistente.getEmail())) {
            Cliente clienteComEmail = clienteRepository.findByEmail(email);
            if (clienteComEmail != null && !clienteComEmail.getId().equals(id)) {
                throw new IllegalArgumentException("Já existe outro cliente com este email");
            }
        }
        
        clienteExistente.setNome(nome);
        clienteExistente.setTelefone(telefone);
        clienteExistente.setEmail(email);
        
        return clienteRepository.update(clienteExistente);
    }
    
    /**
     * Exclui um cliente
     */
    public boolean excluirCliente(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        
        return clienteRepository.delete(id);
    }
}