package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Endereco;
import br.com.fiap.conexoes.ConexaoFactory;

public class EnderecoDAO {
    private Connection connection;

    // Construtor para inicializar a conexão com o banco de dados
    public EnderecoDAO(Connection connection) throws ClassNotFoundException, SQLException {
        this.connection = new ConexaoFactory().conexao();
    }

    // Método para inserir um endereço no banco de dados
    public void inserir(Endereco endereco) throws SQLException {
        String sql = "INSERT INTO ENDERECO (cep, logradouro, numero, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, endereco.getCep());
            stmt.setString(2, endereco.getLogradouro());
            stmt.setInt(3, endereco.getNumero());
            stmt.setString(4, endereco.getEstado());
            stmt.executeUpdate();
        }
    }

    // Método para atualizar um endereço no banco de dados
    public void atualizar(Endereco endereco) throws SQLException {
        String sql = "UPDATE ENDERECO SET logradouro = ?, numero = ?, estado = ? WHERE cep = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, endereco.getLogradouro());
            stmt.setInt(2, endereco.getNumero());
            stmt.setString(3, endereco.getEstado());
            stmt.setString(4, endereco.getCep());
            stmt.executeUpdate();
        }
    }

    // Método para buscar um endereço no banco de dados por cep
    public Endereco buscarPorCep(String cep) throws SQLException {
        String sql = "SELECT * FROM ENDERECO WHERE cep = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cep);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Endereco(
                        rs.getString("cep"),
                        rs.getString("logradouro"),
                        rs.getInt("numero"),
                        null, // Cidade não está na tabela
                        rs.getString("estado")
                    );
                }
            }
        }
        return null;
    }

    // Método para deletar um endereço no banco de dados por cep
    public void deletarPorCep(String cep) throws SQLException {
        String sql = "DELETE FROM ENDERECO WHERE cep = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cep);
            stmt.executeUpdate();
        }
    }

    // Método para listar todos os endereços do banco de dados
    public List<Endereco> listarTodos() throws SQLException {
        List<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM ENDERECO";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                enderecos.add(new Endereco(
                    rs.getString("cep"),
                    rs.getString("logradouro"),
                    rs.getInt("numero"),
                    null, // Cidade não está na tabela
                    rs.getString("estado")
                ));
            }
        }
        return enderecos;
    }
}
