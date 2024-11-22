package br.com.fiap.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.beans.Usuario;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.UsuarioDAO;

public class TesteCadastrarUsuario {

    public static void main(String[] args) {
        try (Connection connection = new ConexaoFactory().conexao(); 
             Scanner scanner = new Scanner(System.in)) {

            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);

            while (true) {
                System.out.println("\n---- MENU DE USUÁRIOS ----");
                System.out.println("1. Buscar Usuário por Nome");
                System.out.println("2. Listar Todos os Usuários");
                System.out.println("3. Atualizar Nome do Usuário");
                System.out.println("4. Atualizar Senha do Usuário");
                System.out.println("5. Atualizar Email do Usuário");
                System.out.println("6. Excluir Usuário");
                System.out.println("7. Sair");
                System.out.print("Escolha uma opção: ");

                // Validação de entrada para evitar erros
                int opcao = -1;
                if (scanner.hasNextInt()) {
                    opcao = scanner.nextInt();
                } else {
                    System.out.println("Entrada inválida! Digite um número entre 1 e 7.");
                    scanner.nextLine(); // Limpar buffer
                    continue;
                }

                scanner.nextLine(); // Limpar o buffer após a leitura do número

                switch (opcao) {
                    case 1: // Buscar Usuário por Nome
                        System.out.print("Digite o nome do usuário a ser buscado: ");
                        String nomeBusca = scanner.nextLine();
                        Usuario usuarioBuscado = usuarioDAO.buscarPorUsuario(nomeBusca);

                        if (usuarioBuscado != null) {
                            System.out.println("Usuário encontrado: " + usuarioBuscado);
                        } else {
                            System.out.println("Usuário não encontrado!");
                        }
                        break;

                    case 2: // Listar Todos os Usuários
                        List<Usuario> usuarios = usuarioDAO.listar();
                        System.out.println("\n---- Lista de Usuários ----");
                        if (usuarios.isEmpty()) {
                            System.out.println("Nenhum usuário encontrado.");
                        } else {
                            for (Usuario usuario : usuarios) {
                                System.out.println(usuario);
                            }
                        }
                        break;

                    case 3: // Atualizar Nome do Usuário
                        System.out.print("Digite o email do usuário a ser atualizado: ");
                        String emailAtualizarNome = scanner.nextLine();
                        System.out.print("Digite o novo nome: ");
                        String novoNome = scanner.nextLine();

                        Usuario usuarioAtualizarNome = new Usuario(null, emailAtualizarNome, null);
                        usuarioAtualizarNome.setNome(novoNome); // Define o novo nome

                        try {
                            usuarioDAO.atualizarNome(usuarioAtualizarNome);
                            System.out.println("Nome atualizado com sucesso!");
                        } catch (SQLException e) {
                            System.out.println("Erro ao atualizar nome: " + e.getMessage());
                        }
                        break;

                    case 4: // Atualizar Senha do Usuário
                        System.out.print("Digite o email do usuário: ");
                        String emailAtualizarSenha = scanner.nextLine();
                        System.out.print("Digite a nova senha: ");
                        String novaSenha = scanner.nextLine();

                        Usuario usuarioSenhaAtualizado = new Usuario(null, emailAtualizarSenha, novaSenha);

                        try {
                            usuarioDAO.atualizarSenha(usuarioSenhaAtualizado);
                            System.out.println("Senha atualizada com sucesso!");
                        } catch (SQLException e) {
                            System.out.println("Erro ao atualizar senha: " + e.getMessage());
                        }
                        break;

                    case 5: // Atualizar Email do Usuário
                        System.out.print("Digite o email atual do usuário: ");
                        String emailAtual = scanner.nextLine();
                        System.out.print("Digite o novo email: ");
                        String novoEmail = scanner.nextLine();

                        Usuario usuarioAtualizarEmail = new Usuario(null, emailAtual, null);
                        usuarioAtualizarEmail.setEmail(novoEmail); // Define o novo email

                        try {
                            usuarioDAO.atualizarEmail(usuarioAtualizarEmail);
                            System.out.println("Email atualizado com sucesso!");
                        } catch (SQLException e) {
                            System.out.println("Erro ao atualizar email: " + e.getMessage());
                        }
                        break;

                    case 6: // Excluir Usuário
                        System.out.print("Digite o email do usuário a ser excluído: ");
                        String emailExcluir = scanner.nextLine();

                        try {
                            System.out.println("Chamando método excluir...");
                            boolean excluido = usuarioDAO.excluir(emailExcluir);
                            if (excluido) {
                                System.out.println("Usuário excluído com sucesso!");
                            } else {
                                System.out.println("Usuário não encontrado.");
                            }
                        } catch (SQLException e) {
                            System.out.println("Erro ao tentar excluir usuário: " + e.getMessage());
                        }
                        break;

                    case 7: // Sair
                        System.out.println("Encerrando o programa...");
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
