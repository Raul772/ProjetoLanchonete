package userinterfaces;

import controllers.AdministradorController;
import entities.Usuario;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author raulv
 */
public class AdministradorCLI extends AdministradorController{
    
    public void administradorMenu(Usuario user){
        
        int option;
        Scanner scanner = new Scanner(System.in);
        
        do {  
            
            System.out.println("""
                   --------------------- Menu Administradores ---------------------
                   | (1) Listar Administradores                                   |
                   | (2) Editar Administradores                                   |
                   | (3) Cadastrar Administradores                                |
                   | (4) Remover Administradores                                  |
                   ----------------------------------------------------------------
                   | (0) Voltar                                                   |
                   ----------------------------------------------------------------""");
            option = scanner.nextInt();
            
            switch (option) {
                case 0 ->{}
                case 1 -> this.listarAdministradores();
                case 2 -> {
                    try {
                        this.editarAdministrador();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
                case 3 -> {
                    try {
                        this.incluirAdministrador();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
                case 4 -> {
                        this.excluirAdministrador();
                }
                    
                default -> System.out.println("Digite uma opçao valida.");
            }
        } while (option != 0);
    }
    
}
