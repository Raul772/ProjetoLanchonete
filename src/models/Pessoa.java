package models;

import java.io.IOException;

/**
 * <p>Essa classe representa uma pessoa no sistema.</p>
 * @author erica
 * @since 1.0
 */
public abstract class Pessoa {
    
    private String nome;
    protected String cpf;
   

//  Sobrecargas de Construtores
    
    public Pessoa(){}
    
    public Pessoa(String nome, String cpf) throws IOException {
        this.nome = nome;
        setCpf(cpf);
    }

//  Getters e Setters
    
    /**
     * <p>Retorna o nome da pessoa.</p>
     * @return String
     * @since 1.0
     */
    public String getNome() {
        return nome;
    }

    /**
     * <p>Determina o nome da pessoa a partir do parâmetro passado.</p>
     * @param nome 
     * @since 1.0
     */
    public void setNome(String nome) {
        if(nome.matches("[0-9]"))
            throw new IllegalArgumentException("Nome deve conter apenas letras.");
        else         
            this.nome = nome;
        
    }

    /**
     * <p>Retorna o cpf da pessoa.</p>
     * @return String
     * @since 1.0
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * <p>Determina o cpf da pessoa a partir do parâmetro passado.</p>
     * @param cpf
     * @throws java.io.IOException
     * @since 1.0
     */
    public final void setCpf(String cpf) throws IOException {
        if(cpf.matches("[0-9]{11}"))
            this.cpf = cpf;
        else
            throw new IOException("CPF deve ser composto apenas por números.");
    }

//  Overrides
    
//  Questão 03 - Sobrescrever o método toString() de todas as classes implementadas.
    @Override
    public String toString() {
        return "Nome : " + nome + "\nCPF : " + cpf + "\n";
    }
}
