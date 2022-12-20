package appExceptions;


public class FailedLoginException extends Exception {

    public FailedLoginException(String message) {
//      Questão 04 - Utilizar a palavra-chave super para implementar os construtores das subclasses.
        super(message);
    }

}
