package com.tecdes.gerenciador.repository;

public class ClienteRepository implements IClienteRepository {
    
    @Override
    public Cliente save(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, telefone, email, data_cadastro) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cliente.setId(generatedKeys.getInt(1));
                    }
                }
            }
            
            return cliente;
            
        } catch (SQLException e) {
            System.err.println("Erro ao salvar cliente: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public Cliente findById(Integer id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToCliente(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente: " + e.getMessage());
        }
        
        return null;
    }
    
    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente ORDER BY nome";
        
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                clientes.add(mapResultSetToCliente(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
        }
        
        return clientes;
    }
    
    @Override
    public Cliente findByEmail(String email) {
        String sql = "SELECT * FROM cliente WHERE email = ?";
        
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToCliente(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente por email: " + e.getMessage());
        }
        
        return null;
    }
    
    @Override
    public List<Cliente> findByNome(String nome) {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nome LIKE ? ORDER BY nome";
        
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                clientes.add(mapResultSetToCliente(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar clientes por nome: " + e.getMessage());
        }
        
        return clientes;
    }
    
    @Override
    public Cliente update(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, telefone = ?, email = ? WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setInt(4, cliente.getId());
            
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0 ? cliente : null;
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao deletar cliente: " + e.getMessage());
            return false;
        }
    }
    
    private Cliente mapResultSetToCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setEmail(rs.getString("email"));
        
        Timestamp timestamp = rs.getTimestamp("data_cadastro");
        if (timestamp != null) {
            cliente.setDataCadastro(timestamp.toLocalDateTime());
        }
        
        return cliente;
    }
}