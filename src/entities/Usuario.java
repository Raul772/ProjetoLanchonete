
package entities;

import java.io.IOException;
import services.ID;

/**
 * <p>Essa classe representa um usuario do sistema.</p>
 * @author erica
 * @since 1.0
 */
public abstract class Usuario extends Pessoa {
    
//  Questão 05 - O sistema deverá armazenar de forma estática 15 colaboradores.
    private static Colaborador[] colaboradores; 
    
    private final int nivelDeAcesso;
    private String user;
    private String password;

//  Sobrecargas de Construtor
    
    public Usuario(int nivelDeAcesso) {
//      Questão 04 - Utilizar a palavra-chave super para implementar os construtores das subclasses.
        super();
        this.nivelDeAcesso = nivelDeAcesso;
    }

    public Usuario(String nome, String cpf, int nivelDeAcesso,String user, String password) throws IOException {
//      Questão 04 - Utilizar a palavra-chave super para implementar os construtores das subclasses.
        super(nome, cpf);
        this.nivelDeAcesso = nivelDeAcesso;
        setUser(user);
        setPassword(password);
    }

//  Getters e Setters

    /**
     * <p>Retorna o nível de acesso do usuario.</p>
     * @return int
     * @since 1.0
     */
    public int getNivelDeAcesso() {
        return nivelDeAcesso;
    }
    
//  Nível de acesso é constante por tanto é inicializado no construtor.

    /**
     * <p>Retorna o user desse usuario.</p>
     * @return String
     * @since 1.0
     */
    public String getUser() {
        return user;
    }

    /**
     * <p>Define um user para o usuario.</p>
     * @param user 
     * @since 1.0
     */
    public final void setUser(String user) {
        
        if(user.equals(""))
            this.user = ID.gerarID(super.getNome(), super.getCpf());
        else
            this.user = user;
    }
    
    /**
     * <p>Retorna a senha de acesso do usuario.</p>
     * @return String
     * @since 1.0
     */
    public String getPassword() {
        return this.password;
    }    

    /**
     * <p>Define uma senha para o usuario.</p>
     * @param password 
     * @since 1.0
     */
    public final void setPassword(String password) {
        if (password.equals(""))
            throw new IllegalArgumentException("A senha não pode ser vazia.");
        
        else
            this.password = password;
    }

    public static Colaborador[] getColaboradores() {
        return colaboradores;
    }

    public static void setColaboradores(Colaborador[] colaboradores) {
        Usuario.colaboradores = colaboradores;
    }
    
//  Métodos de Usuário
    
    /*
    public void registrarPedido() throws IOException{
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("---------------------- Registrar Pedido ------------------------");
        System.out.println("(0) Cadastrar um novo Cliente");
        System.out.println("(1) Utilizar Cliente ja cadastrado");
        System.out.println("----------------------------------------------------------------");
        int opt = scanner.nextInt();
        scanner.nextLine();
        
        if (opt == 0)
            this.incluirCliente();
        

        List<Cliente> aux = this.searchCliente();

        System.out.println("""
                       ----------------------------------------------------------------
                       Selecione um Cliente:  """);
        int key = scanner.nextInt();
        scanner.nextLine();
        System.out.println("----------------------------------------------------------------\n\n");

        if (key <= 0 || key > aux.size())
            throw new IOException("Essa opçao nao é valida.");

        Cliente cliente = Cliente.clientes.get(Cliente.findCliente(
                aux.remove(key-1).getIdCliente()));
            
        
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        
        do {
            System.out.println("""
                                   ------------------------- Menu Pedido --------------------------
                                   | (2) Adicionar item                                           |
                                   | (3) Remover item                                             |
                                   | (4) Adicionar Data e Hora de Entrega                         |
                                   | (5) Editar Status do Pedido                                  |
                                   | (6) Ver Pedido                                               |
                                   ----------------------------------------------------------------
                                   | (0) Cancelar                                                 |
                                   | (1) Salvar Pedido                                            |
                                   ----------------------------------------------------------------
                                   """);
            opt = scanner.nextInt();
            
            switch (opt) {
                case 0:
                    opt = 0;
                    break;
                case 1:
                    opt = 1;
                    break;
                case 2:
                    pedido.addItem();
                    break;
                case 3:
                    pedido.removeItem();
                    break;
                case 4:
                    pedido.addDataHoraEntrega();
                    break;
                case 5:
//                    pedido.editarStatus();
                    break;
                case 6:
                    System.out.println(pedido);
                    break;      
                default:
                    throw new AssertionError("Escolha uma opcao valida.");
            } 
        } while (opt != 0 && opt != 1);
        
        if (opt != 0)    
            pedido.adicionarPedido();

    }
   
    */
    
