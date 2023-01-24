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

    public Colaborador(String nome, String cpf, int nivelDeAcesso, String user, String password) throws IOException {
//      Questão 04 - Utilizar a palavra-chave super para implementar os construtores das subclasses.
        super(nome, cpf, nivelDeAcesso, user, password);
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
