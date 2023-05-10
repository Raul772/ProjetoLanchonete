package views;

import controllers.ColaboradorController;
import models.Usuario;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author raulv
 */
public class ColaboradorCLI extends ColaboradorController {
    
    public void colaboradorMenu(Usuario user){
        
        int option;
        Scanner scanner = new Scanner(System.in);
        
        do {  
            
            System.out.println("""
                   ---------------------- Menu Colaboradores ----------------------
                   | (1) Listar Colaboradores                                     |
                   | (2) Editar Colaboradores                                     |
                   | (3) Cadastrar Colaboradores                                  |
                   | (4) Remover Colaboradores                                    |
                   ----------------------------------------------------------------
                   | (0) Voltar                                                   |
                   ----------------------------------------------------------------""");
            option = scanner.nextInt();
            try {
                switch (option) {
                    case 0 ->{}
                    case 1 -> this.listarColaboradores();
                    case 2 -> this.editarColaborador();
                    case 3 -> this.incluirColaborador();
                    case 4 -> this.excluirColaborador();
                    default -> System.out.println("Digite uma opçao valida.");
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } while (option != 0);
    }
    
    
}
