package entities;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Pedido {
    
//  Questao 08 - Os pedidos e os clientes devem ser salvos de forma dinâmica no sistema.
    private static List<Pedido> pedidos = new ArrayList<>();
    
    private int numero;
    private Cliente cliente;
    private List<Produto> itens = new ArrayList<>();
    private String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss"));
    private String dataHoraEntrega;
    private double precoTotal;
    private String status;
    
    
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
    
//  Métodos de Pedido
    
    public static void menuPedido(Usuario user){
        Scanner scanner = new Scanner(System.in);
        int option;
        do {            
            System.out.println("""
                                   ------------------------- Menu Pedido --------------------------
                                   | (1) Registrar Pedido                                        |
                                   | (2) Editar Pedido                                           |
                                   | (3) Remover Pedido                                          |
                                   ----------------------------------------------------------------
                                   | (0) Voltar                                                  |
                                   ----------------------------------------------------------------
                                   """);
        
            option = scanner.nextInt();
        
            switch (option) {
                case 0:

                    break;
                case 1:
                    try {
                        user.registrarPedido();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        user.editarPedido();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                default:
                    throw new AssertionError();
            }
        } while (option != 0);
        
    }
    
    /**
    * <p>Busca e retorna uma lista de Produtos de acordo com a pesquisa do usuario.</p>
    * @return Uma lista de produtos pesquisado pelo usuario.
    * @param produtos
    * @throws IOException 
    * @since 1.0
    */
    public List<Produto> searchProduto(List<Produto> produtos) throws IOException{

        Scanner scanner = new Scanner(System.in);
        List<Produto> busca = new ArrayList<>();

        System.out.println("----------------------------------------------------------------");
        System.out.println("| Digite o nome do Produto:                                    |");
        String keySearch = scanner.nextLine();
        System.out.println("----------------------------------------------------------------");

        Pattern pattern = Pattern.compile(keySearch, Pattern.CASE_INSENSITIVE);
        Matcher matcher;

        int count = 1, results = 0;
          for (Produto produto : produtos) {
            matcher = pattern.matcher(produto.getNome());
            
            if (matcher.find()) {
                busca.add(produto);
                System.out.printf("""
                                   -------------- %02d --------------
                                   Produto: %s\nPreco: %s\ndescricao: %s
                                   --------------------------------\n
                                   """,count, produto.getNome(),
                                   produto.getPreco(),
                                   produto.getDescricao());
                count++;
                results++;
            }
        }
        

        if (results == 0) {
            throw new IOException("Nao foi encontrado nenhum produto com esse nome.");
        }
        return busca; 
    }
    
    /**
     * <p>Adiciona um ou vários itens no pedido.</p>
     * @since 1.0
     */
    public void addItem(){
        
        Scanner scanner = new Scanner(System.in);

        try {
            List<Produto> aux = this.searchProduto(Produto.getProdutos());

            System.out.println("""
                       ----------------------------------------------------------------
                       Selecione um produto:  """);
            int key = scanner.nextInt();
            scanner.nextLine();
            System.out.println("----------------------------------------------------------------");

            if (key <= 0 || key > aux.size())
                throw new IOException("Essa opçao nao é valida.");

            Produto produto = Produto.getProdutos().get(
                    Produto.getProdutos().indexOf(aux.get(key-1)));


            System.out.println("----------------------- Adicionar Item -------------------------");
            System.out.println("Digite a quantidade para adicionar: ");
            int opt = scanner.nextInt();
            System.out.println("----------------------------------------------------------------");


            for (int i = 0; i < opt; i++){
                this.itens.add(produto);
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
     * <p>Remove um ou vários itens no pedido.</p>
     * @throws java.io.IOException
     * @since 1.0
     */
    public void removeItem() throws IOException{
        
        Scanner scanner = new Scanner(System.in);
        
        List<Produto> aux = this.searchProduto(this.getItens());
            
        try {

            System.out.println("""
                       ----------------------------------------------------------------
                       Selecione um produto:  """);
            int key = scanner.nextInt();
            scanner.nextLine();
            System.out.println("----------------------------------------------------------------");

            if (key <= 0 || key > aux.size())
                throw new IOException("Essa opçao nao é valida.");

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
    
    public void addDataHoraEntrega(){
        
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
    
    public void adicionarPedido(){
        pedidos.add(this);
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
