package controllers;

import models.Colaborador;
import models.Usuario;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author raulv
 */
public class ColaboradorController {
    
    /**
     * <p>Retorna true se esse colaborador estiver cadastrado no sistema.</p>
     * <p>Caso contrário, retorna falso.</p>
     * @param colaborador
     * @return Boolean
     * @since 1.0
     */
    private boolean existeColaborador(Colaborador colaborador){
        Usuario[] colaboradores = Usuario.getColaboradores();
        int index;
        boolean found = false;
        for (index = 0; index < 15; index++) {
            if (colaboradores[index].getUser().equals(colaborador.getUser())) {
                found = true;
                break;
            }
        }

        return found;
    }
    
     /**
     * <p>
     * Imprime no console uma lista de todos os colaboradores cadastrados.</p>
     *
     * @since 1.0
     */
    public void listarColaboradores() {

        System.out.println("\n================================================================");
        Colaborador[] colaboradores = Usuario.getColaboradores();
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
        Colaborador[] colaboradores = Usuario.getColaboradores();
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

        Colaborador[] colaboradores = Usuario.getColaboradores();
        
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
                if (colaboradores[pos] == null) {
                    colaboradores[pos] = funcionario;
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

        Colaborador colaborador = Usuario.getColaboradores()[ColaboradorController.findColaborador(
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

            Colaborador colaborador = Usuario.getColaboradores()[ColaboradorController.findColaborador(
                    aux.remove(key - 1).getUser())];
            
            ColaboradorController.deleteColaborador(colaborador.getUser());

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
    
    /**
     * <p>Faz uma pesquisa dentre os colaboradores cadastrados. Caso encontrado um colaborador
     * que tenha um usuario igual ao passado por parâmetro, retorna esse colaborador</p>
     * @param user
     * @return Colaborador
     * @throws IOException
     * @since 1.0
     */
    private static int findColaborador(String user) throws IOException{
        Usuario[] colaboradores = Usuario.getColaboradores();
        int index;
        boolean found = false;
        for (index = 0; index < 15; index++){
            if(colaboradores[index] != null &&
                    colaboradores[index].getUser().equals(user)){
                
                if (colaboradores[index].getNivelDeAcesso() == 1) {
                    found = true;
                    break;
                }
            }
        }
        if(!found) 
            throw new IOException("Não foi possível encontrar o registro.\n\n");
        
        return index;
    }
    
    /**
    * <p>Remove um Colaborador do sistema retirando-o do vetor.</p>
    * @param user
    * @throws IOException
    * @since 1.0
    */
    private static void deleteColaborador(String user) throws IOException{
            Usuario.getColaboradores()[findColaborador(user)] = null;
    }


    @Override
    public String toString() {
        return "ColaboradorController, classe para controlar o model Colaborador";
    }
    
    
    
}
