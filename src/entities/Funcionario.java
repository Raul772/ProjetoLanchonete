
package entities;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Funcionario extends Pessoa{
    
    
    private long idFuncionario;
    
    public Funcionario(){}

    public Funcionario(long idFuncionario, String nome, String cpf) {
        super(nome, cpf);
        setIdFuncionario(idFuncionario);
    }

    public long getIdFuncionario() {
        return idFuncionario;
    }

    public final void setIdFuncionario(long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
    
    public Cliente incluirCliente(long id, String endereco, String telefone, String nome, String cpf) throws IOException{
        Path filePath = Path.of("C:","ProjetoLanchonete",
                            "registros", "clientes", 
                            String.format("%s.json", id));
        
        Gson gson = new Gson();
        
        if(!Files.exists(filePath.getParent()))
            Files.createDirectories(filePath.getParent());
        
        if(Files.exists(filePath)){ 
            Cliente cliente = 
                    gson.fromJson(Files.readString(filePath), Cliente.class);
            return cliente;
        }
        
        Cliente cliente = new Cliente(id, endereco, telefone, nome, cpf);
        Files.writeString(filePath, gson.toJson(cliente));
        return cliente;
    }
    
    
     @Override
    public String toString() {
        return String.format("%sID Funcionário: %d\n", 
                super.toString(), getIdFuncionario());
    }
}
