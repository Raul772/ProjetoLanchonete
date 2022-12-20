package entities;



import java.util.List;
import java.util.ArrayList;

/**
 *<p>Essa classe representa um produto no sistema.</p>
 * @author raulv
 * @since 1.0
 */
public class Produto {
    
    private static List<Produto> produtos = new ArrayList<>();
    
    private String nome;
    private String descricao;
    private double preco;
    private String codigo;
    private int estoque;
    
//  Sobrecargas de Construtores

    public Produto() {
    }

    public Produto(String nome, String descricao, double preco, String codigo, int estoque) {
        setNome(nome);
        setDescricao(descricao);
        setPreco(preco);
        setCodigo(codigo);
        setEstoque(estoque);
    }
    
    
//  Getters e Setters

    public static List<Produto> getProdutos() {
        return produtos;
    }

    public String getNome() {
        return nome;
    }

    /**
     * <p>Determina o nome do produto.</p>
     * @param nome
     * @since 1.0
     */
    public final void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * <p>Determina a descrição do produto.</p>
     * @param descricao 
     * @since 1.0
     */
    public final void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    /**
     * <p>Determina o preco do produto.</p>
     * @param preco 
     * @since 1.0
     */
    public final void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCodigo() {
        return codigo;
    }

    /**
     * <p>Determina o codigo do produto.</p>
     * @param codigo 
     * @since 1.0
     */
    public final void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getEstoque() {
        return estoque;
    }

    /**
     * <p>Determina o estoque do produto.</p>
     * @param estoque 
     * @since 1.0
     */
    public final void setEstoque(int estoque) {
        this.estoque = estoque;
    }
    
// Métodos de Produto
    
 
    
    
//  Questão 03 - Sobrescrever o método toString() de todas as classes implementadas.
    @Override
    public String toString() {
        return String.format("""
                             ----------------------------------------------------------------
                             Produto: %s - %s\nPreco: %s\nDescricao: %s
                             ----------------------------------------------------------------\n""", 
                this.getNome(), this.getCodigo(), this.getPreco(), this.getDescricao());
    }
    
    
    
    
}
