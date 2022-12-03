package entities;

import com.google.gson.Gson;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import services.ID;

public class Cliente extends Pessoa{

    private String idCliente;
    private String endereco;
    private String telefone;
    
// Sobrecargas de Construtor
    
    public Cliente(){}
    
    public Cliente(String idCliente, String endereco, String telefone, String nome, String cpf) {
        super(nome, cpf);
        setEndereco(endereco);
        setTelefone(telefone);
        setIdCliente(idCliente);
    }

//  Getters e Setters
    
    public String getIdCliente() {
        return idCliente;
    }

    public final void setIdCliente(String idCliente) {
        
        if(idCliente.equals(""))
            if(super.getCpf().equals(""))
                this.idCliente = ID.gerarID(this.telefone, this.endereco);
            else
                this.idCliente = ID.gerarID(super.getCpf());
        else
            this.idCliente = idCliente; 
    }

    public String getEndereco() {
        return endereco;
    }

    public final void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public final void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
//  Métodos de Cliente
    
    public static boolean existeCliente(String idCliente){

        Path filePath = Path.of("\\registros\\clientes", 
                    String.format("%s.json", idCliente));
        
        return (Files.exists(filePath));
    }
    
    public boolean existeCliente(){
        
        Path filePath = Path.of("\\registros\\clientes", 
                        String.format("%s.json", this.getIdCliente()));
        
        return (Files.exists(filePath));
    }
    
    public static Cliente findCliente(String idCliente) throws IOException{
        Gson gson = new Gson();
        Path filePath = Path.of("\\registros\\clientes", 
                            String.format("%s.json", idCliente));
        
        if (existeCliente(idCliente)){
            return gson.fromJson(Files.readString(filePath), Cliente.class);
        }else{
            throw new IOException("Não foi possível encontrar o registro.");
        }
    }
    
//  Overrides
    
    @Override
    public String toString() {
        return String.format("%sID Cliente: %s\nEndereço: %s\nTelefone: %s\n", 
                super.toString(), getIdCliente(), getEndereco(), getTelefone());
    }
}
