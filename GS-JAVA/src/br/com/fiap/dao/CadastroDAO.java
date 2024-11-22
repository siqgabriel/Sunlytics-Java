package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Cadastro;
import br.com.fiap.beans.Endereco;
import br.com.fiap.conexoes.ConexaoFactory;

public class CadastroDAO {
    private Connection connection;

    public CadastroDAO(Connection connection) throws ClassNotFoundException, SQLException {
        this.connection = new ConexaoFactory().conexao();
    }

    // Inserção do Cadastro completo (Usuário e Endereço)
    public void inserir(Cadastro cadastro) throws SQLException {
        // Inserir o usuário e obter o ID gerado
        String sqlUsuario = "INSERT INTO USUARIO (nome, email, senha) VALUES (?, ?, ?)";
        try (PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario, new String[]{"id_usuario"})) {
            stmtUsuario.setString(1, cadastro.getNome());
            stmtUsuario.setString(2, cadastro.getEmail());
            stmtUsuario.setString(3, cadastro.getSenha());
            stmtUsuario.executeUpdate();

            // Recuperar o ID gerado
            try (ResultSet rs = stmtUsuario.getGeneratedKeys()) {
                if (rs.next()) {
                    int idUsuario = rs.getInt(1);

                    // Inserir o endereço associado ao ID do usuário
                    String sqlEndereco = "INSERT INTO ENDERECO (id_usuario, cep, logradouro, numero, estado) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement stmtEndereco = connection.prepareStatement(sqlEndereco)) {
                        stmtEndereco.setInt(1, idUsuario);
                        stmtEndereco.setString(2, cadastro.getEndereco().getCep());
                        stmtEndereco.setString(3, cadastro.getEndereco().getLogradouro());
                        stmtEndereco.setInt(4, cadastro.getEndereco().getNumero());
                        stmtEndereco.setString(5, cadastro.getEndereco().getEstado());
                        stmtEndereco.executeUpdate();
                    }
                } else {
                    throw new SQLException("Erro ao obter ID do usuário.");
                }
            }
        }
    }

    // Buscar Cadastro por email
    public Cadastro buscarPorEmail(String email) throws SQLException {
        String sql = "SELECT u.id_usuario, u.nome, u.email, u.senha, e.cep, e.logradouro, e.numero, e.estado "
                   + "FROM USUARIO u "
                   + "LEFT JOIN ENDERECO e ON u.id_usuario = e.id_usuario "
                   + "WHERE u.email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Endereco endereco = new Endereco(
                        rs.getString("cep"),
                        rs.getString("logradouro"),
                        rs.getInt("numero"),
                        null,
                        rs.getString("estado")
                    );
                    return new Cadastro(
                        rs.getString("nome"),
                        endereco,
                        rs.getString("email"),
                        rs.getString("senha")
                    );
                }
            }
        }
        return null;
    }

    // Deletar o Cadastro completo (Usuário e Endereço)
    public void deletarPorEmail(String email) throws SQLException {
        Cadastro cadastro = buscarPorEmail(email);
        if (cadastro != null) {
            String sqlEndereco = "DELETE FROM ENDERECO WHERE id_usuario = (SELECT id_usuario FROM USUARIO WHERE email = ?)";
            try (PreparedStatement stmtEndereco = connection.prepareStatement(sqlEndereco)) {
                stmtEndereco.setString(1, email);
                stmtEndereco.executeUpdate();
            }

            String sqlUsuario = "DELETE FROM USUARIO WHERE email = ?";
            try (PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario)) {
                stmtUsuario.setString(1, email);
                stmtUsuario.executeUpdate();
            }
        }
    }

    // Listar todos os cadastros
    public List<Cadastro> listarTodos() throws SQLException {
        List<Cadastro> cadastros = new ArrayList<>();
        String sql = "SELECT u.id_usuario, u.nome, u.email, u.senha, e.cep, e.logradouro, e.numero, e.estado "
                   + "FROM USUARIO u "
                   + "LEFT JOIN ENDERECO e ON u.id_usuario = e.id_usuario";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Endereco endereco = new Endereco(
                    rs.getString("cep"),
                    rs.getString("logradouro"),
                    rs.getInt("numero"),
                    null,
                    rs.getString("estado")
                );
                cadastros.add(new Cadastro(
                    rs.getString("nome"),
                    endereco,
                    rs.getString("email"),
                    rs.getString("senha")
                ));
            }
        }
        return cadastros;
    }
}
