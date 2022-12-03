package services;

import appExceptions.FailedLoginException;
import entities.Usuario;
import entities.Administrador;
import entities.Funcionario;    
import java.io.IOException;
import java.util.Scanner;

public abstract class Login {
    
    private static Usuario loggedUser;

    public static Usuario getLoggedUser() {
        return loggedUser;
    }
    
    public static Usuario login(){
        
        Scanner scanner = new Scanner(System.in);
        
        Usuario usuario = null;
        
        while (usuario == null) {
            
            System.out.println("------------------ Login --------------------");
            System.out.println("Digite o Usuario: ");
            String user = scanner.nextLine();
            System.out.println("Digite a senha: ");
            String password = scanner.nextLine();
            System.out.println("----------------------------------------------\n\n");
        
            try{
                usuario = Login.authLogin(user, password);
                
                System.out.println("------ Login realizado com sucesso! ------");
                System.out.printf("%s", usuario.toString());
                System.out.println("------------------------------------------\n\n");
               
                
            }
            catch(FailedLoginException e){
                System.err.println(e.getMessage());
                System.out.println("\nNão foi possível Fazer o Login.\nVerifique Usuario e Senha.\n\n");
            }
        }
        
        return usuario;
    }
    
    public static Usuario authLogin(String user, String password) throws FailedLoginException{
        
        try{
            if(Funcionario.existeFuncionario(user)){
                Funcionario funcionario = Funcionario.findFuncionario(user);
                if(funcionario.getPassword().equals(password))
                    loggedUser = funcionario;
                else
                    throw new FailedLoginException("A senha inserida não corresponde.");
            }else
            if(Administrador.existeAdministrador(user)){
                Administrador administrador = Administrador.findAdministrador(user);
                if(administrador.getPassword().equals(password))
                    loggedUser = administrador;
                else
                    throw new FailedLoginException("A senha inserida não corresponde.");
            }
            else
                throw new FailedLoginException("Usuário não encontrado.");
        }
        catch(IOException e){
            System.err.println(e);
        }
        
        
        return loggedUser;
    }
    
}
