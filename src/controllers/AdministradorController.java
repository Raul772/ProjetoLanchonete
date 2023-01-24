package controllers;

import entities.Administrador;
import entities.Colaborador;
import entities.Usuario;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author raulv
 */
public class AdministradorController {
    
    /**
     * <p>
     * Imprime no console uma lista de todos os Administradores cadastrados.</p>
     *
     * @since 1.0
     */
    public void listarAdministradores() {

        System.out.println("\n================================================================");
        Colaborador[] colaboradores = Usuario.getColaboradores();
        int count = 1;
        for (Colaborador colaborador : colaboradores) {
            if (colaborador != null && colaborador.getNivelDeAcesso() == 2) {
                System.out.printf("""
                                   -------------- %02d --------------
                                   Nome: %s\nCPF: %s\nUsuario: %s
                                   --------------------------------\n
                                   """, count, colaborador.getNome(),
                        colaborador.getCpf(),
                        colaborador.getUser());
                count++;
            }
        }
        System.out.println("================================================================\n");
    }

    /**
     * <p>
     * Inclui um Administrador no sistema.</p>
     *
     * @return Administrador
     * @throws java.io.IOException
     * @since 1.0
     */
    public Administrador incluirAdministrador() throws IOException {

        Scanner scanner = new Scanner(System.in);
        Colaborador[] colaboradores = Usuario.getColaboradores();

        System.out.println("-------------- Incluir Administrador ------------------");
        System.out.println("Digite o nome do Administrador: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF do Administrador: ");
        String cpfNumber = scanner.nextLine();
        System.out.println("Digite o User do Administrador: ");
        String userAdministrador = scanner.nextLine();
        System.out.println("Digite a senha do Administrador: ");
        String senha = scanner.nextLine();
        System.out.println("-------------------------------------------------");

//      Criar novo objeto Administrador caso esse ainda não esteja registrado
        Administrador administrador = new Administrador(nome,
                cpfNumber, 2, userAdministrador, senha);

        boolean inserido = false;
        int pos = 0;
        do {
            if (colaboradores[pos] == null) {
                colaboradores[pos] = administrador;
                inserido = true;
            }
            pos++;
        } while (!inserido && pos < 15);

        if (!inserido) {
            throw new ArrayStoreException("""
                                         -----------------------------------------------------------------
                                         |   Não foi possível armazenar o Administrador, lista cheia.    |
                                         -----------------------------------------------------------------""");
        }

        System.out.println("""
                           ----------------------------------------------------------------
                           |                                                              |
                           |                   Administrador Incluido.                    |
                           |                                                              |
                           ----------------------------------------------------------------""");

//      Retornar Administrador registrado
        return administrador;
    }

    /**
     * <p>
     * Faz uma pesquisa dentre os Administradores cadastrados. Caso encontrado
     * um administrador que tenha um usuario igual ao passado por parâmetro,
     * retorna seu index.</p>
     *
     * @param user
     * @return Int
     * @throws IOException
     * @since 1.0
     */
    public static int findAdministrador(String user) throws IOException {
        Colaborador[] colaboradores = Usuario.getColaboradores();
        int index;
        boolean found = false;
        for (index = 0; index < 15; index++) {
            if (colaboradores[index] != null
                    && colaboradores[index].getUser().equals(user)) {

                if (colaboradores[index].getNivelDeAcesso() == 2) {
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            throw new IOException("Não foi possível encontrar o registro.");
        }

        return index;
    }

    /**
     * <p>
     * Remove um Administrador do sistema retirando-o do vetor de
     * colaboradores.</p>
     *
     * @param user
     * @throws IOException
     * @since 1.0
     */
    private void deleteAdministrador(String user) throws IOException {
        Usuario.getColaboradores()[findAdministrador(user)] = null;
    }

    /**
     * <p>
     * Remove um Administrador do sistema.</p>
     *
     * @since 1.0
     */
    public void excluirAdministrador() {
        Scanner scanner = new Scanner(System.in);
        Colaborador[] colaboradores = Usuario.getColaboradores();

        System.out.println("----------------------------------------------------------------");
        System.out.println("| Digite o user do Administrador:                              |");
        String userID = scanner.nextLine();
        System.out.println("----------------------------------------------------------------");

        try {
            int adminCount = 0;
            for (Colaborador colaborador : colaboradores) {
                if (colaborador != null && colaborador.getNivelDeAcesso() == 2) {
                    adminCount++;
                }
            }

            if (adminCount <= 1) {
                throw new IOException("""
                           ----------------------------------------------------------------
                           |                                                              |
                           |           Deve haver pelo menos um administrador             |
                           |                    cadastrado no sistema.                    |
                           |                                                              |
                           ----------------------------------------------------------------\n\n""");
            }

            this.deleteAdministrador(userID);

            System.out.println("""
                           ----------------------------------------------------------------
                           |                                                              |
                           |                   Administrador Removido                     |
                           |                                                              |
                           ----------------------------------------------------------------\n\n""");
        } catch (IOException e) {
            System.err.printf(e.getMessage());
        }

    }

    /**
     * <p>
     * Edita campos de um colaborador já cadastrado no sistema.</p>
     *
     * @throws java.io.IOException
     * @since 1.0
     */
    public void editarAdministrador() throws IOException {

        Scanner scanner = new Scanner(System.in);
        ColaboradorController CController = new ColaboradorController();

        List<Colaborador> aux = CController.searchColaborador();

        System.out.println("""
                           ----------------------------------------------------------------
                           Selecione um Administrador:  """);
        int key = scanner.nextInt();
        scanner.nextLine();
        System.out.println("----------------------------------------------------------------");

        if (key <= 0 || key > aux.size()) {
            throw new IOException("Essa opçao nao é valida.");
        }

        Colaborador colaborador = Usuario.getColaboradores()[AdministradorController.findAdministrador(
                aux.remove(key - 1).getUser())];

        System.out.println("--------------------- Editar Administrador ---------------------");
        System.out.println("|       Deixe o campo em branco caso nao queira edita-lo       |");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Digite o nome do Administrador: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF do Administrador: ");
        String cpfNumber = scanner.nextLine();
        System.out.println("Digite o User do Administrador: ");
        String userColaborador = scanner.nextLine();
        System.out.println("Digite a senha do Administrador: ");
        String senha = scanner.nextLine();
        System.out.println("----------------------------------------------------------------");

        if (!nome.equals("")) {
            colaborador.setNome(nome);
        }
        if (!cpfNumber.equals("")) {
            colaborador.setCpf(cpfNumber);
        }
        if (!userColaborador.equals("")) {
            colaborador.setUser(userColaborador);
        }
        if (!senha.equals("")) {
            colaborador.setPassword(senha);
        }

        System.out.println("""
                           ----------------------------------------------------------------
                           |                                                              |
                           |                    Administrador Editado                     |
                           |                                                              |
                           ----------------------------------------------------------------\n\n""");

    }
    
    
    
}
