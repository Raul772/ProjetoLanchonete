package views;

import controllers.AdministradorController;
import models.Usuario;
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
            try {
                switch (option) {
                    case 0 ->{}
                    case 1 -> this.listarAdministradores();
                    case 2 -> this.editarAdministrador();
                    case 3 -> this.incluirAdministrador();
                    case 4 -> this.excluirAdministrador();
                    default -> System.out.println("Digite uma opçao valida.");
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } while (option != 0);
    }
    
}
