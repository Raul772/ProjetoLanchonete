package entities;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Funcionario extends Usuario{
    
    
//  Sobrecargas de Construtor
    
    public Funcionario(){
        super(1);
    }

    public Funcionario(String nome, String cpf, String user, String password) {
        super(nome, cpf, 1, user, password);
        
    }

//  Getters e Setters
    
    
//  Métodos de Funcionario
    
    public static boolean existeFuncionario(String user){

        Path filePath = Path.of("\\registros\\funcionarios", 
                    String.format("%s.json", user));
        
        return (Files.exists(filePath));
    }
    
    public boolean existeFuncionario(){
        
        Path filePath = Path.of("\\registros\\funcionarios", 
                        String.format("%s.json", this.getUser()));
        
        return (Files.exists(filePath));
    }
    
    public static Funcionario findFuncionario(String user) throws IOException{
        Gson gson = new Gson();
        Path filePath = Path.of("\\registros\\funcionarios", 
                            String.format("%s.json", user));
        
        if (existeFuncionario(user)){
            return gson.fromJson(Files.readString(filePath), Funcionario.class);
        }else{
            throw new IOException("Não foi possível encontrar o registro.");
        }
    }
    
//  Overrides
    
    @Override
    public String toString() {
        return String.format("%sUser Funcionário: %s\n", 
                super.toString(), getUser());
    }
}
