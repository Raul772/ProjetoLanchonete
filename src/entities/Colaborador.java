package entities;


import java.io.IOException;


/**
 * <p>A classe Colaborador representa um funcionario no sistema e disponibiliza métodos relacionados ao mesmo.</p>
 * @author raulv
 * @since 1.0
 */
public class Colaborador extends Usuario{
    
//  Sobrecargas de Construtor
    
    public Colaborador(int nivelDeAcesso){
//      Questão 04 - Utilizar a palavra-chave super para implementar os construtores das subclasses.
        super(nivelDeAcesso);
    }

    public Colaborador(String nome, String cpf, int nivelDeAcesso, String user, String password) {
//      Questão 04 - Utilizar a palavra-chave super para implementar os construtores das subclasses.
        super(nome, cpf, nivelDeAcesso, user, password);
    }
   
//  Métodos de Colaborador
    
    /**
     * <p>Retorna true se o usuario passado por parâmetro for igual ao de algum colaborador cadastrado.</p>
     * <p>Caso contrário, retorna falso.</p>
     * @param user
     * @return Boolean
     * @since 1.0
     */
    public static boolean existeColaborador(String user){

        int index;
        boolean found = false;
        for (index = 0; index < 15; index++) {
            if (colaboradores[index].getUser().equals(user)) {
                found = true;
                break;
            }
        }

        return found;
    }
    
    /**
     * <p>Retorna true se esse colaborador estiver cadastrado no sistema.</p>
     * <p>Caso contrário, retorna falso.</p>
     * @return Boolean
     * @since 1.0
     */
    public boolean existeColaborador(){
        int index;
        boolean found = false;
        for (index = 0; index < 15; index++) {
            if (colaboradores[index].getUser().equals(this.getUser())) {
                found = true;
                break;
            }
        }

        return found;
    }
    
    /**
     * <p>Faz uma pesquisa dentre os colaboradores cadastrados. Caso encontrado um colaborador
     * que tenha um usuario igual ao passado por parâmetro, retorna esse colaborador</p>
     * @param user
     * @return Colaborador
     * @throws IOException
     * @since 1.0
     */
    public static int findColaborador(String user) throws IOException{
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
    public static void deleteColaborador(String user) throws IOException{
            colaboradores[findColaborador(user)] = null;
    }
    
//  Overrides
    
//  Questão 03 - Sobrescrever o método toString() de todas as classes implementadas.
    @Override
    public String toString() {
        return String.format("""
                             ----------------------------------------------------------------
                             %sUser Funcionário: %s
                             ----------------------------------------------------------------\n""", 
                super.toString(), getUser());
    }
}
