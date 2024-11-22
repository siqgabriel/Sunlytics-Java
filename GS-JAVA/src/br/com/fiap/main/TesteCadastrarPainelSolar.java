package br.com.fiap.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.beans.PainelSolar;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.PainelSolarDAO;

public class TesteCadastrarPainelSolar {

	public static void main(String[] args) {
		try (Connection connection = new ConexaoFactory().conexao(); Scanner scanner = new Scanner(System.in)) {
			PainelSolarDAO painelDAO = new PainelSolarDAO(connection);

			while (true) {
				System.out.println("\n---- MENU ----");
				System.out.println("1. Inserir Painel Solar");
				System.out.println("2. Atualizar Painel Solar");
				System.out.println("3. Deletar Painel Solar");
				System.out.println("4. Buscar Painel Solar por Tipo");
				System.out.println("5. Listar Todos os Painéis Solares");
				System.out.println("6. Sair");
				System.out.print("Escolha uma opção: ");
				int opcao = scanner.nextInt();

				switch (opcao) {
				case 1: // Inserir Painel Solar
					System.out.print("Tipo do Painel Solar: ");
					scanner.nextLine(); // Limpar buffer
					String tipo = scanner.nextLine();
					System.out.print("Potência (kW): ");
					Double potencia = scanner.nextDouble();
					System.out.print("Eficiência (%): ");
					Double eficiencia = scanner.nextDouble();
					System.out.print("Custo (R$): ");
					Double custo = scanner.nextDouble();

					PainelSolar novoPainel = new PainelSolar(tipo, potencia, eficiencia, custo);
					painelDAO.create(novoPainel);
					System.out.println("Painel Solar inserido com sucesso!");
					break;

				case 2: // Atualizar Painel Solar
					System.out.print("Tipo do Painel Solar a ser atualizado: ");
					scanner.nextLine(); // Limpar buffer
					String tipoAtualizar = scanner.nextLine();
					System.out.print("Nova Potência (kW): ");
					Double novaPotencia = scanner.nextDouble();
					System.out.print("Nova Eficiência (%): ");
					Double novaEficiencia = scanner.nextDouble();
					System.out.print("Novo Custo (R$): ");
					Double novoCusto = scanner.nextDouble();

					PainelSolar painelAtualizado = new PainelSolar(tipoAtualizar, novaPotencia, novaEficiencia,
							novoCusto);
					painelDAO.update(tipoAtualizar, painelAtualizado);
					System.out.println("Painel Solar atualizado com sucesso!");
					break;

				case 3: // Deletar Painel Solar
					System.out.print("Tipo do Painel Solar a ser deletado: ");
					scanner.nextLine(); // Limpar buffer
					String tipoDeletar = scanner.nextLine();
					painelDAO.delete(tipoDeletar);
					System.out.println("Painel Solar deletado com sucesso!");
					break;

				case 4: // Buscar Painel Solar por Tipo
					System.out.print("Tipo do Painel Solar a ser buscado: ");
					scanner.nextLine(); // Limpar buffer
					String tipoBuscar = scanner.nextLine();
					PainelSolar painelBuscado = painelDAO.findByType(tipoBuscar);

					if (painelBuscado != null) {
						System.out.println("Painel Solar encontrado: " + painelBuscado);
					} else {
						System.out.println("Painel Solar não encontrado!");
					}
					break;

				case 5: // Listar Todos os Painéis Solares
					List<PainelSolar> paineis = painelDAO.findAll();
					System.out.println("\n---- Lista de Painéis Solares ----");
					for (PainelSolar painel : paineis) {
						System.out.println(painel);
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
