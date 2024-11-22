package br.com.fiap.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.beans.Calculo;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.CalculoDAO;

public class TesteCadastrarCalculo {

    public static void main(String[] args) {
        try (Connection connection = new ConexaoFactory().conexao(); Scanner scanner = new Scanner(System.in)) {
            CalculoDAO calculoDAO = new CalculoDAO(connection);

            while (true) {
                System.out.println("\n---- MENU ----");
                System.out.println("1. Inserir Cálculo");
                System.out.println("2. Atualizar Cálculo");
                System.out.println("3. Deletar Cálculo");
                System.out.println("4. Buscar Cálculo por ID");
                System.out.println("5. Listar Todos os Cálculos");
                System.out.println("6. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                case 1: // Inserir Cálculo
                    System.out.print("Nome do usuário responsável: ");
                    String nomeUsuario = scanner.nextLine();
                    int idUsuarioInserir = calculoDAO.buscarIdUsuario(nomeUsuario);

                    if (idUsuarioInserir == -1) {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }

                    System.out.print("Economia em kWh: ");
                    double economiaKwh = scanner.nextDouble();
                    System.out.print("Economia na Conta (R$): ");
                    double economiaConta = scanner.nextDouble();
                    System.out.print("Redução de CO2 (kg): ");
                    double reducaoCo2 = scanner.nextDouble();

                    Calculo novoCalculo = new Calculo(economiaKwh, economiaConta, reducaoCo2);
                    calculoDAO.create(novoCalculo, idUsuarioInserir);
                    System.out.println("Cálculo inserido com sucesso!");
                    break;

                case 2: // Atualizar Cálculo
                    System.out.print("Nome do usuário responsável: ");
                    nomeUsuario = scanner.nextLine();
                    int idUsuarioAtualizar = calculoDAO.buscarIdUsuario(nomeUsuario);

                    if (idUsuarioAtualizar == -1) {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }

                    System.out.print("ID do cálculo para atualizar: ");
                    int idAtualizar = scanner.nextInt();

                    System.out.print("Nova Economia em kWh: ");
                    double novaEconomiaKwh = scanner.nextDouble();
                    System.out.print("Nova Economia na Conta (R$): ");
                    double novaEconomiaConta = scanner.nextDouble();
                    System.out.print("Nova Redução de CO2 (kg): ");
                    double novaReducaoCo2 = scanner.nextDouble();

                    Calculo calculoAtualizado = new Calculo(novaEconomiaKwh, novaEconomiaConta, novaReducaoCo2);
                    calculoDAO.update(idAtualizar, calculoAtualizado);
                    System.out.println("Cálculo atualizado com sucesso!");
                    break;

                case 3: // Deletar Cálculo
                    System.out.print("Nome do usuário responsável: ");
                    nomeUsuario = scanner.nextLine();
                    int idUsuarioDeletar = calculoDAO.buscarIdUsuario(nomeUsuario);

                    if (idUsuarioDeletar == -1) {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }

                    System.out.print("ID do cálculo para deletar: ");
                    int idDeletar = scanner.nextInt();
                    calculoDAO.delete(idDeletar);
                    System.out.println("Cálculo deletado com sucesso!");
                    break;

                case 4: // Buscar Cálculo por ID
                    System.out.print("ID do cálculo para buscar: ");
                    int idBuscar = scanner.nextInt();
                    Calculo calculoBuscado = calculoDAO.findById(idBuscar);

                    if (calculoBuscado != null) {
                        System.out.println("Cálculo encontrado: " + calculoBuscado);
                    } else {
                        System.out.println("Cálculo não encontrado!");
                    }
                    break;

                case 5: // Listar Todos os Cálculos
                    List<Calculo> calculos = calculoDAO.findAll();
                    System.out.println("\n---- Lista de Cálculos ----");
                    for (Calculo calculo : calculos) {
                        System.out.println(calculo);
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
