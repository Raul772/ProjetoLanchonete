package controllers;

import entities.Cliente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p></p>
 * @author raulv
 */
public class ClienteController{
    
//  Questão 08 - Os pedidos e os clientes devem ser salvos de forma dinâmica no sistema.
    private static List<Cliente> clientes = new ArrayList<>();
    private static int clienteCount = 0;

    
    public ClienteController(){
    }
    
    
    public static List<Cliente> getClientes() {
        return clientes;
    }

    public static void setClientes(List<Cliente> clientes) {
        ClienteController.clientes = clientes;
    }

    /**
     * <p>Retorna o número de clientes instanciados.</p>
     * @return Int
     * @since 1.0
     */
    public static int getClienteCount() {
        return clienteCount;
    }

    public static void setClienteCount(int clienteCount) {
        ClienteController.clienteCount = clienteCount;
    }
    
    
    
    /**
     * <p>Incrementa em uma unidade o número de instancias de Cliente criadas.</p>
     * @since 1.0
     */
    public static void aumentaClienteCount() {
        clienteCount++;
    }
    
    /**
     * <p>Decrementa em uma unidade o número de intâncias de Cliente criadas.</p>
     * @since 1.0
     */
    private static void decrementaClienteCount() {
        clienteCount--;
    }
        
    /**
     * <p>Retorna true se existir um cliente cadastrado com o mesmo idCliente passado por parâmetro.</p>
     * <p>Retorna false no caso contrário.</p>
     * @param idCliente
     * @return Boolean
     * @since 1.0
     */
    public static boolean existeCliente(String idCliente){
        
        Cliente clienteAux = null;
        
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente().equals(idCliente)){
                clienteAux = cliente;
                break;
            }
        }

        return (clienteAux == null)? false : true;
    }
    
    /**
     * <p>Retorna o index do cliente cadastrado com o mesmo idCliente passado por parâmetro, caso seja encontrado.</p>
     * @param idCliente
     * @return Int
     * @throws IOException 
     * @since 1.0
     */
    public static int findCliente(String idCliente) throws IOException{
       
        int index = -1;
        
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente().equals(idCliente)) {
                index = clientes.indexOf(cliente);
                break;
            }       
        }
        
        if(index == -1) 
            throw new IOException("Não foi possível encontrar o registro.\n\n");
        
        
        return index;
    }
    
    /**
     * <p>Remove um cliente do sistema retirando-o da lista e decrementando a contagem de instâncias.</p>
     * @param idCliente
     * @throws IOException
     * @since 1.0
     */
    private static void deleteCliente(String idCliente) throws IOException{
         
            clientes.remove(findCliente(idCliente));
            decrementaClienteCount();
    }
    
    /**
    * <p>Imprime no console uma lista de todos os clientes cadastrados.</p>
    * @since 1.0
    */
    public void listarClientes(){
        
        System.out.println("\n================================================================");
        int count = 1;
        for (Cliente cliente : clientes) {
            if (cliente != null) {
            
                System.out.printf("""
                                   -------------- %02d --------------
                                   Nome: %s\nCPF: %s\nTelefone: %s\nEndereço: %s
                                   --------------------------------\n
                                   """,count, cliente.getNome(),
                                   cliente.getCpf(), cliente.getTelefone(),
                                   cliente.getEndereco());
                count++;
            }
        }
        System.out.println("================================================================\n");
    }
    
    /**
     * <p>Busca e retorna um Cliente de acordo com a escolha do usuario.</p>
     * @return Um cliente escolhido pelo usuario.
     * @throws IOException 
     * @since 1.0
     */
    public List<Cliente> searchCliente() throws IOException{

        Scanner scanner = new Scanner(System.in);
        List<Cliente> busca = new ArrayList<>();

        System.out.println("----------------------------------------------------------------");
        System.out.println("| Digite o nome do Cliente:                                    |");
        String keySearch = scanner.nextLine();
        System.out.println("----------------------------------------------------------------");

        Pattern pattern = Pattern.compile(keySearch, Pattern.CASE_INSENSITIVE);
        Matcher matcher;

        int count = 1, results = 0;
          for (Cliente cliente : clientes) {
            matcher = pattern.matcher(cliente.getNome());
            
            if (matcher.find()) {
                busca.add(cliente);
                System.out.printf("""
                                   -------------- %02d --------------
                                   Nome: %s\nCPF: %s\nTelefone: %s\nEndereço: %s
                                   --------------------------------\n
                                   """,count, cliente.getNome(),
                                   cliente.getCpf(), cliente.getTelefone(),
                                   cliente.getEndereco());
                count++;
                results++;
            }
        }
        

        if (results == 0) {
            throw new IOException("Nao foi encontrado nenhum cliente com esse nome.");
        }

        return busca; 
        
    }
    
