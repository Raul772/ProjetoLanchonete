package entities;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Colaborador extends Usuario{
    
    
//  Sobrecargas de Construtor
    
    public Colaborador(){
        super(1);
    }

    public Colaborador(String nome, String cpf, String user, String password) {
        super(nome, cpf, 1, user, password);
        
    }

//  Getters e Setters
    
    
//  Métodos de Colaborador
    
    public static boolean existeColaborador(String user){

        Path filePath = Path.of("\\registros\\colaboradores", 
                    String.format("%s.json", user));
        
        return (Files.exists(filePath));
    }
    
    public boolean existeColaborador(){
        
        Path filePath = Path.of("\\registros\\colaboradores", 
                        String.format("%s.json", this.getUser()));
        
        return (Files.exists(filePath));
    }
    
    public static Colaborador findColaborador(String user) throws IOException{
        Gson gson = new Gson();
        Path filePath = Path.of("\\registros\\colaboradores", 
                            String.format("%s.json", user));
        
        if (existeColaborador(user)){
            return gson.fromJson(Files.readString(filePath), Colaborador.class);
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
