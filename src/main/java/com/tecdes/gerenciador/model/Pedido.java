package com.tecdes.gerenciador.model;

public class Pedido {
    private Integer id;
    private Cliente cliente;
    private LocalDateTime dataPedido;
    private StatusPedido status;
    private Double total;
    private List<ItemPedido> itens;
    
    public Pedido() {
        this.itens = new ArrayList<>();
        this.dataPedido = LocalDateTime.now();
        this.status = StatusPedido.EM_PRODUCAO;
        this.total = 0.0;
    }
    
    public Pedido(Cliente cliente) {
        this();
        this.cliente = cliente;
    }
    
    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    
    public LocalDateTime getDataPedido() { return dataPedido; }
    public void setDataPedido(LocalDateTime dataPedido) { this.dataPedido = dataPedido; }
    
    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }
    
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    
    public List<ItemPedido> getItens() { return itens; }
    public void setItens(List<ItemPedido> itens) { 
        this.itens = itens; 
        calcularTotal();
    }
    
    // Métodos de negócio
    public void adicionarItem(ItemPedido item) {
        item.setPedidoId(this.id);
        this.itens.add(item);
        calcularTotal();
    }
    
    public void removerItem(ItemPedido item) {
        this.itens.remove(item);
        calcularTotal();
    }
    
    public void calcularTotal() {
        this.total = itens.stream()
                .mapToDouble(ItemPedido::getSubtotal)
                .sum();
    }
    
    public boolean isConcluido() {
        return status == StatusPedido.CONCLUIDO;
    }
    
    @Override
    public String toString() {
        return String.format("Pedido [id=%d, cliente=%s, status=%s, total=R$ %.2f]", 
                           id, cliente.getNome(), status.getDescricao(), total);
    }
}