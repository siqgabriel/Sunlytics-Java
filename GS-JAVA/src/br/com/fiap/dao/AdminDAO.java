package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Admin;
import br.com.fiap.conexoes.ConexaoFactory;

public class AdminDAO {

	private Connection connection;

	// Construtor que recebe a conexão com o banco
	public AdminDAO(Connection connection) throws ClassNotFoundException, SQLException {
		this.connection = new ConexaoFactory().conexao();
	}

	// Método para inserir um Admin no banco
	public void create(Admin admin) throws SQLException {
		String sql = "INSERT INTO ADM (nome, email, senha) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, admin.getNome());
			stmt.setString(2, admin.getEmail());
			stmt.setString(3, admin.getSenha());
			stmt.executeUpdate();
		}
	}

	// Método para atualizar um Admin no banco
	public void update(Admin admin) throws SQLException {
		String sql = "UPDATE ADM SET nome = ?, email = ?, senha = ? WHERE email = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, admin.getNome());
			stmt.setString(2, admin.getEmail());
			stmt.setString(3, admin.getSenha());
			stmt.setString(4, admin.getEmail());
			stmt.executeUpdate();
		}
	}

	// Método para deletar um Admin pelo email
	public void delete(String email) throws SQLException {
		String sql = "DELETE FROM ADM WHERE email = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, email);
			stmt.executeUpdate();
		}
	}

	// Método para buscar um Admin pelo email
	public Admin findByEmail(String email) throws SQLException {
		String sql = "SELECT * FROM ADM WHERE email = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, email);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Admin(rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
				}
			}
		}
		return null; // Retorna null caso não encontre
	}

	// Método para buscar todos os Admins
	public List<Admin> findAll() throws SQLException {
		String sql = "SELECT * FROM ADM";
		List<Admin> admins = new ArrayList<>();
		try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				admins.add(new Admin(rs.getString("nome"), rs.getString("email"), rs.getString("senha")));
			}
		}
		return admins;
	}
}
