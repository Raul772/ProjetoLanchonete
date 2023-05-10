package controllers;

import models.Produto;
import models.Pedido;
import models.Cliente;
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
public class PedidoController{
    
    
    private static List<Pedido> pedidos = new ArrayList<>();

    public static List<Pedido> getPedidos() {
        return pedidos;
    }

    public static void setPedidos(List<Pedido> pedidos) {
        PedidoController.pedidos = pedidos;
    }
    
    
    
    
    public List<Pedido> searchPedido() throws IOException{
        
        Scanner scanner = new Scanner(System.in);
        
        List<Pedido> busca = new ArrayList<>();

        System.out.println("----------------------------------------------------------------");
        System.out.println("| Digite o numero do Pedido:                                   |");
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
    
    public List<Pedido> searchPedidoCliente() throws IOException{
        
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
                                   ----------------------------------------------------------------
                                   | Pedido Numero: %s
                                   | Cliente: %s                                           
                                   | Data e Hora: %s
                                   | Valor Total: %s
                                   | Status: %s
                                   ----------------------------------------------------------------\n""",
                         pedido.getNumero(), pedido.getCliente().getNome(),
                         pedido.getDataHora(), pedido.getPrecoTotal(), pedido.getStatus());
                    count++;
                    results++;
                }
            }
        }

        if (results == 0) {
            throw new IOException("Nao foi encontrado nenhum pedido com esse nome.");
        }

        return busca;
    }
    
    
    /**
     * <p>
     * Adiciona um ou vários itens no pedido.</p>
     *
     * @param pedido
     * @since 1.0
     */
    public void addItem(Pedido pedido) {

        Scanner scanner = new Scanner(System.in);

        ProdutoController PController = new ProdutoController();
        
        System.out.println("""
                       ----------------------------------------------------------------
                       Quantos produtos deseja adicionar? """);
        int n = scanner.nextInt();
        scanner.nextLine();
        System.out.println("----------------------------------------------------------------");
        
        for (int j = n; j > 0; j--) {
            try {
                List<Produto> aux = PController.searchProduto(ProdutoController.getProdutos());

                System.out.println("""
                           ----------------------------------------------------------------
                           Selecione um produto:  """);
                int key = scanner.nextInt();
                scanner.nextLine();
                System.out.println("----------------------------------------------------------------");

                if (key <= 0 || key> aux.size()) {
                    throw new IOException("Essa opçao nao é valida.");
                }

                Produto produto = ProdutoController.getProdutos().get(
                        ProdutoController.getProdutos().indexOf(aux.get(key - 1)));

                System.out.println("----------------------- Adicionar Item -------------------------");
                System.out.println("Digite a quantidade para adicionar: ");
                int opt = scanner.nextInt();
                System.out.println("----------------------------------------------------------------");

                for (int i = 0; i < opt; i++) {
                    pedido.getItens().add(produto); 
                    pedido.setPrecoTotal(produto.getPreco());
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
    }

    /**
     * <p>
     * Remove um ou vários itens no pedido.</p>
     *
     * @param pedido
     * @throws java.io.IOException
     * @since 1.0
     */
    public void removeItem(Pedido pedido) throws IOException {

        Scanner scanner = new Scanner(System.in);
        ProdutoController PController = new ProdutoController();

        System.out.println("""
                       ----------------------------------------------------------------
                       Quantos produtos diferentes deseja remover? """);
        int n = scanner.nextInt();
        scanner.nextLine();
        System.out.println("----------------------------------------------------------------");
        
        for (int j = n; j > 0; j--) {
            
            List<Produto> aux = PController.searchProduto(pedido.getItens());

            try {

                System.out.println("""
                           ----------------------------------------------------------------
                           Selecione um tipo de produto:  """);
                int key = scanner.nextInt();
                scanner.nextLine();
                System.out.println("----------------------------------------------------------------");

                if (key <= 0 || key > aux.size()) {
                    throw new IOException("Essa opçao nao é valida.");
                }

                Produto produto = aux.get(key - 1);

                System.out.println("------------------------ Remover Item --------------------------");
                System.out.println(produto.getNome());
                System.out.println("Digite a quantidade para remover: ");
                int opt = scanner.nextInt();
                System.out.println("----------------------------------------------------------------");

                for (int i = 0; i < opt; i++) {
                    pedido.getItens().remove(produto);
                    double novopreco = pedido.getPrecoTotal() - produto.getPreco();
                    pedido.setPrecoTotal(-novopreco);
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
    }

    public void addDataHoraEntrega(Pedido pedido) {

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

            pedido.setDataHoraEntrega(opt.format(formatter));

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
    
    public void addStatus(Pedido pedido) {

        Scanner scanner = new Scanner(System.in);

        
        System.out.println("""
                       ---------------------- Adicionar Status ------------------------
                                                                    """);
        System.out.println("Digite o Status: ");
        String aux = scanner.nextLine();
        System.out.println("----------------------------------------------------------------");

        pedido.setStatus(aux);

        System.out.println("""
                       ----------------------------------------------------------------
                       |                                                              |
                       |                      Status adicionado                       |
                       |                                                              |
                       ----------------------------------------------------------------\n\n""");
        
    }

    public void adicionarPedido() throws IOException {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("""
                           ----------------------------------------------------------------
                           |                    Registrar Novo Pedido                     |
                           ----------------------------------------------------------------""");
        
        List<Cliente> aux = new ClienteController().searchCliente();
    
        System.out.println("""
                           ----------------------------------------------------------------
                           | Selecione um Cliente:                                        |""");
        int key = scanner.nextInt();
        scanner.nextLine();
        System.out.println("----------------------------------------------------------------");
        
        
        if (key <= 0 || key > aux.size()) {
                throw new IOException("Essa opçao nao é valida.");
            }

            Cliente cliente = ClienteController.getClientes().get(
                    ClienteController.getClientes().indexOf(aux.get(key - 1)));
        
        
        Pedido pedido = new Pedido();    
        this.addItem(pedido);
        pedido.setCliente(cliente);
        getPedidos().add(pedido);
    }
    
    public void editarPedido() throws IOException{
       
        Scanner scanner = new Scanner(System.in);
        
        List<Pedido> aux = searchPedido();
        
        System.out.println("""
                           ----------------------------------------------------------------
                           Selecione um Pedido:  """);
        int key = scanner.nextInt();
        scanner.nextLine();
        System.out.println("----------------------------------------------------------------");

        if (key <= 0 || key > aux.size()) {
            throw new IOException("Essa opçao nao é valida.");
        }

        Pedido pedidoAux = pedidos.remove(pedidos.indexOf(aux.remove(key - 1)));
        int opt;
        
        do{
        System.out.println("------------------------ Editar Pedido -------------------------");
        System.out.println("|              Selecione o campo que quer editar               |");
        System.out.println("----------------------------------------------------------------");
        System.out.println("|(1) Cliente                                                   |");
        System.out.println("|(2) Data de Entrega                                           |");
        System.out.println("|(3) Status do Pedido                                          |");
        System.out.println("|(4) Adicionar Item                                            |");
        System.out.println("|(5) Remover Item                                              |");
        System.out.println("----------------------------------------------------------------");
        System.out.println("|(0) Cancelar                                                  |");
        System.out.println("|(6) Salvar                                                    |");
        System.out.println("----------------------------------------------------------------");
        
        opt = scanner.nextInt();
        scanner.nextLine();
        
            switch (opt) {
                case 1 -> {
                    ClienteController CController = new ClienteController();
                    List<Cliente> cAux = CController.searchCliente();
                    
                    System.out.println("""
                           ----------------------------------------------------------------
                           Selecione um Cliente:  """);
                    int key1 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("----------------------------------------------------------------");

                    if (key1 <= 0 || key1 > aux.size()) {
                        throw new IOException("Essa opçao nao é valida.");
                    }
                    
                    List<Cliente> clientes = ClienteController.getClientes();
                    
                    pedidoAux.setCliente(clientes.get(
                            clientes.indexOf(cAux.remove(key1 - 1))));
                }
                case 2 -> {
                    addDataHoraEntrega(pedidoAux); 
//              Como editar um pedido sem salvar alterações ao cancelar
                }
                case 3 -> {
                    addStatus(pedidoAux);
                }
                case 4 -> {
                    addItem(pedidoAux);
                }
                case 5 -> {
                    removeItem(pedidoAux);
                }
                case 0 -> {
                    return;
                }
                case 6 -> {
                    getPedidos().add(pedidoAux);
                }
                default -> {
                    System.out.println("Digite uma opçao valida.");
                }
            }
        }while(opt != 0 || opt != 6);
        
        System.out.println("""
                           ----------------------------------------------------------------
                           |                                                              |
                           |                       Produto Editado                        |
                           |                                                              |
                           ----------------------------------------------------------------\n\n""");

        
        
    }
    
    public void removerPedido() throws IOException {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("""
                           ----------------------------------------------------------------
                           |                        Remover Pedido                        |
                           ----------------------------------------------------------------""");
        
        
        List<Pedido> pedidosBusca =  searchPedidoCliente();
        
        System.out.println("""
                           ----------------------------------------------------------------
                           | Selecione um Pedido para remover:                            |""");
        int key = scanner.nextInt();
        scanner.nextLine();
        System.out.println("----------------------------------------------------------------");
        
        
        if (key <= 0 || key > pedidosBusca.size()) {
                throw new IOException("Essa opçao nao é valida.");
            }

            Pedido pedido = PedidoController.getPedidos().get(
                    PedidoController.getPedidos().indexOf(pedidosBusca.get(key - 1)));
        
            
            
        PedidoController.getPedidos().remove(pedido);
        
        System.out.println("""
                           ----------------------------------------------------------------
                           |                       Pedido Removido                        |
                           ----------------------------------------------------------------""");
            
    }
}
