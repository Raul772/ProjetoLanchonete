package controllers;

import entities.Produto;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import services.ID;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author raulv
 */
public class ProdutoController {
    
    
    private static List<Produto> produtos = new ArrayList<>();

    public static List<Produto> getProdutos() {
        return produtos;
    }

    public static void setProdutos(List<Produto> produtos) {
        ProdutoController.produtos = produtos;
    }   
    
    /**
     * <p>Remove um Produto do sistema.</p>
     * @since 2.0
     */
    public void excluirProduto() {

        try {
            Scanner scanner = new Scanner(System.in);
            List<Produto> aux = searchProduto();
            
            System.out.println("""
                           ----------------------------------------------------------------
                           Selecione um Produto:  """);
            
            int key = scanner.nextInt();
            scanner.nextLine();
            System.out.println("----------------------------------------------------------------");

            if (key <= 0 || key > aux.size()) {
                throw new IOException("Essa opçao nao é valida.");
            }

            Produto produto = produtos.get(produtos.indexOf(aux.remove(key - 1)));
            
            produtos.remove(produto);

            System.out.println("""
                           ----------------------------------------------------------------
                           |                                                              |
                           |                      Produto Removido                        |
                           |                                                              |
                           ----------------------------------------------------------------""");
        } catch (IOException e) {

            System.err.println(e.getMessage());
        }

    }
    
    /**
     * <p>Busca e retorna uma lista de produtos resultado de uma busca por nome.</p>
     * @return Lista de produtos procurados por nome.
     * @throws IOException 
     * @since 2.0
     */
    public List<Produto> searchProduto() throws IOException {

        Scanner scanner = new Scanner(System.in);
        
        List<Produto> busca = new ArrayList<>();

        System.out.println("----------------------------------------------------------------");
        System.out.println("| Digite o nome do Produto:                                |");
        String keySearch = scanner.nextLine();
        System.out.println("----------------------------------------------------------------");

        Pattern pattern = Pattern.compile(keySearch, Pattern.CASE_INSENSITIVE);
        Matcher matcher;

        int count = 1, results = 0;
        for (Produto produto : produtos) {
            if (produto != null){
                matcher = pattern.matcher(produto.getNome());

                if (matcher.find()) {
                    busca.add(produto);
                    System.out.printf("""
                                       -------------- %02d --------------
                                       Nome: %s\nPreco: %f
                                       --------------------------------\n
                                       """, count, produto.getNome(),
                            produto.getPreco());
                    count++;
                    results++;
                }
            }
        }

        if (results == 0) {
            throw new IOException("Nao foi encontrado nenhum Produto com esse nome.");
        }

        return busca;
    }
    
    /**
     * <p>
     * Edita campos de um Produto já cadastrado no sistema.</p>
     *
     * @throws java.io.IOException
     * @since 2.0
     */
    public void editarProduto() throws IOException {

        Scanner scanner = new Scanner(System.in);
        
        List<Produto> aux = searchProduto();

        System.out.println("""
                           ----------------------------------------------------------------
                           Selecione um Produto:  """);
        int key = scanner.nextInt();
        scanner.nextLine();
        System.out.println("----------------------------------------------------------------");

        if (key <= 0 || key > aux.size()) {
            throw new IOException("Essa opçao nao é valida.");
        }

        Produto produto = produtos.get(produtos.indexOf(aux.remove(key - 1)));

        System.out.println("----------------------- Editar Produto -------------------------");
        System.out.println("|       Deixe o campo em branco caso nao queira edita-lo       |");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Digite o nome do Produto: ");
        String nome = scanner.nextLine();
        System.out.println("Digite a descricao do Produto: ");
        String descricao = scanner.nextLine();
        System.out.println("Digite o preco do Produto: ");
        Double preco = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Digite o estoque do Produto: ");
        int estoque = scanner.nextInt();
        scanner.nextLine();
        System.out.println("----------------------------------------------------------------");

        if (!nome.equals("")) {
            produto.setNome(nome);
        }
        if (!descricao.equals("")) {
            produto.setDescricao(descricao);
        }
        if (preco != 0.0) {
            produto.setPreco(preco);
        }
        if (estoque >= 0) {
            produto.setEstoque(estoque);
        }

        System.out.println("""
                           ----------------------------------------------------------------
                           |                                                              |
                           |                       Produto Editado                        |
                           |                                                              |
                           ----------------------------------------------------------------\n\n""");

    }
    
    /**
     * <p>Retorna true se esse Produto estiver cadastrado no sistema.</p>
     * <p>Caso contrário, retorna falso.</p>
     * @param produto
     * @return Boolean
     * @since 2.0
     */
    public boolean existeProduto(Produto produto){
        boolean found = false;
        
        for (Produto produto1 : produtos) {
            if (produto1.getNome().equals(produto.getNome())) {
                found = true;
                break;
            }
        }

        return found;
    }
    
    /**
     * <p>
     * inclui produtos ao sistema</p>
     *
     * @throws java.io.IOException
     * @since 2.0
     */
    public void incluirProduto() throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("---------------------- Incluir Produto -------------------------");
        System.out.println("Digite quantos Produtos gostaria de incluir: ");
        int quant = scanner.nextInt();
        System.out.println("----------------------------------------------------------------");
        scanner.nextLine();
        
        
        for (int i = 0; i < quant; i++) {

            System.out.printf("--------------------- Incluir Produto %02d -----------------------\n", i + 1);
            System.out.println("Digite o nome do Produto: ");
            String nome = scanner.nextLine();
            System.out.println("Digite a descricao do Produto: ");
            String descricao = scanner.nextLine();
            System.out.println("Digite o preco do Produto: ");
            double preco = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Digite a codigo do Produto: ");
            String cod = scanner.nextLine();
            System.out.println("Digite o estoque do Produto: ");
            int estoque = scanner.nextInt();
            scanner.nextLine();
            System.out.println("----------------------------------------------------------------");
            
            if (nome.equals(""))
                throw new IOException("O nome do produto não pode ser vazio.");
            
            if (preco == 0.0)
                throw new IOException("O Preço não pode ser igual a 0");
            
            if (cod.equals(""))
                cod = ID.gerarID(nome);
            
            if (estoque == 0)
                throw new IOException("O campo estoque deve ser preenchido.");


            Produto produto = new Produto(nome, descricao, preco, cod, estoque);
            
            if(this.existeProduto(produto))
                throw new IOException("Já existe um Produto cadastrado com esse nome.");

            produtos.add(produto);

            System.out.println("""
                               ----------------------------------------------------------------
                               |                                                              |
                               |                      Produto Incluido.                       |
                               |                                                              |
                               ----------------------------------------------------------------""");
        }
    }
    
}
