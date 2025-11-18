package com.tecdes.gerenciador.controller;

public class ProdutoController {
    private IProdutoRepository produtoRepository;
    
    public ProdutoController() {
        this.produtoRepository = new ProdutoRepository();
    }
    
    /**
     * Cadastra um novo produto com validações
     */
    public Produto cadastrarProduto(String nome, String descricao, Double preco, String categoria) {
        // Validações
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório");
        }
        
        if (preco == null || preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        
        Produto produto = new Produto(nome.trim(), descricao, preco, categoria);
        return produtoRepository.save(produto);
    }
    
    /**
     * Busca produto por ID
     */
    public Produto buscarPorId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        
        return produtoRepository.findById(id);
    }
    
    /**
     * Lista todos os produtos ativos
     */
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
    
    /**
     * Atualiza um produto existente
     */
    public Produto atualizarProduto(Integer id, String nome, String descricao, Double preco, String categoria) {
        Produto produtoExistente = produtoRepository.findById(id);
        if (produtoExistente == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        
        produtoExistente.setNome(nome);
        produtoExistente.setDescricao(descricao);
        produtoExistente.setPreco(preco);
        produtoExistente.setCategoria(categoria);
        
        return produtoRepository.update(produtoExistente);
    }
    
    /**
     * Exclui um produto (exclusão lógica)
     */
    public boolean excluirProduto(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        
        return produtoRepository.delete(id);
    }
    
    /**
     * Busca produtos por categoria
     */
    public List<Produto> buscarPorCategoria(String categoria) {
        return produtoRepository.findAll().stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }
}