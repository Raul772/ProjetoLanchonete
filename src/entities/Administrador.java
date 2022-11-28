package entities;

import com.google.gson.Gson;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

public class Administrador extends Pessoa{
    
    protected static long id = 0;
    private long idAdministrador;
    
    public Administrador(){}

    public Administrador(String nome, String cpf) {
        super(nome, cpf);
        setIdAdministrador();
    }
    
    public Administrador(long idAdministrador, String nome, String cpf) {
        super(nome, cpf);
        setIdAdministrador(idAdministrador);
    }

    public final void setIdAdministrador(long idAdministrador) {
        this.idAdministrador = idAdministrador;
    }
    
     public final void setIdAdministrador() {
        this.idAdministrador = id + 1;
        Administrador.id = this.idAdministrador;
    }
    
    public long getIdAdministrador() {
        return idAdministrador;
    }
    
    public Funcionario incluirFuncionario(long id, String nome, String cpf) throws IOException{
        Path filePath = Path.of("C:","ProjetoLanchonete",
                            "registros", "funcionarios", 
                            String.format("%s.json", id));
        
        Gson gson = new Gson();
        
        if(!Files.exists(filePath.getParent()))
            Files.createDirectories(filePath.getParent());
        
        if(Files.exists(filePath)){ 
            Funcionario funcionario = 
                    gson.fromJson(Files.readString(filePath), Funcionario.class);
            return funcionario;
        }
        
        Funcionario funcionario = new Funcionario(id, nome, cpf);
        Files.writeString(filePath, gson.toJson(funcionario));
        return funcionario;
    }
    
    @Override
    public String toString() {
        return String.format("%sID Administrador: %d\n", 
                super.toString(), getIdAdministrador());
    }
    
}
