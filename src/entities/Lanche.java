package entities;

/**
 *<p>Essa classe representa um lanche no sistema.</p>
 *@author raulv
 */
public class Lanche extends Produto{
    
    private String ingredientes;

    
//  Sobrecargas de Construtores
    public Lanche() {
//      Questão 04 - Utilizar a palavra-chave super para implementar os construtores das subclasses.
        super();
    }

    public Lanche(String ingredientes, String nome, String descricao, double preco, String codigo, int estoque) {
//      Questão 04 - Utilizar a palavra-chave super para implementar os construtores das subclasses.
        super(nome, descricao, preco, codigo, estoque);
        setIngredientes(ingredientes);
    }

    
//  Getters e Setters
    public String getIngredientes() {
        return ingredientes;
    }

    public final void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    
//  Questão 03 - Sobrescrever o método toString() de todas as classes implementadas.
    @Override
    public String toString() {
        return String.format("""
                             ----------------------------------------------------------------
                             Produto: %s - %s\nPreco: %s\nDescricao: %s/nIngredientes: %s
                             ----------------------------------------------------------------\n""", 
                this.getNome(), this.getCodigo(), this.getPreco(),
                this.getDescricao(), this.getIngredientes());
    }

   
    
    
    
    
}
