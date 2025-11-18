class ClienteRepositoryTest {
    
    private IClienteRepository repository = new ClienteRepository();
    
    @Test
    void testSaveAndFindCliente() {
        // Criar cliente de teste
        Cliente cliente = new Cliente();
        cliente.setNome("Teste Cliente Repository");
        cliente.setTelefone("11999999999");
        cliente.setEmail("teste.repository@email.com");
        
        // Salvar cliente
        Cliente savedCliente = repository.save(cliente);
        assertNotNull(savedCliente);
        assertNotNull(savedCliente.getId());
        
        // Buscar cliente pelo ID
        Cliente foundCliente = repository.findById(savedCliente.getId());
        assertNotNull(foundCliente);
        assertEquals(savedCliente.getId(), foundCliente.getId());
        assertEquals("Teste Cliente Repository", foundCliente.getNome());
        assertEquals("teste.repository@email.com", foundCliente.getEmail());
    }
    
    @Test
    void testFindByEmail() {
        Cliente cliente = repository.findByEmail("teste.repository@email.com");
        assertNotNull(cliente);
        assertEquals("Teste Cliente Repository", cliente.getNome());
    }
    
    @Test
    void testFindAll() {
        var clientes = repository.findAll();
        assertNotNull(clientes);
        // Pode ser vazio se não houver clientes, mas não deve ser null
    }
    
    @Test
    void testUpdateCliente() {
        // Primeiro busca um cliente existente
        var clientes = repository.findAll();
        if (!clientes.isEmpty()) {
            Cliente cliente = clientes.get(0);
            String novoNome = cliente.getNome() + " Atualizado";
            cliente.setNome(novoNome);
            
            Cliente updated = repository.update(cliente);
            assertNotNull(updated);
            assertEquals(novoNome, updated.getNome());
        }
    }
}