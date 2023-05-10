package views;

import models.Usuario;
import java.util.Scanner;
import java.io.IOException;
import controllers.PedidoController;

/**
 *
 * @author raulv
 */
public class PedidoCLI extends PedidoController {

    public void pedidoMenu(Usuario user) {
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
            try {
                switch (option) {
                    case 0 -> {}
                    case 1 -> new PedidoController().adicionarPedido();
                    case 2 -> new PedidoController().editarPedido();
                    case 3 -> new PedidoController().removerPedido();
                    default -> System.out.println("Selecione uma opção válida");
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } while (option != 0);
    }
}
