package br.com.unijorge.projetos.estruturadedados.av3.model;

public class Pedido {
    // Atributos privados do pedido
    private int id;                      // Identificador
    private String nomeCliente;         // Nome do cliente
    private String nomePedido;          // Nome do pedido
    private String descPedido;          // Descrição do pedido
    private int qtdPedido;              // Quantidade do pedido

    private static int proximoId = 1;    // Gera IDs automáticos



    public Pedido() {
        this.id = proximoId++;
    }    // Atribui o ID ao pedido


    public int getId() {
        return id;
    }    // Retorna o ID do pedido

    public String getNomeCliente() {
        return nomeCliente;
    }    // Define o nome do cliente


    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }    // Retorna o nome do cliente

    public String getNomePedido() {
        return nomePedido;
    }    // Retorna o nome do pedido

    public void setNomePedido(String nomePedido) {
        this.nomePedido = nomePedido;
    }    // Define o nome do pedido

    public String getDescPedido() {
        return descPedido;
    }    // Retorna a descrição do pedido

    public void setDescPedido(String descPedido) {
        this.descPedido = descPedido;
    }    // Define a descrição do pedido

    public int getQtdPedido() {
        return qtdPedido;
    }    // Retorna a quantidade do pedido

    public void setQtdPedido(int qtdPedido) {
        this.qtdPedido = qtdPedido;
    }    // Define a quantidade do pedido



    // Forma em que o Objeto Pedido será exibida
    @Override
    public String toString(){
        return String.format("""
            \n
            --------------------------
            ID: %d
            Cliente: %s
            Pedido: %s
            Descrição: %s
            Quantidade: %d
            --------------------------
            \n
            """, id, nomeCliente, nomePedido, descPedido, qtdPedido);
    }
}