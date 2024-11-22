package br.com.fiap.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.beans.Estado;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.EstadoDAO;

public class TesteCadastrarEstado {

	public static void main(String[] args) {
		try (Connection connection = new ConexaoFactory().conexao(); Scanner scanner = new Scanner(System.in)) {
			EstadoDAO estadoDAO = new EstadoDAO(connection);

			while (true) {
				System.out.println("\n---- MENU ----");
				System.out.println("1. Inserir Estado");
				System.out.println("2. Atualizar Estado");
				System.out.println("3. Deletar Estado");
				System.out.println("4. Buscar Estado por Nome");
				System.out.println("5. Listar Todos os Estados");
				System.out.println("6. Sair");
				System.out.print("Escolha uma opção: ");
				int opcao = scanner.nextInt();

				switch (opcao) {
				case 1: // Inserir Estado
					System.out.print("Nome do Estado: ");
					scanner.nextLine(); // Limpar o buffer
					String nome = scanner.nextLine();
					System.out.print("Tarifa Média: ");
					Double tarifaMedia = scanner.nextDouble();
					System.out.print("Irradiação Solar Média: ");
					Double irradiacaoSolarMedia = scanner.nextDouble();

					Estado novoEstado = new Estado(nome, tarifaMedia, irradiacaoSolarMedia);
					estadoDAO.create(novoEstado);
					System.out.println("Estado inserido com sucesso!");
					break;

				case 2: // Atualizar Estado
					System.out.print("Nome do Estado a ser atualizado: ");
					scanner.nextLine(); // Limpar o buffer
					String nomeAtualizar = scanner.nextLine();
					System.out.print("Nova Tarifa Média: ");
					Double novaTarifaMedia = scanner.nextDouble();
					System.out.print("Nova Irradiação Solar Média: ");
					Double novaIrradiacaoSolarMedia = scanner.nextDouble();

					Estado estadoAtualizado = new Estado(nomeAtualizar, novaTarifaMedia, novaIrradiacaoSolarMedia);
					estadoDAO.update(nomeAtualizar, estadoAtualizado);
					System.out.println("Estado atualizado com sucesso!");
					break;

				case 3: // Deletar Estado
					System.out.print("Nome do Estado a ser deletado: ");
					scanner.nextLine(); // Limpar o buffer
					String nomeDeletar = scanner.nextLine();
					estadoDAO.delete(nomeDeletar);
					System.out.println("Estado deletado com sucesso!");
					break;

				case 4: // Buscar Estado por Nome
					System.out.print("Nome do Estado a ser buscado: ");
					scanner.nextLine(); // Limpar o buffer
					String nomeBuscar = scanner.nextLine();
					Estado estadoBuscado = estadoDAO.findByName(nomeBuscar);

					if (estadoBuscado != null) {
						System.out.println("Estado encontrado: " + estadoBuscado);
					} else {
						System.out.println("Estado não encontrado!");
					}
					break;

				case 5: // Listar Todos os Estados
					List<Estado> estados = estadoDAO.findAll();
					System.out.println("\n---- Lista de Estados ----");
					for (Estado estado : estados) {
						System.out.println(estado);
					}
					break;

				case 6: // Sair
					System.out.println("Saindo...");
					return;

				default:
					System.out.println("Opção inválida! Tente novamente.");
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Erro: " + e.getMessage());
		}
	}
}
