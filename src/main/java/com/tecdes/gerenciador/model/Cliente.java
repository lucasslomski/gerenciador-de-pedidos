package com.tecdes.gerenciador.model;

public class Cliente {
    private Integer id;
    private String nome;
    private String telefone;
    private String email;
    private LocalDateTime dataCadastro;
    
    public Cliente() {}
    
    public Cliente(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataCadastro = LocalDateTime.now();
    }
    
    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
    
    @Override
    public String toString() {
        return String.format("Cliente [id=%d, nome=%s, telefone=%s]", id, nome, telefone);
    }
}