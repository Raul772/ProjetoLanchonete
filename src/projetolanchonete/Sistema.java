
package projetolanchonete;

import entities.Administrador;
import entities.Usuario;
import java.util.Scanner;

public class Sistema {
    
    
    
    public static void menu(Usuario user){
            
        switch (user.getNivelDeAcesso()) {
            case 1:
                    menuFuncionario(user);
                break;
                
            case 2:
                    menuAdministrador((Administrador)user);
                break;
            default:
                throw new AssertionError();
        }  
    }

    private static void menuFuncionario(Usuario user){

        int option = 1;
        Scanner scanner = new Scanner(System.in);
        
        while(option != 0){
            System.out.println("--------------------- Menu ---------------------");
            System.out.println("(1) Cadastrar Cliente");
            System.out.println("(2) Remover Cliente");
            System.out.println("(0) Sair");
            System.out.println("------------------------------------------------");
            option = scanner.nextInt();
            
            switch (option) {
                case 0:
                    System.out.println("**** Tem Certeza que quer sair?\n(0) Sim\n(1) Não");
                    option = scanner.nextInt();
                    break;
                
                case 1:
                    user.incluirCliente();
                    break;
                case 2: 
                    
                    break;

                default:
                    
                    break;
            }
        }
    }
    
    private static void menuAdministrador(Administrador user){

        int option = 1;
        Scanner scanner = new Scanner(System.in);
        
        while(option != 0){
            System.out.println("--------------------- Menu ---------------------");
            System.out.println("(1) Cadastrar Cliente");
            System.out.println("(2) Remover Cliente");
            System.out.println("(3) Cadastrar Funcionario");
            System.out.println("(4) Remover Funcionario");
            System.out.println("(0) Sair");
            System.out.println("------------------------------------------------");
            option = scanner.nextInt();
            
            switch (option) {
                case 0:
                    System.out.println("**** Tem Certeza que quer sair?\n(0) Sim\n(1) Não");
                    option = scanner.nextInt();
                    break;
                
                case 1:
                    user.incluirCliente();
                    break;
                case 2: 
                    
                    break;
                case 3: 
                    user.incluirFuncionario();
                    break;
                case 4: 

                    break;

                default:
                    
                    break;
            }
        }
    }
    
    
}
