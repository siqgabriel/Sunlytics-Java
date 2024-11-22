package br.com.fiap.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.beans.Comparacao;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.ComparacaoDAO;

public class TesteCadastrarComparacao {

    public static void main(String[] args) {
        try (Connection connection = new ConexaoFactory().conexao(); Scanner scanner = new Scanner(System.in)) {
            ComparacaoDAO comparacaoDAO = new ComparacaoDAO(connection);

            while (true) {
                System.out.println("\n---- MENU ----");
                System.out.println("1. Inserir Comparação");
                System.out.println("2. Atualizar Comparação");
                System.out.println("3. Deletar Comparação");
                System.out.println("4. Buscar Comparação por ID");
                System.out.println("5. Listar Todas as Comparações");
                System.out.println("6. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();

                switch (opcao) {
                    case 1: // Inserir Comparação
                        System.out.print("Nome do Usuário Responsável: ");
                        String nomeUsuario = scanner.next();

                        System.out.print("Nome do Usuário Comparado: ");
                        String nomeUsuarioComparado = scanner.next();

                        System.out.print("Economia Relativa (%): ");
                        double economiaRelativa = scanner.nextDouble();
                        System.out.print("Redução de CO2 Relativa (%): ");
                        double reducaoCo2Relativa = scanner.nextDouble();

                        Comparacao novaComparacao = new Comparacao(economiaRelativa, reducaoCo2Relativa);
                        comparacaoDAO.create(nomeUsuario, nomeUsuarioComparado, novaComparacao);
                        System.out.println("Comparação inserida com sucesso!");
                        break;

                    case 2: // Atualizar Comparação
                        System.out.print("ID da comparação para atualizar: ");
                        int idAtualizar = scanner.nextInt();

                        System.out.print("Nova Economia Relativa (%): ");
                        double novaEconomiaRelativa = scanner.nextDouble();
                        System.out.print("Nova Redução de CO2 Relativa (%): ");
                        double novaReducaoCo2Relativa = scanner.nextDouble();

                        Comparacao comparacaoAtualizada = new Comparacao(novaEconomiaRelativa, novaReducaoCo2Relativa);
                        comparacaoDAO.update(idAtualizar, comparacaoAtualizada);
                        System.out.println("Comparação atualizada com sucesso!");
                        break;

                    case 3: // Deletar Comparação
                        System.out.print("ID da comparação para deletar: ");
                        int idDeletar = scanner.nextInt();
                        comparacaoDAO.delete(idDeletar);
                        System.out.println("Comparação deletada com sucesso!");
                        break;

                    case 4: // Buscar Comparação por ID
                        System.out.print("ID da comparação para buscar: ");
                        int idBuscar = scanner.nextInt();
                        Comparacao comparacaoBuscada = comparacaoDAO.findById(idBuscar);

                        if (comparacaoBuscada != null) {
                            System.out.println("Comparação encontrada: " + comparacaoBuscada);
                        } else {
                            System.out.println("Comparação não encontrada!");
                        }
                        break;

                    case 5: // Listar Todas as Comparações
                        List<Comparacao> comparacoes = comparacaoDAO.findAll();
                        System.out.println("\n---- Lista de Comparações ----");
                        for (Comparacao comparacao : comparacoes) {
                            System.out.println(comparacao);
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
