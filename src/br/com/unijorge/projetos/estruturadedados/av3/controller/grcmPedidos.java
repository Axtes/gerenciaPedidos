package br.com.unijorge.projetos.estruturadedados.av3.controller;
import br.com.unijorge.projetos.estruturadedados.av3.model.Pedido;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class grcmPedidos  {
    private final List<String> historicoAcoes = new ArrayList<>();
    private final Map<Integer, String> mapaAcoes = new LinkedHashMap<>();
    private int contadorAcao = 1;

    Queue<Pedido> ListaDePedidos  = new LinkedList<>();

    // Método responsável por cadastrar um novo pedido, com validações de entrada para cada campo
    public void CriarPedido() {
        Scanner leitorP = new Scanner(System.in);
        Pedido pedidos = new Pedido();

        try {
            // Nome do cliente
            while (true) {
                System.out.print("Digite o nome do cliente: ");
                String nomeCliente = leitorP.nextLine();

                if (nomeCliente.isBlank()) {
                    System.out.println("Erro: Nome do cliente não pode ser vazio.");
                    continue;
                }

                if (nomeCliente.matches("\\d+")) {
                    System.out.println("Erro: Nome do cliente não pode ser apenas números.");
                    continue;
                }

                pedidos.setNomeCliente(nomeCliente);
                break;
            }

            // Nome do pedido
            while (true) {
                System.out.print("Digite o nome do pedido: ");
                String nomePedido = leitorP.nextLine();

                if (nomePedido.isBlank()) {
                    System.out.println("Erro: Nome do pedido não pode ser vazio.");
                    continue;
                }

                if (nomePedido.matches("\\d+")) {
                    System.out.println("Erro: Nome do pedido não pode ser apenas números.");
                    continue;
                }

                pedidos.setNomePedido(nomePedido);
                break;
            }

            // Descrição do pedido
            while (true) {
                System.out.print("Coloque a descrição do produto: ");
                String descPedido = leitorP.nextLine();

                if (descPedido.isBlank()) {
                    System.out.println("Erro: Descrição não pode ser vazia.");
                    continue;
                }

                if (descPedido.matches("\\d+")) {
                    System.out.println("Erro: Descrição não pode ser apenas números.");
                    continue;
                }

                pedidos.setDescPedido(descPedido);
                break;
            }

            // Quantidade
            while (true) {
                System.out.print("Digite a quantidade dos produtos: ");
                String qtdStr = leitorP.nextLine();

                if (qtdStr.isBlank()) {
                    System.out.println("Erro: Quantidade não pode ser vazia.");
                    continue;
                }

                try {
                    int qtd = Integer.parseInt(qtdStr);
                    if (qtd <= 0) {
                        System.out.println("Erro: Quantidade deve ser maior que zero.");
                        continue;
                    }

                    pedidos.setQtdPedido(qtd);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Digite um número inteiro válido.");
                }
            }

            System.out.println("Pedido criado com sucesso!");
            ListaDePedidos.add(pedidos);
            historicoAcoes.add("Cadastrou pedido ID " + pedidos.getId());
            mapaAcoes.put(contadorAcao++, "Cadastro pedido ID " + pedidos.getId());

        } catch (Exception e) {
            System.out.println("Erro inesperado durante o cadastro: " + e.getMessage());
        }
    }
    // Lista todos os pedidos cadastrados ou exibe uma mensagem se estiver vazio.
    // Também registra a ação no histórico.
    public void Listagem() {
        if (ListaDePedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");

            historicoAcoes.add("Tentou listar pedidos (lista vazia).");
            mapaAcoes.put(contadorAcao++, "Listagem sem resultado.");
        } else {
            System.out.println(ListaDePedidos);

            historicoAcoes.add("Listou todos os pedidos.");
            mapaAcoes.put(contadorAcao++, "Listagem de pedidos.");
        }
    }
    // Permite consultar pedidos por ID, nome do cliente ou nome do produto.
    // Exibe os resultados encontrados ou uma mensagem de erro.
    public void Consulta() {
        if (ListaDePedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("""
                \n
                --------------------------------
                Como deseja buscar o pedido?
                1 - Por ID
                2 - Por Nome do Cliente
                3 - Por Nome do Produto
                
                Digite a opção desejada:
                --------------------------------
                \n
                """);

        int opC = sc.nextInt();
        sc.nextLine();

        System.out.print("Digite o termo de busca: ");
        String termoBusca = sc.nextLine().toLowerCase();

        boolean encontrado = false;

        for (Pedido p : ListaDePedidos) {
            switch (opC) {
                case 1 -> {
                    try {
                        int idBusca = Integer.parseInt(termoBusca);
                        if (p.getId() == idBusca) {
                            if (!encontrado) {
                                System.out.println("Pedidos encontrados por ID:");
                                encontrado = true;
                            }
                            System.out.println(p);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido. Precisa ser um número.");
                        return;
                    }
                }
                case 2 -> {
                    if (p.getNomeCliente().toLowerCase().contains(termoBusca.toLowerCase())) {
                        if (!encontrado) {
                            System.out.println("Pedidos encontrados pelo nome do cliente:");
                            encontrado = true;
                        }
                        System.out.println(p);
                    }
                }
                case 3 -> {
                    if (p.getNomePedido().toLowerCase().contains(termoBusca.toLowerCase())) {
                        if (!encontrado) {
                            System.out.println("Pedidos encontrados pelo nome do produto:");
                            encontrado = true;
                        }
                        System.out.println(p);
                    }
                }
                default -> {
                    System.out.println("Opção inválida.");
                    return;
                }
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum pedido encontrado.");
            historicoAcoes.add("Tentou consultar um pedido (nenhum encontrado).");
            mapaAcoes.put(contadorAcao++, "Consulta sem resultado.");
        } else {
            historicoAcoes.add("Consultou pedido por ID ou nome.");
            mapaAcoes.put(contadorAcao++, "Consulta realizada.");
        }
    }
    // Permite alterar campos de um pedido previamente cadastrado.
    // Busca o pedido por ID, nome, produto ou descrição.
    // Registra cada alteração no histórico de ações.
    public void alterar() {
        if (ListaDePedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("""
        --------------------------------
        Como deseja buscar o pedido para alterar?
        1 - Por ID
        2 - Por Nome do Cliente
        3 - Por Nome do Produto
        4 - Por Descrição
        --------------------------------
        """);

        int opC = sc.nextInt();
        sc.nextLine();

        System.out.print("Digite o termo de busca: ");
        String termoBusca = sc.nextLine().toLowerCase().trim();

        boolean algumEncontrado = false;

        for (Pedido p : ListaDePedidos) {
            boolean corresponde = false;

            switch (opC) {
                case 1:
                    try {
                        int idBusca = Integer.parseInt(termoBusca);
                        corresponde = p.getId() == idBusca;
                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido. Digite um número.");
                        return;
                    }
                    break;
                case 2:
                    corresponde = p.getNomeCliente().toLowerCase().contains(termoBusca);
                    break;
                case 3:
                    corresponde = p.getNomePedido().toLowerCase().contains(termoBusca);
                    break;
                case 4:
                    corresponde = p.getDescPedido().toLowerCase().contains(termoBusca);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    return;
            }

            if (corresponde) {
                if (!algumEncontrado) {
                    System.out.println("Pedidos encontrados:");
                    algumEncontrado = true;
                }
                System.out.println(p);
            }
        }

        if (!algumEncontrado) {
            System.out.println("Nenhum pedido encontrado com o termo informado.");
            return;
        }

        while (true) {
            System.out.print("Digite o ID do pedido que deseja alterar: ");
            String entrada = sc.nextLine();

            int idBusca;
            try {
                idBusca = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("ID inválido. Precisa ser um número.");
                return;
            }

            Pedido pedidoSelecionado = null;
            for (Pedido p : ListaDePedidos) {
                if (p.getId() == idBusca) {
                    pedidoSelecionado = p;
                    break;
                }
            }

            if (pedidoSelecionado == null) {
                System.out.println("Não foi possível encontrar um pedido com o ID informado.");
                return;
            }

            boolean continuarAlterando = true;
            while (continuarAlterando) {
                System.out.println("\nPedido selecionado:");
                System.out.println(pedidoSelecionado);

                System.out.println("""
                O que deseja alterar?
                1 - Nome do Cliente
                2 - Nome do Produto
                3 - Descrição
                4 - Quantidade
                """);

                String opcao = sc.nextLine();
                switch (opcao) {
                    case "1" -> {
                        System.out.print("Novo nome do cliente: ");
                        String nome = sc.nextLine().trim();
                        if (nome.isEmpty()) {
                            System.out.println("Nome inválido.");
                        } else {
                            pedidoSelecionado.setNomeCliente(nome);
                            historicoAcoes.add("Alterou o pedido ID " + pedidoSelecionado.getId());
                            mapaAcoes.put(contadorAcao++, "Alteração no pedido ID " + pedidoSelecionado.getId());
                            System.out.println("Nome do cliente alterado.");
                        }
                    }
                    case "2" -> {
                        System.out.print("Novo nome do produto: ");
                        String produto = sc.nextLine().trim();
                        if (produto.isEmpty()) {
                            System.out.println("Nome do produto inválido.");
                        } else {
                            pedidoSelecionado.setNomePedido(produto);
                            historicoAcoes.add("Alterou o pedido ID " + pedidoSelecionado.getId());
                            mapaAcoes.put(contadorAcao++, "Alteração no pedido ID " + pedidoSelecionado.getId());
                            System.out.println("Nome do produto alterado.");
                        }
                    }
                    case "3" -> {
                        System.out.print("Nova descrição: ");
                        String desc = sc.nextLine().trim();
                        if (desc.isEmpty()) {
                            System.out.println("Descrição inválida.");
                        } else {
                            pedidoSelecionado.setDescPedido(desc);
                            historicoAcoes.add("Alterou o pedido ID " + pedidoSelecionado.getId());
                            mapaAcoes.put(contadorAcao++, "Alteração no pedido ID " + pedidoSelecionado.getId());
                            System.out.println("Descrição alterada.");
                        }
                    }
                    case "4" -> {
                        System.out.print("Nova quantidade: ");
                        String qtdStr = sc.nextLine();
                        try {
                            int qtd = Integer.parseInt(qtdStr);
                            if (qtd > 0) {
                                pedidoSelecionado.setQtdPedido(qtd);
                                historicoAcoes.add("Alterou o pedido ID " + pedidoSelecionado.getId());
                                mapaAcoes.put(contadorAcao++, "Alteração no pedido ID " + pedidoSelecionado.getId());
                                System.out.println("Quantidade alterada.");
                            } else {
                                System.out.println("A quantidade deve ser maior que zero.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Quantidade inválida.");
                        }
                    }
                    default -> System.out.println("Opção inválida.");
                }

                System.out.println("""
                Deseja:
                1 - Continuar alterando este pedido
                2 - Alterar outro pedido
                3 - Voltar ao menu principal
                """);

                String acao = sc.nextLine();
                switch (acao) {
                    case "1" -> continuarAlterando = true;
                    case "2" -> {
                        continuarAlterando = false;
                    }
                    case "3" -> {
                        return;
                    }
                    default -> System.out.println("Opção inválida. Voltando ao menu principal...");
                }
            }
        }
    }
    // Remove um pedido da lista com base em uma busca por ID, nome do cliente ou nome do produto.
    // Confirma a remoção com o usuário antes de executar.
    // A remoção também é registrada no histórico.
    public void remover() {
        if (ListaDePedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("""
        --------------------------------
        Como deseja buscar o pedido para remover?
        1 - Por ID
        2 - Por Nome do Cliente
        3 - Por Nome do Produto
        4 - Cancelar e voltar ao menu
        --------------------------------
        """);

            int opcao;
            try {
                opcao = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }

            if (opcao == 4) {
                System.out.println("Voltando ao menu...");
                return;
            }

            System.out.print("Digite o termo de busca: ");
            String termoBusca = sc.nextLine().toLowerCase().trim();

            Pedido pedidoEncontrado = null;
            for (Pedido p : ListaDePedidos) {
                boolean corresponde = switch (opcao) {
                    case 1 -> {
                        try {
                            int idBusca = Integer.parseInt(termoBusca);
                            yield p.getId() == idBusca;
                        } catch (NumberFormatException e) {
                            yield false;
                        }
                    }
                    case 2 -> p.getNomeCliente().toLowerCase().contains(termoBusca);
                    case 3 -> p.getNomePedido().toLowerCase().contains(termoBusca);
                    default -> false;
                };

                if (corresponde) {
                    pedidoEncontrado = p;
                    break;
                }
            }

            if (pedidoEncontrado == null) {
                System.out.println("Nenhum pedido encontrado com o termo informado.");
                continue;
            }

            System.out.println("Deseja remover o seguinte pedido?");
            System.out.println(pedidoEncontrado);
            System.out.print("Digite 'Sim' para confirmar ou qualquer outra tecla para cancelar: ");
            String confirmacao = sc.nextLine().trim();

            if (confirmacao.equalsIgnoreCase("Sim")) {
                boolean removido = ListaDePedidos.remove(pedidoEncontrado);
                if (removido) {
                    System.out.println("Pedido removido com sucesso!");
                    historicoAcoes.add("Removeu pedido ID " + pedidoEncontrado.getId());
                    mapaAcoes.put(contadorAcao++, "Remoção do pedido ID " + pedidoEncontrado.getId());
                } else {
                    System.out.println("Ocorreu um erro ao remover o pedido.");
                }
            } else {
                System.out.println("Remoção cancelada.");
            }

            String escolha;
            do {
                System.out.println("""
                    Deseja:
                    1 - Remover outro pedido
                    2 - Voltar ao menu principal
                    """);

                escolha = sc.nextLine();

                switch (escolha) {
                    case "1": {
                        break;
                    }
                    case "2": {
                        return;
                    }
                    default: {
                        System.out.println("Opção inválida. Digite novamente.");
                    }
                }
            } while (!escolha.equals("1"));

        }
    }
    // Exporta os pedidos atuais para um arquivo JSON (pedidos.json) formatado.
    // Registra a exportação no histórico.
    public void exportarPedidos() {
        if (ListaDePedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado para exportar.");
            return;
        }

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try (FileWriter escritor = new FileWriter("pedidos.json")) {
            gson.toJson(ListaDePedidos, escritor);
            System.out.println("Pedidos exportados com sucesso para 'pedidos.json'!");
            historicoAcoes.add("Exportou pedidos.");
            mapaAcoes.put(contadorAcao++, "Exportação de pedidos.");
        } catch (IOException e) {
            System.out.println("Erro ao exportar pedidos: " + e.getMessage());
        }
    }
    // Importa pedidos a partir de um arquivo JSON (pedidos.json).
    // Substitui os pedidos atuais pelos importados.
    // Também registra a ação no histórico.
    public void importarPedidos() {
        File arquivo = new File("pedidos.json");
        if (!arquivo.exists()) {
            System.out.println("Arquivo 'pedidos.json' não encontrado.");
            return;
        }

        Gson gson = new Gson();

        try (FileReader lerArquivo = new FileReader(arquivo)) {
            Pedido[] pedidosArray = gson.fromJson(lerArquivo, Pedido[].class);

            if (pedidosArray != null) {
                ListaDePedidos.clear();
                Collections.addAll(ListaDePedidos, pedidosArray);
                System.out.println("Pedidos importados com sucesso!");
                historicoAcoes.add("Importou pedidos e limpou os anteriores.");
                mapaAcoes.put(contadorAcao++, "Importação de pedidos.");
            } else {
                System.out.println("Arquivo vazio ou com formato inválido.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao importar pedidos: " + e.getMessage());
        }
    }
    // Exibe todas as ações realizadas no sistema até o momento, como cadastro, alteração, remoção etc.
    // Também registra o acesso ao relatório.
    public void relatorioAcoes() {
        if (historicoAcoes.isEmpty()) {
            System.out.println("Nenhuma ação registrada.");
            return;
        }

        System.out.println("\n========== RELATÓRIO DE AÇÕES ==========");
        int i = 1;
        for (String acao : historicoAcoes) {
            System.out.println(i++ + " - " + acao);
        }
        System.out.println("========================================");

        historicoAcoes.add("Acessou o relatório de ações.");
        mapaAcoes.put(contadorAcao++, "Relatório acessado.");
    }
}
