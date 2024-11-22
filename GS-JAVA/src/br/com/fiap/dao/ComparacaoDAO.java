package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Comparacao;

public class ComparacaoDAO {

    private Connection connection;

    public ComparacaoDAO(Connection connection) throws ClassNotFoundException, SQLException {
        this.connection = connection;
    }

    // Método para buscar ID do usuário pelo nome
    private int findUserIdByName(String nomeUsuario) throws SQLException {
        String sql = "SELECT id_usuario FROM USUARIO WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nomeUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_usuario");
                } else {
                    throw new SQLException("Usuário não encontrado: " + nomeUsuario);
                }
            }
        }
    }

    // Método para inserir uma Comparacao no banco
    public void create(String nomeUsuario, String nomeUsuarioComparado, Comparacao comparacao) throws SQLException {
        int userId = findUserIdByName(nomeUsuario);
        int userComparadoId = findUserIdByName(nomeUsuarioComparado); // Busca ID do usuário comparado

        String sql = "INSERT INTO COMPARACAO (economiaRelativa, reducaoCo2Relativa, id_usuario, id_usuario_comparado) " +
                     "VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, comparacao.getEconomiaRelativa());
            stmt.setDouble(2, comparacao.getReducaoCo2Relativa());
            stmt.setInt(3, userId);
            stmt.setInt(4, userComparadoId); // Adiciona o ID do usuário comparado
            stmt.executeUpdate();
        }
    }

    // Método para buscar uma Comparacao por ID
    public Comparacao findById(int id) throws SQLException {
        String sql = "SELECT * FROM COMPARACAO WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Comparacao(rs.getDouble("economiaRelativa"), rs.getDouble("reducaoCo2Relativa"));
                }
            }
        }
        return null; // Retorna null se não encontrar
    }

    // Método para buscar todas as Comparacoes
    public List<Comparacao> findAll() throws SQLException {
        String sql = "SELECT * FROM COMPARACAO";
        List<Comparacao> comparacoes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                comparacoes.add(new Comparacao(rs.getDouble("economiaRelativa"), rs.getDouble("reducaoCo2Relativa")));
            }
        }
        return comparacoes;
    }

    // Método para atualizar uma Comparacao pelo ID
    public void update(int id, Comparacao comparacao) throws SQLException {
        String sql = "UPDATE COMPARACAO SET economiaRelativa = ?, reducaoCo2Relativa = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, comparacao.getEconomiaRelativa());
            stmt.setDouble(2, comparacao.getReducaoCo2Relativa());
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }

    // Método para deletar uma Comparacao pelo ID
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM COMPARACAO WHERE id_comparacao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
