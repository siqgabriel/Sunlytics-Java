package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Calculo;

public class CalculoDAO {

    private Connection connection;

    public CalculoDAO(Connection connection) {
        this.connection = connection;
    }

    public int buscarIdUsuario(String nomeUsuario) throws SQLException {
        String sql = "SELECT id_usuario FROM Usuario WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nomeUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_usuario");
                }
            }
        }
        return -1; // Retorna -1 se não encontrar
    }

    public void create(Calculo calculo, int idUsuario) throws SQLException {
        String sql = "INSERT INTO CALCULO (economiaKwh, economiaConta, reducaoCo2, id_usuario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, calculo.getEconomiaKwh());
            stmt.setDouble(2, calculo.getEconomiaConta());
            stmt.setDouble(3, calculo.getReducaoCo2());
            stmt.setInt(4, idUsuario);
            stmt.executeUpdate();
        }
    }

    public void update(int id, Calculo calculo) throws SQLException {
        String sql = "UPDATE CALCULO SET economiaKwh = ?, economiaConta = ?, reducaoCo2 = ? WHERE id_calculo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, calculo.getEconomiaKwh());
            stmt.setDouble(2, calculo.getEconomiaConta());
            stmt.setDouble(3, calculo.getReducaoCo2());
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM CALCULO WHERE id_calculo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Calculo findById(int id) throws SQLException {
        String sql = "SELECT * FROM CALCULO WHERE id_calculo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Calculo(rs.getDouble("economiaKwh"), rs.getDouble("economiaConta"),
                            rs.getDouble("reducaoCo2"));
                }
            }
        }
        return null; // Retorna null se não encontrar
    }

    public List<Calculo> findAll() throws SQLException {
        String sql = "SELECT * FROM CALCULO";
        List<Calculo> calculos = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                calculos.add(new Calculo(rs.getDouble("economiaKwh"), rs.getDouble("economiaConta"),
                        rs.getDouble("reducaoCo2")));
            }
        }
        return calculos;
    }
}
