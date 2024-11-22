package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Usuario;

public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    // Atualizar senha do usuário
    public void atualizarSenha(Usuario usuario) throws SQLException {
        String sql = "UPDATE USUARIO SET senha = ? WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getSenha());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();
        }
    }

    // Atualizar nome do usuário
    public void atualizarNome(Usuario usuario) throws SQLException {
        String sql = "UPDATE USUARIO SET nome = ? WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();
        }
    }

    // Atualizar e-mail do usuário
    public void atualizarEmail(Usuario usuario) throws SQLException {
        String sql = "UPDATE USUARIO SET email = ? WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();
        }
    }

    // Excluir usuário pelo email
    public boolean excluir(String email) throws SQLException {
        System.out.println("Tentando excluir usuário com email: " + email);
        System.out.println("AutoCommit inicial está " + connection.getAutoCommit());

        // Desativa o autoCommit para controlar manualmente as transações
        connection.setAutoCommit(false);

        // Verifica se o email existe no banco
        String verificarSql = "SELECT 1 FROM USUARIO WHERE email = ?";
        try (PreparedStatement verificarStmt = connection.prepareStatement(verificarSql)) {
            verificarStmt.setString(1, email);
            try (ResultSet rs = verificarStmt.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("Email não encontrado: " + email);
                    return false; // Email não encontrado
                }
            }
        } catch (SQLException e) {
            connection.rollback();
            System.err.println("Erro ao verificar email: " + e.getMessage());
            throw e;
        }

        // Executa o DELETE
        String sql = "DELETE FROM USUARIO WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                connection.commit(); // Confirma a exclusão
                System.out.println("Usuário excluído com sucesso!");
                return true;
            }
            return false;
        } catch (SQLException e) {
            connection.rollback(); // Reverte a transação em caso de erro
            System.err.println("Erro ao excluir usuário: " + e.getMessage());
            throw e;
        } finally {
            // Retorna o autoCommit para o estado original
            connection.setAutoCommit(false);
        }
    }

    // Buscar usuário por nome
    public Usuario buscarPorUsuario(String nomeUsuario) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nomeUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha")
                    );
                }
            }
        }
        return null;
    }

    // Listar todos os usuários
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new Usuario(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                ));
            }
        }
        return usuarios;
    }

    // Fechar conexão
    public void fecharConexao() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
