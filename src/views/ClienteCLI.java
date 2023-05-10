package views;

import controllers.ClienteController;
import models.Usuario;
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
                   | (5) Listar Pedidos                                           |
                   ----------------------------------------------------------------
                   | (0) Voltar                                                   |
                   ----------------------------------------------------------------""");
            option = scanner.nextInt();
            try {
                switch (option) {
                    case 0 ->{}
                    case 1 -> this.listarClientes();
                    case 2 -> this.editarCliente();
                    case 3 -> this.incluirCliente();
                    case 4 -> this.excluirCliente();
                    case 5 -> this.listarPedidos();
                    default -> System.out.println("Digite uma opçao valida.");
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } while (option != 0);
    }  
}
