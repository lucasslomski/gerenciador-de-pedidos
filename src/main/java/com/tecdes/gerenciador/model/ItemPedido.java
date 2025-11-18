package com.tecdes.gerenciador.model;

public class ItemPedido {
    private Integer id;
    private Integer pedidoId;
    private Produto produto;
    private Integer quantidade;
    private Double precoUnitario;
    private Double subtotal;
    
    public ItemPedido() {}
    
    public ItemPedido(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco();
        calcularSubtotal();
    }
    
    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Integer getPedidoId() { return pedidoId; }
    public void setPedidoId(Integer pedidoId) { this.pedidoId = pedidoId; }
    
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { 
        this.produto = produto;
        this.precoUnitario = produto.getPreco();
        calcularSubtotal();
    }
    
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { 
        this.quantidade = quantidade; 
        calcularSubtotal();
    }
    
    public Double getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(Double precoUnitario) { 
        this.precoUnitario = precoUnitario; 
        calcularSubtotal();
    }
    
    public Double getSubtotal() { return subtotal; }
    
    private void calcularSubtotal() {
        if (precoUnitario != null && quantidade != null) {
            this.subtotal = precoUnitario * quantidade;
        }
    }
    
    @Override
    public String toString() {
        return String.format("ItemPedido [produto=%s, quantidade=%d, subtotal=R$ %.2f]", 
                           produto.getNome(), quantidade, subtotal);
    }
}