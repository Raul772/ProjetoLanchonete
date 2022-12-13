package projetolanchonete;

import services.Login;

public class ProjetoLanchonete {

    public static void main(String[] args) {
       
        Login.login();
        
        Sistema.menu(Login.getLoggedUser());
       
        
       
    }
    
}
