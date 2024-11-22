package br.com.fiap.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.beans.ConsumoEnergetico;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.ConsumoEnergeticoDAO;

public class TesteCadastarConsumoEnergetico {

	public static void main(String[] args) {
		try (Connection connection = new ConexaoFactory().conexao(); Scanner scanner = new Scanner(System.in)) {
			ConsumoEnergeticoDAO consumoDAO = new ConsumoEnergeticoDAO(connection);

			while (true) {
				System.out.println("\n---- MENU ----");
				System.out.println("1. Inserir Consumo Energético");
				System.out.println("2. Atualizar Consumo Energético");
				System.out.println("3. Deletar Consumo Energético");
				System.out.println("4. Buscar Consumo Energético por ID");
				System.out.println("5. Listar Todos os Consumos Energéticos");
				System.out.println("6. Sair");
				System.out.print("Escolha uma opção: ");
				int opcao = scanner.nextInt();

				switch (opcao) {
				case 1: // Inserir Consumo Energético
					System.out.print("Consumo Mensal (kWh): ");
					double consumoMensalKwh = scanner.nextDouble();
					System.out.print("Tarifa por kWh (R$): ");
					double tarifaPorKwh = scanner.nextDouble();
					System.out.print("Gasto Mensal (R$): ");
					double gastoMensal = scanner.nextDouble();

					ConsumoEnergetico novoConsumo = new ConsumoEnergetico(consumoMensalKwh, tarifaPorKwh, gastoMensal);
					consumoDAO.create(novoConsumo);
					System.out.println("Consumo Energético inserido com sucesso!");
					break;

				case 2: // Atualizar Consumo Energético
					System.out.print("ID do consumo para atualizar: ");
					int idAtualizar = scanner.nextInt();

					System.out.print("Novo Consumo Mensal (kWh): ");
					double novoConsumoMensalKwh = scanner.nextDouble();
					System.out.print("Nova Tarifa por kWh (R$): ");
					double novaTarifaPorKwh = scanner.nextDouble();
					System.out.print("Novo Gasto Mensal (R$): ");
					double novoGastoMensal = scanner.nextDouble();

					ConsumoEnergetico consumoAtualizado = new ConsumoEnergetico(novoConsumoMensalKwh, novaTarifaPorKwh,
							novoGastoMensal);
					consumoDAO.update(idAtualizar, consumoAtualizado);
					System.out.println("Consumo Energético atualizado com sucesso!");
					break;

				case 3: // Deletar Consumo Energético
					System.out.print("ID do consumo para deletar: ");
					int idDeletar = scanner.nextInt();
					consumoDAO.delete(idDeletar);
					System.out.println("Consumo Energético deletado com sucesso!");
					break;

				case 4: // Buscar Consumo Energético por ID
					System.out.print("ID do consumo para buscar: ");
					int idBuscar = scanner.nextInt();
					ConsumoEnergetico consumoBuscado = consumoDAO.findById(idBuscar);

					if (consumoBuscado != null) {
						System.out.println("Consumo Energético encontrado: " + consumoBuscado);
					} else {
						System.out.println("Consumo Energético não encontrado!");
					}
					break;

				case 5: // Listar Todos os Consumos Energéticos
					List<ConsumoEnergetico> consumos = consumoDAO.findAll();
					System.out.println("\n---- Lista de Consumos Energéticos ----");
					for (ConsumoEnergetico consumo : consumos) {
						System.out.println(consumo);
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
