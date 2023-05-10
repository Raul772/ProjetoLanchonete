package views;

import models.Administrador;
import models.Colaborador;
import models.Usuario;
import java.util.Scanner;
import java.io.IOException;

/**
 * <p>
 * Representa o controlador de um CLI de menu principal no sistema.</p>
 *
 * @author raulv
 * @since 2.0
 */
public class MainCLI {

    /**
     * <p>
     * Recebe um usuario e mostra o menu adequado a depender do nível de acesso
     * deste.</p>
     *
     * @param user
     * @since 2.0
     */
    public void mainMenu(Usuario user) {
        try {
            switch (user.getNivelDeAcesso()) {
                case 1 -> colaboradorMainMenu(
                                new Colaborador(user.getNome(), user.getCpf(),
                                        1, user.getNome(), user.getPassword()));
                
                case 2 -> administradorMainMenu(
                                new Administrador(user.getNome(), user.getCpf(),
                                        2, user.getNome(), user.getPassword()));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * <p>Recebe um Colaborador e mostra no console um menu com as opções de
     * colaborador.</p>
     *
     * @param user
     * @since 2.0
     */
    private void colaboradorMainMenu(Colaborador user) {
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        while (option != 0) {

            System.out.println("""
                                    ----------------------------- Menu -----------------------------
                                    | (1) Menu Clientes                                            |
                                    | (2) Menu de Pedidos                                          |
                                    ----------------------------------------------------------------
                                    | (0) Sair                                                     |
                                    ----------------------------------------------------------------
                                    """);

            option = scanner.nextInt();

//              Direcionamento da execução de acordo com a opção escolhida
            switch (option) {
                case 0 -> {
                    System.out.println("""
                                                               ----------------------------------------------------------------
                                                               |                Tem Certeza que quer sair?                    |
                                                               | (0) Sim                                                      |
                                                               | (1) Nao                                                      |
                                                               ----------------------------------------------------------------
                                                                  """);

                    option = scanner.nextInt();
                }

                case 1 -> new ClienteCLI().clienteMenu(user);
                case 2 -> new PedidoCLI().pedidoMenu(user);
                default -> System.out.println("Digite uma opçao valida.");
            }

//                  Padrão caso o usuário selecione uma opção que não está na lista
        }
    }

    /**
     * <p>
     * Recebe um Administrador e mostra no console um menu com as opções de
     * administrador.</p>
     *
     * @param user
     * @since 2.0
     */
    private void administradorMainMenu(Administrador user) {
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        while (option != 0) {

            System.out.println("""
                                   ----------------------------- Menu -----------------------------
                                   | (1) Menu Clientes                                            |
                                   | (2) Menu Colaboradores                                       |
                                   | (3) Menu Administradores                                     |
                                   | (4) Menu de Produtos                                         |
                                   | (5) Menu de Pedidos                                          |
                                   ----------------------------------------------------------------
                                   | (0) Sair                                                     |
                                   ----------------------------------------------------------------
                                   """);

            option = scanner.nextInt();

            switch (option) {
                case 0 -> {
                    System.out.println("""
                                                                ----------------------------------------------------------------
                                                                |                Tem Certeza que quer sair?                    |
                                                                | (0) Sim                                                      |
                                                                | (1) Nao                                                      |
                                                                ----------------------------------------------------------------
                                                               """);
                    option = scanner.nextInt();
                }
                case 1 -> new ClienteCLI().clienteMenu(user);
                case 2 -> new ColaboradorCLI().colaboradorMenu(user);
                case 3 -> new AdministradorCLI().administradorMenu(user);
                case 4 -> new ProdutoCLI().produtoMenu(user);
                case 5 -> new PedidoCLI().pedidoMenu(user);
                default ->System.out.println("Digite uma opçao valida.");
            }
//              Padrão caso o usuário selecione uma opção que não está na lista
        }
    }
}
