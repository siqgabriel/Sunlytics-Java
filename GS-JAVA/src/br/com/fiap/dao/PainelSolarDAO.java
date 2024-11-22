package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.PainelSolar;
import br.com.fiap.conexoes.ConexaoFactory;

public class PainelSolarDAO {

	private Connection connection;

	public PainelSolarDAO(Connection connection) throws ClassNotFoundException, SQLException {
		this.connection = new ConexaoFactory().conexao();
	}

	// Método para inserir um PainelSolar no banco
	public void create(PainelSolar painelSolar) throws SQLException {
		String sql = "INSERT INTO PAINELSOLAR (tipo, potencia, eficiencia, custo) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, painelSolar.getTipo());
			stmt.setDouble(2, painelSolar.getPotencia());
			stmt.setDouble(3, painelSolar.getEficiencia());
			stmt.setDouble(4, painelSolar.getCusto());
			stmt.executeUpdate();
		}
	}

	// Método para buscar um PainelSolar por tipo
	public PainelSolar findByType(String tipo) throws SQLException {
		String sql = "SELECT * FROM PAINELSOLAR WHERE tipo = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, tipo);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new PainelSolar(rs.getString("tipo"), rs.getDouble("potencia"), rs.getDouble("eficiencia"),
							rs.getDouble("custo"));
				}
			}
		}
		return null; // Retorna null se não encontrar
	}

	// Método para buscar todos os Painéis Solares
	public List<PainelSolar> findAll() throws SQLException {
		String sql = "SELECT * FROM PAINELSOLAR";
		List<PainelSolar> paineis = new ArrayList<>();
		try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				paineis.add(new PainelSolar(rs.getString("tipo"), rs.getDouble("potencia"), rs.getDouble("eficiencia"),
						rs.getDouble("custo")));
			}
		}
		return paineis;
	}

	// Método para atualizar um PainelSolar pelo tipo
	public void update(String tipo, PainelSolar painelSolar) throws SQLException {
		String sql = "UPDATE PAINELSOLAR SET potencia = ?, eficiencia = ?, custo = ? WHERE tipo = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setDouble(1, painelSolar.getPotencia());
			stmt.setDouble(2, painelSolar.getEficiencia());
			stmt.setDouble(3, painelSolar.getCusto());
			stmt.setString(4, tipo);
			stmt.executeUpdate();
		}
	}

	// Método para deletar um PainelSolar pelo tipo
	public void delete(String tipo) throws SQLException {
		String sql = "DELETE FROM PAINELSOLAR WHERE tipo = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, tipo);
			stmt.executeUpdate();
		}
	}
}
