package projetolanchonete;

import controllers.ClienteController;
import controllers.ProdutoController;
import userinterfaces.MainCLI;
import entities.Usuario;
import services.Login;
import java.util.Locale;

public class Sistema {

    public static void main(String[] args) {
        
        Locale.setDefault(Locale.of("pt", "BR"));
        
        ClienteController.setClientes(Arquivo.carregarClientes());
        Usuario.setColaboradores(Arquivo.carregarColaboradores());
        ProdutoController.setProdutos(Arquivo.carregarProdutos());
//        Pedido.setPedidos(Arquivo.carregarPedidos());

        Usuario user = Login.login();
        
        MainCLI mainMenu = new MainCLI();
        mainMenu.mainMenu(user);


        Arquivo.salvarClientes(ClienteController.getClientes());
        Arquivo.salvarColaboradores(Usuario.getColaboradores());
        Arquivo.salvarProduto(ProdutoController.getProdutos());
//        Arquivo.salvarPedidos(Pedido.getPedidos());
    }

}
