package services;

import appExceptions.FailedLoginException;
import entities.Administrador;
import entities.Colaborador;
import entities.Usuario;
import java.util.Scanner;

/**
 * <p>Classe responsável pelos métodos de Login</p>
 * @author raulv
 * @since 1.0
 */
public abstract class Login {
    
    private static Usuario loggedUser = null;

    /**
     * <p>Retorna o usuario logado sem uma requisição de login.</p>
     * @return Usuario
     */
    public static Usuario getLoggedUser() {
        return loggedUser;
    }
    
    /**
     * <p>Realiza a requisição de login e retorna o usuario logado.</p>
     * @return Usuario
     * @since 1.0
     */
    public static Usuario login(){
        
        Scanner scanner = new Scanner(System.in);
        
        Usuario usuario = null;
        
        while (usuario == null) {
            
            System.out.println("---------------------------- Login ------------------------------");
            System.out.println("| Digite o Usuario:                                             |");
            String user = scanner.nextLine();
            System.out.println("| Digite a senha:                                               |");
            String password = scanner.nextLine();
            System.out.println("-----------------------------------------------------------------\n\n");
        
            try{
//              a chamada de função a seguir pode gerar uma exceção do tipo FailedLoginException.
                usuario = Login.authLogin(user, password);
                
//              O código abaixo só será executado se nenhuma exceção do tipo FailedLoginException
//              for lançada.

                System.out.printf("""
                                    -----------------------------------------------------------------
                                    |                  Login Realizado com Sucesso                  |
                                    -----------------------------------------------------------------
                                                                                                   
                                    Usuario: %-55s\nCPF: %-16s\n                                     
                                    -----------------------------------------------------------------\n\n\n""",
                                usuario.getNome(), usuario.getCpf());
                
                
            }
            
//          Se uma exeção do tipo FailedLoginException for lançada, ela será tratada aqui: 
            catch(FailedLoginException e){
                System.err.println(e.getMessage());
                System.out.println("\nNão foi possível Fazer o Login.\nVerifique Usuario e Senha.\n\n");
            }
        }
        
        return usuario;
    }

    /**
     * <p>Faz a autenticação de login conferindo user e senha do usuario.</p>
     * @param user
     * @param password
     * @return Usuario
     * @throws FailedLoginException 
     * @since 1.0
     */
    public static Usuario authLogin(String user, String password) throws FailedLoginException{
        
        Usuario[] colaboradores = Usuario.getColaboradores();
        
        for (int i = 0; i < 15; i++) {
//          Verifica se há algum usuário com esse username

            if(colaboradores[i] != null &&
                    colaboradores[i].getUser().equals(user)){
                
//              Verifica se a senha do usuario encontrado coincide com a digitada
                if(colaboradores[i].getPassword().equals(password)){
                    loggedUser = colaboradores[i];
                    break;
                } else{
//                  Caso a senha não seja encontrada
                    throw new FailedLoginException("A senha digitada não está correta.");
                }
            }else{
//              Caso o usuario não seja encontrado
                if(i == 14)
                    throw new FailedLoginException("O usuario digitado não foi encontrado.");
            }        
                    
        }
        
//      Retorna o usuário logado
        return loggedUser;
    }
    
//  Questão 03 - Sobrescrever o método toString() de todas as classes implementadas.
    @Override
    public String toString() {
        return "Login : Classe responsável pelas funcionalidades de login do sistema.";
    }  
}
