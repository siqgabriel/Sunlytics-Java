package br.com.fiap.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.beans.Endereco;
import br.com.fiap.dao.EnderecoDAO;
import br.com.fiap.conexoes.ConexaoFactory;

public class TesteCadastrarEndereco {

    public static void main(String[] args) {
        try (Connection connection = new ConexaoFactory().conexao(); Scanner scanner = new Scanner(System.in)) {
            EnderecoDAO enderecoDAO = new EnderecoDAO(connection);

            while (true) {
                System.out.println("\n---- MENU ----");
                System.out.println("1. Inserir Endereço");
                System.out.println("2. Atualizar Endereço");
                System.out.println("3. Deletar Endereço");
                System.out.println("4. Buscar Endereço por CEP");
                System.out.println("5. Listar Todos os Endereços");
                System.out.println("6. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();

                switch (opcao) {
                    case 1: // Inserir Endereço
                        System.out.print("CEP: ");
                        String cep = scanner.next();
                        System.out.print("Logradouro: ");
                        scanner.nextLine(); // Limpar o buffer
                        String logradouro = scanner.nextLine();
                        System.out.print("Número: ");
                        int numero = scanner.nextInt();
                        System.out.print("Estado: ");
                        scanner.nextLine(); // Limpar o buffer
                        String estado = scanner.nextLine();

                        Endereco novoEndereco = new Endereco(cep, logradouro, numero, null, estado);
                        enderecoDAO.inserir(novoEndereco);
                        System.out.println("Endereço inserido com sucesso!");
                        break;

                    case 2: // Atualizar Endereço
                        System.out.print("CEP do endereço a ser atualizado: ");
                        String cepAtualizar = scanner.next();
                        System.out.print("Novo Logradouro: ");
                        scanner.nextLine(); // Limpar o buffer
                        String novoLogradouro = scanner.nextLine();
                        System.out.print("Novo Número: ");
                        int novoNumero = scanner.nextInt();
                        System.out.print("Novo Estado: ");
                        scanner.nextLine(); // Limpar o buffer
                        String novoEstado = scanner.nextLine();

                        Endereco enderecoAtualizado = new Endereco(cepAtualizar, novoLogradouro, novoNumero, null, novoEstado);
                        enderecoDAO.atualizar(enderecoAtualizado);
                        System.out.println("Endereço atualizado com sucesso!");
                        break;

                    case 3: // Deletar Endereço
                        System.out.print("CEP do endereço a ser deletado: ");
                        String cepDeletar = scanner.next();
                        enderecoDAO.deletarPorCep(cepDeletar);
                        System.out.println("Endereço deletado com sucesso!");
                        break;

                    case 4: // Buscar Endereço por CEP
                        System.out.print("CEP do endereço a ser buscado: ");
                        String cepBuscar = scanner.next();
                        Endereco enderecoBuscado = enderecoDAO.buscarPorCep(cepBuscar);

                        if (enderecoBuscado != null) {
                            System.out.println("Endereço encontrado: " + enderecoBuscado);
                        } else {
                            System.out.println("Endereço não encontrado!");
                        }
                        break;

                    case 5: // Listar Todos os Endereços
                        List<Endereco> enderecos = enderecoDAO.listarTodos();
                        System.out.println("\n---- Lista de Endereços ----");
                        for (Endereco endereco : enderecos) {
                            System.out.println(endereco);
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
