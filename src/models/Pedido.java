package models;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Pedido {
    
//  Questao 08 - Os pedidos e os clientes devem ser salvos de forma dinâmica no sistema.
    private static List<Pedido> pedidos = new ArrayList<>();
    
    private int numero;
    private Cliente cliente;
    private List<Produto> itens = new ArrayList<>();
    private String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss"));
    private String dataHoraEntrega;
    private double precoTotal;
    private String status = "Processando";
    
    
//  Getters e Setters

    public static List<Pedido> getPedidos() {
        return pedidos;
    }

    public static void setPedidos(List<Pedido> pedidos) {
        Pedido.pedidos = pedidos;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getItens() {
        return itens;
    }

    public void setItens(List<Produto> itens) {
        this.itens = itens;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getDataHoraEntrega() {
        return dataHoraEntrega;
    }

    public void setDataHoraEntrega(String dataHoraEntrega) {
        this.dataHoraEntrega = dataHoraEntrega;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal += precoTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

//  Questão 03 - Sobrescrever o método toString() de todas as classes implementadas.
    @Override
    public String toString() {
        
        String itensToString = "";
        int itemCount = 1;
        
        for (Produto item : itens) {
            itensToString = itensToString.concat(
                    String.format("""
                                  -----------------------------------
                                  Item - %02d
                                  Produto: %s
                                  Preco: %s
                                  Descricao: %s
                                  -----------------------------------\n""", 
                    itemCount, 
                    item.getNome(), 
                    item.getPreco(), 
                    item.getDescricao()));
            itemCount++;
        }
        
        return String.format("""
                             -------------------------- Pedido -----------------------------
                             
                             Numero do Pedido: %s - Status: %s
                             Cliente: %s
                             Endereco: %s
                             Data: %s
                             Entrega: %s
                                                        Itens
                             %s

                             Preco Total: R$%.2f
                             
                             ----------------------------------------------------------------\n""",
                this.getNumero(), this.getStatus(), this.getCliente().getNome(),
                this.getCliente().getEndereco(), this.getDataHora(), this.getDataHoraEntrega(),
                itensToString, this.getPrecoTotal());
    }   
    
}
