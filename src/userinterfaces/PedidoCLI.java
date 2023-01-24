package userinterfaces;

import entities.Usuario;
import java.util.Scanner;
import java.io.IOException;

/**
 *
 * @author raulv
 */
public class PedidoCLI {
    
    public static void menuPedido(Usuario user) throws IOException{
        Scanner scanner = new Scanner(System.in);
        int option;
        do {            
            System.out.println("""
                                   ------------------------- Menu Pedido -------------------------
                                   | (1) Registrar Pedido                                        |
                                   | (2) Editar Pedido                                           |
                                   | (3) Remover Pedido                                          |
                                   ----------------------------------------------------------------
                                   | (0) Voltar                                                  |
                                   ----------------------------------------------------------------
                                   """);
        
            option = scanner.nextInt();
        
            switch (option) {
                case 0:

                    break;
                case 1:
                    try {
//                        user.registrarPedido();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
//                        user.editarPedido();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                default:
                    throw new AssertionError();
            }
        } while (option != 0);
        
    }
    
    
}
