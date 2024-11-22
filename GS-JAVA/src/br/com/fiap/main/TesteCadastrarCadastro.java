package br.com.fiap.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.beans.Cadastro;
import br.com.fiap.beans.Endereco;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.CadastroDAO;

public class TesteCadastrarCadastro {

    public static void main(String[] args) {
        try (Connection connection = new ConexaoFactory().conexao(); Scanner scanner = new Scanner(System.in)) {
            CadastroDAO cadastroDAO = new CadastroDAO(connection);

            while (true) {
                System.out.println("\n---- MENU ----");
                System.out.println("1. Inserir Cadastro");
                System.out.println("2. Buscar Cadastro por Email");
                System.out.println("3. Listar Todos os Cadastros");
                System.out.println("4. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();

                switch (opcao) {
                    case 1: // Inserir Cadastro
                        System.out.print("Nome: ");
                        scanner.nextLine(); // Limpar buffer
                        String nome = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Senha: ");
                        String senha = scanner.nextLine();

                        System.out.println("\n---- Endereço ----");
                        System.out.print("CEP: ");
                        String cep = scanner.nextLine();
                        System.out.print("Logradouro: ");
                        String logradouro = scanner.nextLine();
                        System.out.print("Número: ");
                        int numero = scanner.nextInt();
                        System.out.print("Estado: ");
                        scanner.nextLine(); // Limpar buffer
                        String estado = scanner.nextLine();

                        Endereco endereco = new Endereco(cep, logradouro, numero, null, estado);
                        Cadastro novoCadastro = new Cadastro(nome, endereco, email, senha);
                        cadastroDAO.inserir(novoCadastro);
                        System.out.println("Cadastro inserido com sucesso!");
                        break;

                    case 2: // Buscar Cadastro por Email
                        System.out.print("Email do cadastro a ser buscado: ");
                        scanner.nextLine(); // Limpar buffer
                        String emailBuscar = scanner.nextLine();
                        Cadastro cadastroBuscado = cadastroDAO.buscarPorEmail(emailBuscar);

                        if (cadastroBuscado != null) {
                            System.out.println("Cadastro encontrado: " + cadastroBuscado);
                        } else {
                            System.out.println("Cadastro não encontrado!");
                        }
                        break;

                    case 3: // Listar Todos os Cadastros
                        List<Cadastro> cadastros = cadastroDAO.listarTodos();
                        System.out.println("\n---- Lista de Cadastros ----");
                        for (Cadastro cadastro : cadastros) {
                            System.out.println(cadastro);
                        }
                        break;

                    case 4: // Sair
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
