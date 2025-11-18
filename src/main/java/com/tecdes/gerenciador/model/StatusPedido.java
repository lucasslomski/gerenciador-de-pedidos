package com.tecdes.gerenciador.model;

public enum StatusPedido {
    EM_PRODUCAO("Em Produção"),
    PRONTO("Pronto"),
    ENTREGUE("Entregue"),
    CONCLUIDO("Concluído");
    
    private final String descricao;
    
    StatusPedido(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
}