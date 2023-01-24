package userinterfaces;

import controllers.ColaboradorController;
import entities.Usuario;
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
            
            switch (option) {
                case 0 ->{}
                case 1 -> this.listarColaboradores();
                case 2 -> {
                    try {
                        this.editarColaborador();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
                case 3 -> {
                    try {
                        this.incluirColaborador();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
                case 4 -> {
                        this.excluirColaborador();
                }
                    
                default -> System.out.println("Digite uma opçao valida.");
            }
        } while (option != 0);
    }
    
    
}
