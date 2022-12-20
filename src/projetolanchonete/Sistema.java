package projetolanchonete;

import entities.Administrador;
import entities.Cliente;
import entities.Pedido;
import entities.Produto;
import entities.Usuario;
import services.Login;
import java.util.Scanner;
import java.io.IOException;

public class Sistema {

    public static void main(String[] args) {

        Cliente.clientes = Arquivo.carregarClientes();
        Usuario.colaboradores = Arquivo.carregarColaboradores();
        Pedido.setPedidos(Arquivo.carregarPedidos());
        
        Produto.getProdutos().add(new Produto("Coca Cola 2L", "Refrigerante CocaCola - Garrafa 2L", 7.0, "1", 1));

        Usuario user = Login.login();

        int option = 1;
        Scanner scanner = new Scanner(System.in);

        if (user.getNivelDeAcesso() == 1) {
            while (option != 0) {

                try {
//                  Necessário para sincronização de threads
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }

                System.out.println("""
                                    ----------------------------- Menu -----------------------------
                                    | (1) Listar Clientes                                          |
                                    | (2) Editar Clientes                                          |
                                    | (3) Cadastrar Clientes                                       |
                                    | (4) Remover Cliente                                          |
                                    ----------------------------------------------------------------
                                    | (5) Menu de Pedidos                                          |
                                    ----------------------------------------------------------------
                                    | (0) Sair                                                     |
                                    ----------------------------------------------------------------
                                    """);

                option = scanner.nextInt();

//              Direcionamento da execução de acordo com a opção escolhida
                switch (option) {
                    case 0:
                        System.out.println("""
                                        ----------------------------------------------------------------
                                        |                Tem Certeza que quer sair?                    |
                                        | (0) Sim                                                      |
                                        | (1) Nao                                                      |
                                        ----------------------------------------------------------------
                                           """);

                        option = scanner.nextInt();
                        break;

                    case 1:
                        user.listarClientes();
                        break;
                        
                    case 2:
                        try {
                        user.editarCliente();
                        } catch (IOException e) {
                            System.err.println(e.getMessage());
                        }
                        break;

                    case 3:
                        try {
                        user.incluirCliente();
                        } catch (IOException e) {
                            System.err.println(e.getMessage());
                        }
                        break;

                    case 4:
                        try {
                            user.excluirCliente();
                        } catch (IOException e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    case 5:
                        Pedido.menuPedido(user);
                        break;

//                  Padrão caso o usuário selecione uma opção que não está na lista
                    default:
                        System.out.println("Digite uma opçao valida.");
                        break;
                }

            }
        }
        if (user.getNivelDeAcesso() == 2) {

            Administrador admin = new Administrador(
                    user.getNome(),
                    user.getCpf(),
                    2,
                    user.getUser(),
                    user.getPassword());

            while (option != 0) {

                try {
//                  Necessário para sincronização de threads
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
                System.out.println("""
                                   ----------------------------- Menu -----------------------------
                                   | (1) Listar Clientes                                          |
                                   | (2) Editar Clientes                                          |
                                   | (3) Cadastrar Clientes                                       |
                                   | (4) Remover Cliente                                          |
                                   ----------------------------------------------------------------
                                   | (5) Listar Colaboradores                                     |
                                   | (6) Editar Colaboradores                                     |
                                   | (7) Cadastrar Colaborador                                    |
                                   | (8) Remover Colaborador                                      |
                                   ----------------------------------------------------------------
                                   | (9) Listar Administradores                                   |
                                   | (10) Cadastrar Administrador                                 |
                                   | (11) Remover Administrador                                   |
                                   ----------------------------------------------------------------
                                   | (12) Menu de Pedidos                                         |
                                   ----------------------------------------------------------------
                                   | (0) Sair                                                     |
                                   ----------------------------------------------------------------
                                   """);

                option = scanner.nextInt();

                switch (option) {
                    case 0:
                        System.out.println("""
                                            ----------------------------------------------------------------
                                            |                Tem Certeza que quer sair?                    |
                                            | (0) Sim                                                      |
                                            | (1) Nao                                                      |
                                            ----------------------------------------------------------------
                                           """);
                        option = scanner.nextInt();
                        break;
                    case 1:
                        System.out.println("""
                                            ----------------------------------------------------------------
                                            |          Quer fazer uma procura ou listar todos?             |
                                            | (0) fazer procura                                            |
                                            | (1) listar todos                                             |
                                            ----------------------------------------------------------------
                                           """);
                         
                        
                        if(scanner.nextInt() == 0)
                            try{
                                admin.searchCliente();
                            } catch (IOException e) {
                                System.err.println(e.getMessage());
                            }
                        else
                            admin.listarClientes();
                        
                        break;
                    case 2:
                        try {
                            admin.editarCliente();
                        } catch (IOException e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    case 3:
                        try {
                        admin.incluirCliente();
                        } catch (IOException e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    case 4:
                        try {
                            admin.excluirCliente();
                        } catch (IOException e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.println("""
                                            ----------------------------------------------------------------
                                            |          Quer fazer uma procura ou listar todos?             |
                                            | (0) fazer procura                                            |
                                            | (1) listar todos                                             |
                                            ----------------------------------------------------------------
                                           """);
                        if(scanner.nextInt() == 0)
                            try {
                            admin.searchColaborador();
                            } catch (IOException e) {
                                System.err.println(e.getMessage());
                            }
                        else
                            admin.listarColaboradores();
                        
                        break;
                    case 6:
                        try {
                            admin.editarColaborador();
                        } catch (IOException e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    case 7:
                        try {
                        admin.incluirColaborador();
                        } catch (IOException e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    case 8:
                        admin.excluirColaborador();
                        break;
                    case 9:
                        admin.listarAdministradores();
                        break;
                    case 10:
                        admin.incluirAdministrador();
                        break;
                    case 11:
                        admin.excluirAdministrador();
                        break;
                    case 12:
                        Pedido.menuPedido(admin);
                        break;
                    

//              Padrão caso o usuário selecione uma opção que não está na lista
                    default:
                        System.out.println("Digite uma opçao valida.");
                        break;
                }
            }
        }

        Arquivo.salvarClientes(Cliente.clientes);
        Arquivo.salvarColaboradores(Usuario.colaboradores);
        Arquivo.salvarPedidos(Pedido.getPedidos());
    }

}
