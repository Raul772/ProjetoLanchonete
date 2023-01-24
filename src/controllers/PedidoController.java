package controllers;

import entities.Produto;
import entities.Pedido;
import entities.Cliente;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author raulv
 */
public class PedidoController extends Pedido {
    
    
    private static List<Pedido> pedidos = new ArrayList<>();

    public static List<Pedido> getPedidos() {
        return pedidos;
    }

    public static void setPedidos(List<Pedido> pedidos) {
        PedidoController.pedidos = pedidos;
    }
    
    
    
    
    public List<Pedido> searchPedido(int id) throws IOException{
        
        Scanner scanner = new Scanner(System.in);
        
        List<Pedido> busca = new ArrayList<>();

        System.out.println("----------------------------------------------------------------");
        System.out.println("| Digite o id do Pedido:                                       |");
        String keySearch = scanner.nextLine();
        System.out.println("----------------------------------------------------------------");

        Pattern pattern = Pattern.compile(keySearch, Pattern.CASE_INSENSITIVE);
        Matcher matcher;

        int count = 1, results = 0;
        for (Pedido pedido : pedidos) {
            if (pedido != null){
                matcher = pattern.matcher(String.valueOf(pedido.getNumero()));

                if (matcher.find()) {
                    busca.add(pedido);
                    System.out.printf("""
                                       -------------- %02d --------------
                                       N°: %s\nCliente: %s\nData: %s
                                       --------------------------------\n
                                       """, count, pedido.getNumero(),
                            pedido.getCliente().getNome(), pedido.getDataHora());
                    count++;
                    results++;
                }
            }
        }

        if (results == 0) {
            throw new IOException("Nao foi encontrado nenhum Produto com esse nome.");
        }

        return busca;
    }
    
    public List<Pedido> searchPedido(Cliente cliente) throws IOException{
        
        Scanner scanner = new Scanner(System.in);
        
        List<Pedido> busca = new ArrayList<>();

        System.out.println("----------------------------------------------------------------");
        System.out.println("| Digite o nome do cliente:                                    |");
        String keySearch = scanner.nextLine();
        System.out.println("----------------------------------------------------------------");

        Pattern pattern = Pattern.compile(keySearch, Pattern.CASE_INSENSITIVE);
        Matcher matcher;

        int count = 1, results = 0;
        for (Pedido pedido : pedidos) {
            if (pedido != null){
                matcher = pattern.matcher(pedido.getCliente().getNome());

                if (matcher.find()) {
                    busca.add(pedido);
                    System.out.printf("""
                                       -------------- %02d --------------
                                       N°: %s\nCliente: %s\nData: %s
                                       --------------------------------\n
                                       """, count, pedido.getNumero(),
                            pedido.getCliente().getNome(), pedido.getDataHora());
                    count++;
                    results++;
                }
            }
        }

        if (results == 0) {
            throw new IOException("Nao foi encontrado nenhum Produto com esse nome.");
        }

        return busca;
    }
    
    
    /**
     * <p>
     * Adiciona um ou vários itens no pedido.</p>
     *
     * @since 1.0
     */
    public void addItem() {

        Scanner scanner = new Scanner(System.in);

        ProdutoController PController = new ProdutoController();
        
        try {
            List<Produto> aux = PController.searchProduto();

            System.out.println("""
                       ----------------------------------------------------------------
                       Selecione um produto:  """);
            int key = scanner.nextInt();
            scanner.nextLine();
            System.out.println("----------------------------------------------------------------");

            if (key <= 0 || key > aux.size()) {
                throw new IOException("Essa opçao nao é valida.");
            }

            Produto produto = Produto.getProdutos().get(
                    Produto.getProdutos().indexOf(aux.get(key - 1)));

            System.out.println("----------------------- Adicionar Item -------------------------");
            System.out.println("Digite a quantidade para adicionar: ");
            int opt = scanner.nextInt();
            System.out.println("----------------------------------------------------------------");

            for (int i = 0; i < opt; i++) {
                this.getItens().add(produto); 
                this.setPrecoTotal(produto.getPreco());
            }

            System.out.println("""
                       ----------------------------------------------------------------
                       |                                                              |
                       |                       Item Adicionado                        |
                       |                                                              |
                       ----------------------------------------------------------------\n\n""");

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * <p>
     * Remove um ou vários itens no pedido.</p>
     *
     * @throws java.io.IOException
     * @since 1.0
     */
    public void removeItem() throws IOException {

        Scanner scanner = new Scanner(System.in);
        ProdutoController PController = new ProdutoController();

        List<Produto> aux = PController.searchProduto(this.getItens());
//        Como fazer pra pesquisar produtos na lista de itens do pedido?

        try {

            System.out.println("""
                       ----------------------------------------------------------------
                       Selecione um produto:  """);
            int key = scanner.nextInt();
            scanner.nextLine();
            System.out.println("----------------------------------------------------------------");

            if (key <= 0 || key > aux.size()) {
                throw new IOException("Essa opçao nao é valida.");
            }

            Produto produto = aux.get(key - 1);

            System.out.println("------------------------ Remover Item --------------------------");
            System.out.println("Digite a quantidade de itens para remover: ");
            int opt = scanner.nextInt();
            System.out.println("----------------------------------------------------------------");

            for (int i = 0; i < opt; i++) {
                this.itens.remove(produto);
                this.precoTotal -= produto.getPreco();
            }
            System.out.println("""
                       ----------------------------------------------------------------
                       |                                                              |
                       |                        Item Removido                         |
                       |                                                              |
                       ----------------------------------------------------------------\n\n""");

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public void addDataHoraEntrega() {

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

        try {
            System.out.println("""
                           --------------- Adicionar momento de Entrega -------------------
                           Utilize os formatos 'DD/MM/YY HH:MM'
                           ----------------------------------------------------------------
                                                                        """);
            System.out.println("Digite o de entrega: ");
            LocalDateTime opt = LocalDateTime.parse(scanner.nextLine(), formatter);
            System.out.println("----------------------------------------------------------------");

            this.setDataHoraEntrega(opt.format(formatter));

            System.out.println("""
                           ----------------------------------------------------------------
                           |                                                              |
                           |                   Data e hora adicionados                    |
                           |                                                              |
                           ----------------------------------------------------------------\n\n""");
        } catch (DateTimeParseException e) {
            System.err.println(e.getMessage());
        }
    }

    public void adicionarPedido() {
        pedidos.add(this);
    }

}
