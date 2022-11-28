package services;

import entities.Pessoa;
import entities.Funcionario;
import com.google.gson.Gson;

public class Login {
    
    private String user;
    private String password;
    private Pessoa currentUser;
    
    private Gson gson = new Gson();

    public Login(String user, String password) {
        this.user = user;
        this.password = password;
    }
    
    private Pessoa authLogin(){
        
        return new Funcionario();
    }
    
}