    /**
     * <p>Busca e retorna uma lista de Pedidos de acordo com a pesquisa do usuario.</p>
     * @param cliente
     * @return Uma lista de pedidos pesquisada pelo usuario.
     * @throws IOException 
     * @since 1.0
     */
    /*public List<Pedido> searchPedido(Cliente cliente) throws IOException{

        List<Pedido> busca = new ArrayList<>();

        Pattern pattern = Pattern.compile(cliente.getNome(), Pattern.CASE_INSENSITIVE);
        Matcher matcher;

        int count = 1, results = 0;
          for (Pedido pedido : Pedido.getPedidos()) {
            matcher = pattern.matcher(pedido.getCliente().getNome());
            
            if (matcher.find()) {
                busca.add(pedido);
                System.out.printf("""
                                   -------------- %02d --------------
                                   Codigo: %s\nCliente: %s\nData: %s\nEndereço: %s
                                   --------------------------------\n
                                   """,count, pedido.getNumero(),
                                   pedido.getCliente().getNome(), pedido.getDataHora(),
                                   pedido.getCliente().getEndereco());
                count++;
                results++;
            }
        }
        

        if (results == 0) {
            throw new IOException("Nao foi encontrado nenhum pedido para um cliente com esse nome.");
        }

        return busca; 
        
    }
    */
    /**
     * <p>
     * Edita campos de um pedido já registrado no sistema.</p>
     * @throws java.io.IOException
     * @since 1.0
     */
    /*public void editarPedido() throws IOException {

        Scanner scanner = new Scanner(System.in);

        List<Cliente> aux = this.searchCliente();

        System.out.println("""
                           ----------------------------------------------------------------
                           Selecione um Cliente:  """);
        int key = scanner.nextInt();
        scanner.nextLine();
        System.out.println("----------------------------------------------------------------");

        if (key <= 0 || key > aux.size()) {
            throw new IOException("Essa opçao nao é valida.");
        }

        Cliente cliente = aux.get(key - 1);
        
        List<Pedido> pedidos = this.searchPedido(cliente);
        
        System.out.println("""
                           ----------------------------------------------------------------
                           Selecione um Pedido:  """);
        key = scanner.nextInt();
        scanner.nextLine();
        System.out.println("----------------------------------------------------------------");

        if (key <= 0 || key > pedidos.size()) {
            throw new IOException("Essa opçao nao é valida.");
        }

        Pedido pedido = pedidos.get(key - 1);
        
        int opt;
        do {
            System.out.println("""
                                   ------------------------- Menu Pedido --------------------------
                                   | (2) Adicionar item                                           |
                                   | (3) Remover item                                             |
                                   | (4) Adicionar Data e Hora de Entrega                         |
                                   | (5) Editar Status do Pedido                                  |
                                   | (6) Ver Pedido                                               |
                                   ----------------------------------------------------------------
                                   | (0) Cancelar                                                 |
                                   | (1) Salvar Pedido                                            |
                                   ----------------------------------------------------------------
                                   """);
            opt = scanner.nextInt();
            
            switch (opt) {
                case 0:
                    opt = 0;
                    break;
                case 1:
                    opt = 1;
                    break;
                case 2:
                    pedido.addItem();
                    break;
                case 3:
                    pedido.removeItem();
                    break;
                case 4:
                    pedido.addDataHoraEntrega();
                    break;
                case 5:
//                    pedido.editarStatus();
                    break;
                case 6:
                    System.out.println(pedido);
                    break;      
                default:
                    throw new AssertionError("Escolha uma opcao valida.");
            } 
        } while (opt != 0 && opt != 1);

        System.out.println("""
                           ----------------------------------------------------------------
                           |                                                              |
                           |                        Pedido Editado                        |
                           |                                                              |
                           ----------------------------------------------------------------\n\n""");

    }
    */
//  Overrides
    
//  Questão 03 - Sobrescrever o método toString() de todas as classes implementadas.
    @Override
    public String toString(){
        return String.format("""
                             ----------------------------------------------------------------
                             %sNível de Acesso: %d\nSenha: %s
                             ----------------------------------------------------------------\n""", 
                super.toString(),getNivelDeAcesso(), getPassword());
    }
    
}
