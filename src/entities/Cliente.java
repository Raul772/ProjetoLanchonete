package entities;

import java.io.IOException;
import java.util.List;
import services.ID;

/**
 * <p>Essa classe representa um Cliente no sistema.</p>
 * @author raulv
 * @since 1.0
 */
public class Cliente extends Pessoa{

//  Questão 08 - Os pedidos e os clientes devem ser salvos de forma dinâmica no sistema.
    public static List<Cliente> clientes;
    private static int clienteCount = 0;
   
    
    private String idCliente;
    private String endereco;
    private String telefone;
    
// Sobrecargas de Construtor
    
    public Cliente(){
//      Questão 04 - Utilizar a palavra-chave super para implementar os construtores das subclasses.
        super();
        aumentaClienteCount();
    }
    
    public Cliente(String idCliente, String endereco, String telefone, String nome, String cpf) {
//      Questão 04 - Utilizar a palavra-chave super para implementar os construtores das subclasses.
        super(nome, cpf);
        setEndereco(endereco);
        setTelefone(telefone);
        setIdCliente(idCliente);
        aumentaClienteCount();
    }

//  Getters e Setters

    /**
     * <p>Retorna o número de clientes instanciados.</p>
     * @return Int
     * @since 1.0
     */
    public static int getClienteCount() {
        return clienteCount;
    }

    /**
     * <p>Incrementa em uma unidade o número de instancias de Cliente criadas.</p>
     * @since 1.0
     */
    public static void aumentaClienteCount() {
        Cliente.clienteCount++;
    }
    
    /**
     * <p>Decrementa em uma unidade o número de intâncias de Cliente criadas.</p>
     * @since 1.0
     */
    public static void decrementaClienteCount() {
        Cliente.clienteCount--;
    }
    
    /**
     * <p>Retorna o ID do Cliente.</p>
     * @return String
     * @since 1.0
     */
    public String getIdCliente() {
        return idCliente;
    }

    /**
     * <p>Determina o ID do cliente a partir do parâmetro passado.</p>
     * @param idCliente 
     * @since 1.0
     */
    public final void setIdCliente(String idCliente) {
        
        if(idCliente.equals(""))
            if(super.getCpf().equals(""))
                this.idCliente = ID.gerarID(this.telefone, this.endereco);
            else
                this.idCliente = ID.gerarID(super.getCpf());
        else
            this.idCliente = idCliente; 
    }

    /**
     * <p>Retorna o endereço do cliente.</p>
     * @return String
     * @since 1.0
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * <p>Determina o endereço do cliente a partir do parâmetro passado.</p>
     * @param endereco 
     * @since 1.0
     */
    public final void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * <p>Retorna o telefone do cliente.</p>
     * @return String
     * @since 1.0
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * <p>Determina o telefone do cliente a partir do parâmetro passado.</p>
     * @param telefone 
     * @since 1.0
     */
    public final void setTelefone(String telefone) {
        
        if(!telefone.matches("[a-zA-Z]+|[-]+"))
            this.telefone = telefone;
        else
            throw new IllegalArgumentException("Telefone só pode conter números");
    }
    
//  Métodos de Cliente
    
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
    public static void deleteCliente(String idCliente) throws IOException{
         
            clientes.remove(findCliente(idCliente));
            decrementaClienteCount();
        
    }
    
    
//  Overrides
    
//  Questão 03 - Sobrescrever o método toString() de todas as classes implementadas.
    @Override
    public String toString() {
        return String.format("""
                             ----------------------------------------------------------------
                             %sID Cliente: %s\nEndereço: %s\nTelefone: %s
                             ----------------------------------------------------------------\n""", 
                super.toString(), getIdCliente(), getEndereco(), getTelefone());
    }
}
