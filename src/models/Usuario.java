
package models;

import java.io.IOException;
import services.ID;

/**
 * <p>Essa classe representa um usuario do sistema.</p>
 * @author erica
 * @since 1.0
 */
public abstract class Usuario extends Pessoa {
    
//  Questão 05 - O sistema deverá armazenar de forma estática 15 colaboradores.
    private static Colaborador[] colaboradores; 
    
    private final int nivelDeAcesso;
    private String user;
    private String password;

//  Sobrecargas de Construtor
    
    public Usuario(int nivelDeAcesso) {
//      Questão 04 - Utilizar a palavra-chave super para implementar os construtores das subclasses.
        super();
        this.nivelDeAcesso = nivelDeAcesso;
    }

    public Usuario(String nome, String cpf, int nivelDeAcesso,String user, String password) throws IOException {
//      Questão 04 - Utilizar a palavra-chave super para implementar os construtores das subclasses.
        super(nome, cpf);
        this.nivelDeAcesso = nivelDeAcesso;
        setUser(user);
        setPassword(password);
    }

//  Getters e Setters

    /**
     * <p>Retorna o nível de acesso do usuario.</p>
     * @return int
     * @since 1.0
     */
    public int getNivelDeAcesso() {
        return nivelDeAcesso;
    }
    
//  Nível de acesso é constante por tanto é inicializado no construtor.

    /**
     * <p>Retorna o user desse usuario.</p>
     * @return String
     * @since 1.0
     */
    public String getUser() {
        return user;
    }

    /**
     * <p>Define um user para o usuario.</p>
     * @param user 
     * @since 1.0
     */
    public final void setUser(String user) {
        
        if(user.equals(""))
            this.user = ID.gerarID(super.getNome(), super.getCpf());
        else
            this.user = user;
    }
    
    /**
     * <p>Retorna a senha de acesso do usuario.</p>
     * @return String
     * @since 1.0
     */
    public String getPassword() {
        return this.password;
    }    

    /**
     * <p>Define uma senha para o usuario.</p>
     * @param password 
     * @since 1.0
     */
    public final void setPassword(String password) {
        if (password.equals(""))
            throw new IllegalArgumentException("A senha não pode ser vazia.");
        
        else
            this.password = password;
    }

    public static Colaborador[] getColaboradores() {
        return colaboradores;
    }

    public static void setColaboradores(Colaborador[] colaboradores) {
        Usuario.colaboradores = colaboradores;
    }
    
//  Overrides
    
//  Questão 03 - Sobrescrever o método toString() de todas as classes implementadas.
    @Override
    public String toString(){
        return String.format("""
                             ----------------------------------------------------------------
                             %sNível de Acesso: %d\nSenha: %s
                             ----------------------------------------------------------------\n""", 
                super.toString(),getNivelDeAcesso(), getPassword());
    }
    
}
