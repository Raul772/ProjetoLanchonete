package entities;

import java.io.IOException;

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

    public Administrador(String nome, String cpf, int nivelDeAcesso, String user, String password) throws IOException {
//      Questão 04 - Utilizar a palavra-chave super para implementar os construtores das subclasses.
        super(nome, cpf, nivelDeAcesso, user, password);
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
