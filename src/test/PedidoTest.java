class PedidoTest {
    
    @Test
    void testCriacaoPedido() {
        Cliente cliente = new Cliente("João Silva", "11999999999", "joao@email.com");
        Pedido pedido = new Pedido(cliente);
        
        assertNotNull(pedido);
        assertEquals(cliente, pedido.getCliente());
        assertEquals(StatusPedido.EM_PRODUCAO, pedido.getStatus());
        assertEquals(0.0, pedido.getTotal());
        assertTrue(pedido.getItens().isEmpty());
    }
    
    @Test
    void testAdicionarItemPedido() {
        Cliente cliente = new Cliente("Maria Santos", "11888888888", "maria@email.com");
        Pedido pedido = new Pedido(cliente);
        
        Produto produto = new Produto("X-Burger", "Hambúrguer com queijo", 15.90, "Lanches");
        produto.setId(1);
        
        ItemPedido item = new ItemPedido(produto, 2);
        pedido.adicionarItem(item);
        
        assertEquals(1, pedido.getItens().size());
        assertEquals(31.80, pedido.getTotal());
        assertEquals(produto, pedido.getItens().get(0).getProduto());
    }
    
    @Test
    void testCalcularTotalMultiplosItens() {
        Cliente cliente = new Cliente("Carlos Oliveira", "11777777777", "carlos@email.com");
        Pedido pedido = new Pedido(cliente);
        
        Produto produto1 = new Produto("X-Burger", "Hambúrguer com queijo", 15.90, "Lanches");
        produto1.setId(1);
        
        Produto produto2 = new Produto("Refrigerante", "Lata 350ml", 5.00, "Bebidas");
        produto2.setId(2);
        
        pedido.adicionarItem(new ItemPedido(produto1, 2));
        pedido.adicionarItem(new ItemPedido(produto2, 1));
        
        assertEquals(36.80, pedido.getTotal());
    }
    
    @Test
    void testStatusPedido() {
        Cliente cliente = new Cliente("Ana Costa", "11666666666", "ana@email.com");
        Pedido pedido = new Pedido(cliente);
        
        // Inicia em produção
        assertEquals(StatusPedido.EM_PRODUCAO, pedido.getStatus());
        assertFalse(pedido.isConcluido());
        
        // Altera status
        pedido.setStatus(StatusPedido.PRONTO);
        assertEquals(StatusPedido.PRONTO, pedido.getStatus());
        
        pedido.setStatus(StatusPedido.CONCLUIDO);
        assertTrue(pedido.isConcluido());
    }
}