package views;

import models.Usuario;
import controllers.ProdutoController;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author raulv
 */
public class ProdutoCLI extends ProdutoController {
    
    public void produtoMenu(Usuario user){
        Scanner scanner = new Scanner(System.in);
        int option;
        do {            
            System.out.println("""
                                   ------------------------ Menu Produto -------------------------
                                   | (1) Registrar Produto                                       |
                                   | (2) Editar Produto                                          |
                                   | (3) Remover Produto                                         |
                                   ----------------------------------------------------------------
                                   | (0) Voltar                                                  |
                                   ----------------------------------------------------------------
                                   """);
        
            option = scanner.nextInt();
            ProdutoController PController = new ProdutoController();
            try {
                switch (option) {
                    case 0 -> {}
                    case 1 -> PController.incluirProduto();
                    case 2 -> PController.editarProduto();
                    case 3 -> PController.excluirProduto();
                    default -> System.out.println("Digite uma opçao valida.");
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } while (option != 0);
        
    }
}
