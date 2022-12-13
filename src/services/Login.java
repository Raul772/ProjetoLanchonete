package services;

import appExceptions.FailedLoginException;
import entities.Usuario;
import entities.Administrador;
import entities.Colaborador;    
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
//              a chamada de função a seguir pode gerar uma exceção do tipo FailedLoginException.
                usuario = Login.authLogin(user, password);
                
//              O código abaixo só será executado se nenhuma exceção do tipo FailedLoginException
//              for lançada.

                System.out.println("------ Login realizado com sucesso! ------");
                System.out.printf("%s", usuario.toString());
                System.out.println("------------------------------------------\n\n");
               
                
            }
            
//          Se uma exeção do tipo FailedLoginException for lançada, ela será tratada aqui: 
            catch(FailedLoginException e){
                System.err.println(e.getMessage());
                System.out.println("\nNão foi possível Fazer o Login.\nVerifique Usuario e Senha.\n\n");
            }
        }
        
        return usuario;
    }
    
    public static Usuario authLogin(String user, String password) throws FailedLoginException{
        
        try{
            
//          Confere se existe um funcionario cadastrado com esse usuário.
            if(Colaborador.existeColaborador(user)){
//              Cria um objeto do funcionario cadastrado para a conferencia da senha.
                Colaborador funcionario = Colaborador.findColaborador(user);
                
//              Confere se a senha digitada pelo usuario é igual ao do cadastro.
                if(funcionario.getPassword().equals(password))
                    loggedUser = funcionario;
                else
//                  Lança uma exceção caso a senha não esteja correta visto que nesse ponto
//                  somente senha incorreta poderia falhar o login.
                    throw new FailedLoginException("A senha inserida não corresponde.");
                
//           Caso não seja encontrado um funcionario com esse usuario, 
//           verifica se há um administrador com esse usuario.
            }else
                
//          Confere se existe um administrador cadastrado com esse usuário.
            if(Administrador.existeAdministrador(user)){
//              Cria um objeto do Administrador cadastrado para a conferencia da senha.
                Administrador administrador = Administrador.findAdministrador(user);
                
//              Confere se a senha digitada pelo usuario é igual ao do cadastro.
                if(administrador.getPassword().equals(password))
                    loggedUser = administrador;
                else
//                  Lança uma exceção caso a senha não esteja correta visto que nesse ponto
//                  somente senha incorreta poderia falhar o login.
                    throw new FailedLoginException("A senha inserida não corresponde.");
            }
            else
//              Caso não tenha sido encontrado nenhum funcionario ou administrador a partir do usuario digitado
//              lançar uma exceção com mensagem de usuario não encontrado.
                throw new FailedLoginException("Usuário não encontrado.");
        }
//      Tratamento de exceção relacionada a abertura de arquivos.
        catch(IOException e){
            System.err.println(e);
        }
        
//      Retorna o usuário logado
        return loggedUser;
    }
    
}
