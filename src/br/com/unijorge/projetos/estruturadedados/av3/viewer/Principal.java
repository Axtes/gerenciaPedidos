package br.com.unijorge.projetos.estruturadedados.av3.viewer;

import br.com.unijorge.projetos.estruturadedados.av3.controller.grcmPedidos;

import java.util.*;

public class Principal {
    public static void main(String[] args) {

        grcmPedidos pedidos = new grcmPedidos();
        Scanner leitor = new Scanner(System.in);
        int op = 0;

        do {
            boolean entradaValida = false;

            // Loop para garantir que a entrada seja um número inteiro válido
            while (!entradaValida) {
                try {
                    // Exibe o menu de opções
                    System.out.println("""
                        ****************************************
                        *                                      *
                        * 1 - Cadastrar                        *
                        * 2 - Lista de Pedidos                 *
                        * 3 - Consultar pedido por ID ou Nome  *
                        * 4 - Atualizar pedido                 *
                        * 5 - Remover pedido                   *
                        * 6 - Exportar pedidos                 *
                        * 7 - Importar pedidos                 *
                        * 8 - Relatório de Ações               *
                        * 9 - Sair                             *
                        *                                      *
                        * Digite uma opção:                    *
                        ****************************************
                        """);

                    // Lê a entrada como string e converte para inteiro
                    op = Integer.parseInt(leitor.nextLine());
                    // Saida do loop
                    entradaValida = true;

                } catch (NumberFormatException e) {
                    // Mensagem de erro caso o usuário digite texto ou valor inválido
                    System.out.println("Erro: Por favor, digite apenas números inteiros entre 1 e 9.");
                }
            }

            switch (op) {
                case 1 -> pedidos.CriarPedido();            // Cadastra um novo pedido
                case 2 -> pedidos.Listagem();               // Lista todos os pedidos registrados
                case 3 -> pedidos.Consulta();               // Consulta um pedido por ID ou nome
                case 4 -> pedidos.alterar();                // Altera/atualiza os dados de um pedido existente
                case 5 -> pedidos.remover();                // Remove um pedido do sistema
                case 6 -> pedidos.exportarPedidos();        // Exporta os pedidos para um arquivo
                case 7 -> pedidos.importarPedidos();        // Importa pedidos de um arquivo
                case 8 -> pedidos.relatorioAcoes();         // Gera um relatório das ações realizadas
                case 9 -> System.out.println("Encerrando aplicação...");  // Encerra o programa
                default -> System.out.println("Opção Inválida! Digite um número de 1 a 9."); // Trata opção fora do menu
            }

        } while (op != 9);

        leitor.close();
    }
}