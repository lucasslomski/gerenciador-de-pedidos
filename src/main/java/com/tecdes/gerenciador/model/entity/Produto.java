package com.tecdes.gerenciador.model;

public class Produto {
    private Integer id;
    private String nome;
    private String descricao;
    private Double preco;
    private String categoria;
    private Boolean ativo;
    
    public Produto() {}
    
    public Produto(String nome, String descricao, Double preco, String categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.ativo = true;
    }
    
    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    
    @Override
    public String toString() {
        return String.format("Produto [id=%d, nome=%s, preco=R$ %.2f]", id, nome, preco);
    }
}