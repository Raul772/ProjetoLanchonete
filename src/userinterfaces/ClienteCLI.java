package userinterfaces;

import controllers.ClienteController;
import entities.Usuario;
import java.io.IOException;
import java.util.Scanner;

/**
 * <p></p>
 * @author raulv
 */ 
public class ClienteCLI extends ClienteController{
    
    public void clienteMenu(Usuario user){
        
        int option;
        Scanner scanner = new Scanner(System.in);
        
        do {  
            
            System.out.println("""
                   ------------------------- Menu Clientes ------------------------
                   | (1) Listar Clientes                                          |
                   | (2) Editar Clientes                                          |
                   | (3) Cadastrar Clientes                                       |
                   | (4) Remover Cliente                                          |
                   ----------------------------------------------------------------
                   | (0) Voltar                                                   |
                   ----------------------------------------------------------------""");
            option = scanner.nextInt();
            
            switch (option) {
                case 0 ->{}
                case 1 -> this.listarClientes();
                case 2 -> {
                    try {
                        this.editarCliente();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
                case 3 -> {
                    try {
                        this.incluirCliente();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        this.excluirCliente();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
                    
                default -> System.out.println("Digite uma opçao valida.");
            }
        } while (option != 0);
    }  
}
