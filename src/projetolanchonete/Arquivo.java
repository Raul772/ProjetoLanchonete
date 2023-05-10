package projetolanchonete;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Administrador;
import models.Cliente;
import models.Colaborador;
import models.Pedido;
import models.Produto;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Type;



public class Arquivo {
    
    /**
     *
     * @param produtos
     */
    public static void salvarProduto(List<Produto> produtos){
        try{
//          Objeto conversor de objeto para Json
            Gson gson = new Gson();
            
//          Escrever um novo arquivo de Cliente no diretório nomeado por ID.json
            Files.writeString(Path.of("\\SistemaLanchonete\\registros\\produtos\\produtos.json"),
                    gson.toJson(produtos));
        }
        catch(IOException e){
//          Tratar exceção caso não seja possível escrever arquivo
            System.err.println(e);
        }
        
       
    }
    
    /**
    * <p>Retorna uma lista de produtos a partir da leitura de um arquivo JSON.</p>
    * @return List&lt;Produto&gt;
    * @since 2.0
    */
    public static List<Produto> carregarProdutos(){
        
        Gson gson = new Gson();
        
        List<Produto> produtos = new ArrayList<>();
        
        Type arrayProduto = new TypeToken<ArrayList<Produto>>() {}.getType();
        
        
        try{
            if (!Files.exists(Path.of("\\SistemaLanchonete\\registros\\produtos")))
                Files.createDirectories(Path.of("\\SistemaLanchonete\\registros\\produtos"));
            
            if (!Files.exists(Path.of("\\SistemaLanchonete\\registros\\produtos\\produtos.json")))
                Files.writeString(Path.of("\\SistemaLanchonete\\registros\\produtos\\produtos.json"), 
                        gson.toJson(produtos));
            
            
            produtos = gson.fromJson(
                    Files.readString(
                            Path.of("\\SistemaLanchonete\\registros\\produtos\\produtos.json")),
                    arrayProduto);
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
        
        return produtos;
        
    }
    
    
//    Clientes
    
    
/**
 * <p>Salva uma lista de Clientes em um arquivo JSON.</p>
 * @param clientes 
 * @since 1.0
 */
    public static void salvarClientes(List<Cliente> clientes){
        
        try{
//          Objeto conversor de objeto para Json
            Gson gson = new Gson();
            
//          Escrever um novo arquivo de Cliente no diretório nomeado por ID.json
            Files.writeString(Path.of("\\SistemaLanchonete\\registros\\clientes\\clientes.json"),
                    gson.toJson(clientes));
        }
        catch(IOException e){
//          Tratar exceção caso não seja possível escrever arquivo
            System.err.println(e);
        }
        
       
    }
    
/**
 * <p>Retorna uma lista de clientes a partir da leitura de um arquivo JSON.</p>
 * @return List&lt;Cliente&gt;
 * @since 1.0
 */
    public static List<Cliente> carregarClientes(){
        
//     Objeto conversor de objeto para Json;
        Gson gson = new Gson();
        
        List<Cliente> clientes = new ArrayList<>();
        
        Type arrayCliente = new TypeToken<ArrayList<Cliente>>() {}.getType();
        
        
        try{
            if (!Files.exists(Path.of("\\SistemaLanchonete\\registros\\clientes")))
                Files.createDirectories(Path.of("\\SistemaLanchonete\\registros\\clientes"));
            
            if (!Files.exists(Path.of("\\SistemaLanchonete\\registros\\clientes\\clientes.json")))
                Files.writeString(Path.of("\\SistemaLanchonete\\registros\\clientes\\clientes.json"), 
                        gson.toJson(clientes));
            
            
            clientes = gson.fromJson(
                    Files.readString(
                            Path.of("\\SistemaLanchonete\\registros\\clientes\\clientes.json")),
                    arrayCliente);
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
        
        return clientes;
    }
    
    
//    Colaboradores
   
    
/**
 * <p>Salva um vetor estatico de colaboradores em um arquivo JSON.</p>
 * @param colaboradores 
 * @since 1.0
 */
    public static void salvarColaboradores(Colaborador[] colaboradores){
        
        try{
//          Objeto conversor de objeto para Json
            Gson gson = new Gson();
            
//          Escrever um novo arquivo de Cliente no diretório nomeado por ID.json
            Files.writeString(Path.of("\\SistemaLanchonete\\registros\\colaboradores\\colaboradores.json"),
                    gson.toJson(colaboradores));
        }
        catch(IOException e){
//          Tratar exceção caso não seja possível escrever arquivo
            System.err.println(e);
        }
        
       
    }
    
/**
 * <p>Retorna um vetor estático de colaboradores a partir da leitura de um arquivo.</p>
 * @return Colaborador[]
 * @since 1.0
 */
    public static Colaborador[] carregarColaboradores(){
        
//     Objeto conversor de objeto para Json;
        Gson gson = new Gson();
        
        Colaborador[] colaboradores = new Colaborador[15];
        
        try{
            
            if (!Files.exists(Path.of("\\SistemaLanchonete\\registros\\colaboradores")))
                Files.createDirectories(Path.of("\\SistemaLanchonete\\registros\\colaboradores"));
            
            if (!Files.exists(Path.of("\\SistemaLanchonete\\registros\\colaboradores\\colaboradores.json"))) {
                
                colaboradores[0] = new Administrador("Padrao",
                                                    "00000000000",
                                                    2,
                                                    "padrao", 
                                                    "12345");
                Files.writeString(Path.of("\\SistemaLanchonete\\registros\\colaboradores\\colaboradores.json"), 
                        gson.toJson(colaboradores));
            }
            else{
                colaboradores = gson.fromJson(
                        Files.readString(
                                Path.of("\\SistemaLanchonete\\registros\\colaboradores\\colaboradores.json")),
                        Colaborador[].class);
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
        
        return colaboradores;
    }
    
    
//    Pedidos
    
    
/**
 * <p>Salva uma lista de pedidos em um arquivo JSON.</p>
 * @param pedidos 
 * @since 1.0
 */
    public static void salvarPedidos(List<Pedido> pedidos){
        
        try{
//          Objeto conversor de objeto para Json
            Gson gson = new Gson();
            
//          Escrever um novo arquivo de Pedido no diretório nomeado por ID.json
            Files.writeString(Path.of("\\SistemaLanchonete\\registros\\pedidos\\pedidos.json"),
                    gson.toJson(pedidos));
        }
        catch(IOException e){
//          Tratar exceção caso não seja possível escrever arquivo
            System.err.println(e);
        }
        
       
    }

/**
 * <p>Retorna uma lista de pedidos a partir da leitura de um arquivo JSON.</p>
 * @return List&lt;Pedido&gt;
 * @since 1.0
 */
    public static List<Pedido> carregarPedidos(){
        
//     Objeto conversor de objeto para Json;
        Gson gson = new Gson();
        
        List<Pedido> pedidos = new ArrayList<>();
        
        Type arrayPedidos = new TypeToken<ArrayList<Pedido>>() {}.getType();
        
        
        try{
            if (!Files.exists(Path.of("\\SistemaLanchonete\\registros\\pedidos")))
                Files.createDirectories(Path.of("\\SistemaLanchonete\\registros\\pedidos"));
            
            if (!Files.exists(Path.of("\\SistemaLanchonete\\registros\\pedidos\\pedidos.json")))
                Files.writeString(Path.of("\\SistemaLanchonete\\registros\\pedidos\\pedidos.json"), 
                        gson.toJson(pedidos));
            
            
            pedidos = gson.fromJson(
                    Files.readString(
                            Path.of("\\SistemaLanchonete\\registros\\pedidos\\pedidos.json")),
                    arrayPedidos);
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
        
        return pedidos;
    }
    
//  Questão 03 - Sobrescrever o método toString() de todas as classes implementadas.
    @Override
    public String toString() {
        return "Arquivo : Classe responsável pelo carregamento e salvamento dos arquivos do sistema.";
    }
     
}
