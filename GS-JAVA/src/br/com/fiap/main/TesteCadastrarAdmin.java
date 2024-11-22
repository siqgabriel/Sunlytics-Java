package br.com.fiap.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.beans.Admin;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.AdminDAO;

public class TesteCadastrarAdmin {

    public static void main(String[] args) {
        try (Connection connection = new ConexaoFactory().conexao(); Scanner scanner = new Scanner(System.in)) {
            AdminDAO adminDAO = new AdminDAO(connection);

            while (true) {
                System.out.println("---- MENU ----");
                System.out.println("1. Criar Admin");
                System.out.println("2. Atualizar Admin");
                System.out.println("3. Deletar Admin");
                System.out.println("4. Buscar Admin por Email");
                System.out.println("5. Listar Todos os Admins");
                System.out.println("6. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Senha: ");
                        String senha = scanner.nextLine();

                        // Criar objeto Admin com valores fornecidos
                        Admin novoAdmin = new Admin(nome, email, senha);

                        // Chamar método para criar admin no banco
                        adminDAO.create(novoAdmin);
                        System.out.println("Admin criado com sucesso!");
                        break;

                    case 2:
                        System.out.print("Email do Admin para atualizar: ");
                        String emailAtualizar = scanner.nextLine();
                        Admin adminExistente = adminDAO.findByEmail(emailAtualizar);
                        if (adminExistente != null) {
                            System.out.print("Novo Nome: ");
                            String novoNome = scanner.nextLine();
                            System.out.print("Nova Senha: ");
                            String novaSenha = scanner.nextLine();

                            adminExistente.setNome(novoNome);
                            adminExistente.setSenha(novaSenha);
                            adminDAO.update(adminExistente);
                            System.out.println("Admin atualizado com sucesso!");
                        } else {
                            System.out.println("Admin não encontrado!");
                        }
                        break;

                    case 3:
                        System.out.print("Email do Admin para deletar: ");
                        String emailDeletar = scanner.nextLine();
                        adminDAO.delete(emailDeletar);
                        System.out.println("Admin deletado com sucesso!");
                        break;

                    case 4:
                        System.out.print("Email do Admin para buscar: ");
                        String emailBuscar = scanner.nextLine();
                        Admin adminBuscado = adminDAO.findByEmail(emailBuscar);
                        if (adminBuscado != null) {
                            System.out.println("Admin encontrado: " + adminBuscado);
                        } else {
                            System.out.println("Admin não encontrado!");
                        }
                        break;

                    case 5:
                        List<Admin> admins = adminDAO.findAll();
                        System.out.println("---- Lista de Admins ----");
                        for (Admin admin : admins) {
                            System.out.println(admin);
                        }
                        break;

                    case 6:
                        System.out.println("Saindo...");
                        return;

                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
