package models;

import controllers.ClienteController;
import services.ID;
import java.io.IOException;

/**
 * <p>Essa classe representa um Cliente no sistema.</p>
 * @author raulv
 * @since 1.0
 */
public class Cliente extends Pessoa{
   
    private String idCliente;
    private String endereco;
    private String telefone;
    
// Sobrecargas de Construtor
    
    public Cliente(){
//      Questão 04 - Utilizar a palavra-chave super para implementar os construtores das subclasses.
        super();
        ClienteController.aumentaClienteCount();
    }
    
    public Cliente(String idCliente, String endereco, String telefone, String nome, String cpf) throws IOException {
//      Questão 04 - Utilizar a palavra-chave super para implementar os construtores das subclasses.
        super(nome, cpf);
        setEndereco(endereco);
        setTelefone(telefone);
        setIdCliente(idCliente);
        ClienteController.aumentaClienteCount();
    }

//  Getters e Setters
    
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
