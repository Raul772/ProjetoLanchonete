package entities;

public class Cliente extends Pessoa{

    protected static long idClienteGlobal;
    private long idCliente;
    private String endereco;
    private String telefone;
    
    public Cliente(){}

    public Cliente(int idCliente, String endereco, String telefone, String nome, String cpf) {
        super(nome, cpf);
        setIdCliente(idCliente);
        setEndereco(endereco);
        setTelefone(telefone);
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    @Override
    public String toString() {
        return String.format("%sID Cliente: %d\nEndereço: %s\nTelefone: %s\n", 
                super.toString(), getIdCliente(), getEndereco(), getTelefone());
    }
}
