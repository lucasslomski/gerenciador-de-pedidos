package com.tecdes.gerenciador.model.dao;

public class ProdutoDAO {
    private int codProduto;
    private int nomeProduto;
    private double precoProduto;


    public ProdutoDAO(){}

    //Construtor
    public ProdutoDAO(int codProduto, int nomeProduto, double precoProduto) {
        this.codProduto = codProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
    }

    //Getters e Setters

    public int getCodProduto() {
        return codProduto;
    }
    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }
    public int getNomeProduto() {
        return nomeProduto;
    }
    public void setNomeProduto(int nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    public double getPrecoProduto() {
        return precoProduto;
    }
    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }
}