//  Questao 06 - Deve ser possível cadastrar os clientes no sistema, alterar ou editar seus atributos;
    /**
     * <p>Edita campos de um cliente já cadastrado no sistema.</p>
     * @throws java.io.IOException
     * @since 1.0
     */
    public void editarCliente() throws IOException{
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("----------------------- Editar Cliente -------------------------");
        
        List<Cliente> aux = this.searchCliente();
        
        System.out.println("""
                           ----------------------------------------------------------------
                           Selecione um Cliente:  """);
        int key = scanner.nextInt();
        scanner.nextLine();
        System.out.println("----------------------------------------------------------------");
        
        if (key <= 0 || key > aux.size())
            throw new IOException("Essa opçao nao é valida.");
        
        Cliente cliente = clientes.get(findCliente(
                aux.remove(key-1).getIdCliente())) ;
        
        
        System.out.println("----------------------- Editar Cliente -------------------------");
        System.out.println("|       Deixe o campo em branco caso nao queira edita-lo       |");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Digite o nome do Cliente: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF do Cliente: ");
        String cpfNumber = scanner.nextLine();
        System.out.println("Digite o Telefone do Cliente: ");
        String telefone = scanner.nextLine();
        System.out.println("Digite o Endereço do Cliente: ");
        String endereco = scanner.nextLine();
        System.out.println("Digite o ID do Cliente: ");
        String idCliente = scanner.nextLine();
        System.out.println("----------------------------------------------------------------");

        if (!nome.equals("")) cliente.setNome(nome);
        if (!cpfNumber.equals("")) cliente.setCpf(cpfNumber);
        if (!telefone.equals("")) cliente.setTelefone(telefone);
        if (!endereco.equals("")) cliente.setEndereco(endereco);
        if (!idCliente.equals("")) cliente.setIdCliente(idCliente);
        
        System.out.println("""
                           ----------------------------------------------------------------
                           |                                                              |
                           |                       Cliente Editado                        |
                           |                                                              |
                           ----------------------------------------------------------------\n\n""");
       
    }
    
//  Questao 06 - Deve ser possível cadastrar os clientes no sistema, alterar ou editar seus atributos;
    /**
     * <p>Inclui um novo cliente ao sistema.</p>
     * @throws java.io.IOException
     * @since 1.0
     */
    public void incluirCliente() throws IOException{
        
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.println("---------------------- Incluir Cliente ------------------------");
        System.out.println("Digite quantos clientes gostaria de incluir: ");
        int quant = scanner.nextInt();
        System.out.println("----------------------------------------------------------------");
        scanner.nextLine();
        
        for (int i = 0; i < quant; i++) {
        
            System.out.printf("--------------------- Incluir Cliente %02d -----------------------\n", i + 1);
            System.out.println("Digite o nome do Cliente: ");
            String nome = scanner.nextLine();
            System.out.println("Digite o CPF do Cliente: ");
            String cpfNumber = scanner.nextLine();
            System.out.println("Digite o Telefone do Cliente: ");
            String telefone = scanner.nextLine();
            System.out.println("Digite o Endereço do Cliente: ");
            String endereco = scanner.nextLine();
            System.out.println("Digite o ID do Cliente: ");
            String idCliente = scanner.nextLine();
            System.out.println("----------------------------------------------------------------");

            if (existeCliente(idCliente)) {
                throw new IOException("""
                                             -----------------------------------------------------------------
                                             |                  O Cliente já foi cadastrado.                 |
                                             -----------------------------------------------------------------""");
            }

    //      Criar novo objeto Cliente caso esse ainda não esteja registrado.
            Cliente cliente = new Cliente(idCliente, endereco, telefone, nome, cpfNumber);

    //      Adicionar Cliente na lista de clientes.
            clientes.add(cliente);
            
        }
    }
    
    /**
     * <p>Remove um cliente do sistema.</p>
     * @throws java.io.IOException
     * @since 1.0
     */
    public void excluirCliente() throws IOException{
        Scanner scanner = new Scanner(System.in);
        
        List<Cliente> aux = this.searchCliente();
        
        System.out.println("""
                           ----------------------------------------------------------------
                           Selecione um Cliente:  """);
        int key = scanner.nextInt();
        scanner.nextLine();
        System.out.println("----------------------------------------------------------------");
        
        if (key <= 0 || key > aux.size())
            throw new IOException("Essa opçao nao é valida.");
        
        Cliente cliente = clientes.get(findCliente(
                aux.remove(key-1).getIdCliente())) ;
        
        try{

            deleteCliente(cliente.getIdCliente());
            
            System.out.println("""
                           ----------------------------------------------------------------
                           |                                                              |
                           |                      Cliente Removido                        |
                           |                                                              |
                           ----------------------------------------------------------------\n\n""");
        }catch(IOException e){
            System.err.printf(e.getMessage());
        }        
        
    }

    
    
    @Override
    public String toString() {
        return "Classe responsável pelo controle e manipulação de Clientes;";
    }    
}
