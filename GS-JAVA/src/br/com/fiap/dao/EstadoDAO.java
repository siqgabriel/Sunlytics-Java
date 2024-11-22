package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Estado;
import br.com.fiap.conexoes.ConexaoFactory;

public class EstadoDAO {

	private Connection connection;

	public EstadoDAO(Connection connection) throws ClassNotFoundException, SQLException {
		this.connection = new ConexaoFactory().conexao();
	}

	// Método para inserir um Estado no banco
	public void create(Estado estado) throws SQLException {
		String sql = "INSERT INTO estado (nome, tarifa_media, irradiacao_solar_media) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, estado.getNome());
			stmt.setDouble(2, estado.getTarifaMedia());
			stmt.setDouble(3, estado.getIrradiacaoSolarMedia());
			stmt.executeUpdate();
		}
	}

	// Método para buscar um Estado por nome
	public Estado findByName(String nome) throws SQLException {
		String sql = "SELECT * FROM estado WHERE nome = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, nome);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Estado(rs.getString("nome"), rs.getDouble("tarifa_media"),
							rs.getDouble("irradiacao_solar_media"));
				}
			}
		}
		return null; // Retorna null se não encontrar
	}

	// Método para buscar todos os Estados
	public List<Estado> findAll() throws SQLException {
		String sql = "SELECT * FROM estado";
		List<Estado> estados = new ArrayList<>();
		try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				estados.add(new Estado(rs.getString("nome"), rs.getDouble("tarifa_media"),
						rs.getDouble("irradiacao_solar_media")));
			}
		}
		return estados;
	}

	// Método para atualizar um Estado pelo nome
	public void update(String nome, Estado estado) throws SQLException {
		String sql = "UPDATE estado SET tarifa_media = ?, irradiacao_solar_media = ? WHERE nome = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setDouble(1, estado.getTarifaMedia());
			stmt.setDouble(2, estado.getIrradiacaoSolarMedia());
			stmt.setString(3, nome);
			stmt.executeUpdate();
		}
	}

	// Método para deletar um Estado pelo nome
	public void delete(String nome) throws SQLException {
		String sql = "DELETE FROM estado WHERE nome = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, nome);
			stmt.executeUpdate();
		}
	}
}
