package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.ConsumoEnergetico;
import br.com.fiap.conexoes.ConexaoFactory;

public class ConsumoEnergeticoDAO {

	private Connection connection;

	public ConsumoEnergeticoDAO(Connection connection) throws ClassNotFoundException, SQLException {
		this.connection = new ConexaoFactory().conexao();
	}

	// Método para inserir um ConsumoEnergetico no banco
	public void create(ConsumoEnergetico consumoEnergetico) throws SQLException {
		String sql = "INSERT INTO CONSUMOENERGETICO (consumoMensalKwh, tarifaPorKwh, gastoMensal) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setDouble(1, consumoEnergetico.getConsumoMensalKwh());
			stmt.setDouble(2, consumoEnergetico.getTarifaPorKwh());
			stmt.setDouble(3, consumoEnergetico.getGastoMensal());
			stmt.executeUpdate();
		}
	}

	// Método para buscar um ConsumoEnergetico por ID
	public ConsumoEnergetico findById(int id) throws SQLException {
		String sql = "SELECT * FROM CONSUMOENERGETICO WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new ConsumoEnergetico(rs.getDouble("consumoMensalKwh"), rs.getDouble("tarifaPorKwh"),
							rs.getDouble("gasto_mensal"));
				}
			}
		}
		return null; // Retorna null se não encontrar
	}

	// Método para buscar todos os registros de ConsumoEnergetico
	public List<ConsumoEnergetico> findAll() throws SQLException {
		String sql = "SELECT * FROM CONSUMOENERGETICO";
		List<ConsumoEnergetico> consumos = new ArrayList<>();
		try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				consumos.add(new ConsumoEnergetico(rs.getDouble("consumoMensalKwh"), rs.getDouble("tarifaPorKwh"),
						rs.getDouble("gastoMensal")));
			}
		}
		return consumos;
	}

	// Método para atualizar um ConsumoEnergetico pelo ID
	public void update(int id, ConsumoEnergetico consumoEnergetico) throws SQLException {
		String sql = "UPDATE CONSUMOENERGETICO SET consumoMensalKwh = ?, tarifaPorKwh = ?, gastoMensal = ? WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setDouble(1, consumoEnergetico.getConsumoMensalKwh());
			stmt.setDouble(2, consumoEnergetico.getTarifaPorKwh());
			stmt.setDouble(3, consumoEnergetico.getGastoMensal());
			stmt.setInt(4, id);
			stmt.executeUpdate();
		}
	}

	// Método para deletar um ConsumoEnergetico pelo ID
	public void delete(int id) throws SQLException {
		String sql = "DELETE FROM CONSUMOENERGETICO WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}
}
