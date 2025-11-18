class ProdutoRepositoryTest {
    
    private IProdutoRepository repository = new ProdutoRepository();
    
    @Test
    void testSaveAndFindById() {
        // Criar produto de teste
        Produto produto = new Produto();
        produto.setNome("X-Burger Test");
        produto.setDescricao("Hambúrguer com queijo");
        produto.setPreco(15.90);
        produto.setCategoria("Lanches");
        
        // Salvar produto
        Produto savedProduto = repository.save(produto);
        assertNotNull(savedProduto);
        assertNotNull(savedProduto.getId());
        
        // Buscar produto pelo ID
        Produto foundProduto = repository.findById(savedProduto.getId());
        assertNotNull(foundProduto);
        assertEquals(savedProduto.getId(), foundProduto.getId());
        assertEquals("X-Burger Test", foundProduto.getNome());
    }
    
    @Test
    void testFindAll() {
        // Testar listagem de produtos
        var produtos = repository.findAll();
        assertNotNull(produtos);
        // Pode ser vazio se não houver produtos, mas não deve ser null
    }
}