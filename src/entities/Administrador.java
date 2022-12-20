package entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Essa classe representa um Administrador no sistema.</p>
 *
 * @author raulv
 * @since 1.0
 */
public class Administrador extends Colaborador {

//  Sobrecargas de Construtor
    public Administrador(int nivelDeAcesso) {
//      Questão 04 - Utilizar a palavra-chave super para implementar os construtores das subclasses.
        super(nivelDeAcesso);
    }

    public Administrador(String nome, String cpf, int nivelDeAcesso, String user, String password) {
//      Questão 04 - Utilizar a palavra-chave super para implementar os construtores das subclasses.
        super(nome, cpf, nivelDeAcesso, user, password);
    }

//  Métodos de Administrador
    /**
     * <p>
     * Imprime no console uma lista de todos os Administradores cadastrados.</p>
     *
     * @since 1.0
     */
    public void listarAdministradores() {

        System.out.println("\n================================================================");
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
     * @since 1.0
     */
    public Administrador incluirAdministrador() {

        Scanner scanner = new Scanner(System.in);

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
            if (Usuario.colaboradores[pos] == null) {
                Usuario.colaboradores[pos] = administrador;
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
        colaboradores[findAdministrador(user)] = null;
    }

    /**
     * <p>
     * Remove um Administrador do sistema.</p>
     *
     * @since 1.0
     */
    public void excluirAdministrador() {
        Scanner scanner = new Scanner(System.in);

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

//  Métodos de Colaborador
    /**
     * <p>
     * Imprime no console uma lista de todos os colaboradores cadastrados.</p>
     *
     * @since 1.0
     */
    public void listarColaboradores() {

        System.out.println("\n================================================================");
        int count = 1;
        for (Colaborador colaborador : colaboradores) {
            if (colaborador != null && colaborador.getNivelDeAcesso() == 1) {
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
     * <p>Busca e retorna uma lista de colaboradores resultado de uma busca por nome.</p>
     * @return Lista de colaboradores procurados por nome.
     * @throws IOException 
     * @since 1.0
     */
    public List<Colaborador> searchColaborador() throws IOException {

        Scanner scanner = new Scanner(System.in);
        List<Colaborador> busca = new ArrayList<>();

        System.out.println("----------------------------------------------------------------");
        System.out.println("| Digite o nome do Colaborador:                                |");
        String keySearch = scanner.nextLine();
        System.out.println("----------------------------------------------------------------");

        Pattern pattern = Pattern.compile(keySearch, Pattern.CASE_INSENSITIVE);
        Matcher matcher;

        int count = 1, results = 0;
        for (Colaborador colaborador : colaboradores) {
            if (colaborador != null){
                matcher = pattern.matcher(colaborador.getNome());

                if (matcher.find()) {
                    busca.add(colaborador);
                    System.out.printf("""
                                       -------------- %02d --------------
                                       Nome: %s\nCPF: %s\nUsuario: %s
                                       --------------------------------\n
                                       """, count, colaborador.getNome(),
                            colaborador.getCpf(),
                            colaborador.getUser());
                    count++;
                    results++;
                }
            }
        }

        if (results == 0) {
            throw new IOException("Nao foi encontrado nenhum colaborador com esse nome.");
        }

        return busca;
    }

    /**
     * <p>
     * inclui colaboradores ao sistema</p>
     *
     * @throws java.io.IOException
     * @since 1.0
     */
    public void incluirColaborador() throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------- Incluir Colaborador -----------------------");
        System.out.println("Digite quantos colaboradores gostaria de incluir: ");
        int quant = scanner.nextInt();
        System.out.println("----------------------------------------------------------------");
        scanner.nextLine();

        for (int i = 0; i < quant; i++) {

            System.out.printf("------------------- Incluir Colaborador %02d ---------------------\n", i + 1);
            System.out.println("Digite o nome do Colaborador: ");
            String nome = scanner.nextLine();
            System.out.println("Digite o CPF do Colaborador: ");
            String cpfNumber = scanner.nextLine();
            System.out.println("Digite o User do Colaborador: ");
            String userColaborador = scanner.nextLine();
            System.out.println("Digite a senha do Colaborador: ");
            String senha = scanner.nextLine();
            System.out.println("----------------------------------------------------------------");

//          Criar novo objeto Colaborador caso esse ainda não esteja registrado
            Colaborador funcionario = new Colaborador(nome,
                    cpfNumber, 1, userColaborador, senha);

            boolean inserido = false;
            int pos = 0;
            do {
                if (Usuario.colaboradores[pos] == null) {
                    Usuario.colaboradores[pos] = funcionario;
                    inserido = true;
                }
                pos++;
            } while (!inserido && pos < 15);

            if (!inserido) {
                throw new IOException("""
                                             -----------------------------------------------------------------
                                             |    Nao foi possivel armazenar o Colaborador, lista cheia.     |
                                             -----------------------------------------------------------------""");
            }

            System.out.println("""
                               ----------------------------------------------------------------
                               |                                                              |
                               |                    Colaborador Incluido.                     |
                               |                                                              |
                               ----------------------------------------------------------------""");
        }
    }

    /**
     * <p>
     * Edita campos de um colaborador já cadastrado no sistema.</p>
     *
     * @throws java.io.IOException
     * @since 1.0
     */
    public void editarColaborador() throws IOException {

        Scanner scanner = new Scanner(System.in);

        List<Colaborador> aux = this.searchColaborador();

        System.out.println("""
                           ----------------------------------------------------------------
                           Selecione um Colaborador:  """);
        int key = scanner.nextInt();
        scanner.nextLine();
        System.out.println("----------------------------------------------------------------");

        if (key <= 0 || key > aux.size()) {
            throw new IOException("Essa opçao nao é valida.");
        }

        Colaborador colaborador = colaboradores[Colaborador.findColaborador(
                aux.remove(key - 1).getUser())];

        System.out.println("--------------------- Editar Colaborador -----------------------");
        System.out.println("|       Deixe o campo em branco caso nao queira edita-lo       |");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Digite o nome do Colaborador: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF do Colaborador: ");
        String cpfNumber = scanner.nextLine();
        System.out.println("Digite o User do Colaborador: ");
        String userColaborador = scanner.nextLine();
        System.out.println("Digite a senha do Colaborador: ");
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
                           |                     Colaborador Editado                      |
                           |                                                              |
                           ----------------------------------------------------------------\n\n""");

    }

    /**
     * <p>Remove um Funcionario do sistema.</p>
     * @since 1.0
     */
    public void excluirColaborador() {

        try {
            Scanner scanner = new Scanner(System.in);
            List<Colaborador> aux = this.searchColaborador();
            
            System.out.println("""
                           ----------------------------------------------------------------
                           Selecione um Colaborador:  """);
            
            int key = scanner.nextInt();
            scanner.nextLine();
            System.out.println("----------------------------------------------------------------");

            if (key <= 0 || key > aux.size()) {
                throw new IOException("Essa opçao nao é valida.");
            }

            Colaborador colaborador = colaboradores[Colaborador.findColaborador(
                    aux.remove(key - 1).getUser())];
            
            Colaborador.deleteColaborador(colaborador.getUser());

            System.out.println("""
                           ----------------------------------------------------------------
                           |                                                              |
                           |                    Colaborador Removido                      |
                           |                                                              |
                           ----------------------------------------------------------------""");
        } catch (IOException e) {

            System.err.println(e.getMessage());
        }

    }

//  Overrides
//  Questão 03 - Sobrescrever o método toString() de todas as classes implementadas.
    @Override
    public String toString() {
        return String.format("""
                             ----------------------------------------------------------------
                             %sID Administrador: %s
                             ----------------------------------------------------------------\n""",
                super.toString(), getUser());
    }

}
