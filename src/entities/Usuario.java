
package entities;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import services.ID;

public abstract class Usuario extends Pessoa{
    
    protected final int nivelDeAcesso;
    protected String user;
    protected String password;

//  Sobrecargas de Construtor
    
    public Usuario(int nivelDeAcesso) {
        this.nivelDeAcesso = nivelDeAcesso;
    }

    public Usuario(String nome, String cpf, int nivelDeAcesso,String user, String password) {
        super(nome, cpf);
        this.nivelDeAcesso = nivelDeAcesso;
        setUser(user);
        setPassword(password);
    }

//  Getters e Setters

    public int getNivelDeAcesso() {
        return nivelDeAcesso;
    }

    public String getUser() {
        return user;
    }

    public final void setUser(String user) {
        
        if(user.equals(""))
            this.user = ID.gerarID(super.getNome(), super.getCpf());
        else
            this.user = user;
    }
    
    public String getPassword() {
        return this.password;
    }    

    public final void setPassword(String password) {
        if (password.equals(""))
            throw new IllegalArgumentException("A senha não pode ser vazia.");
        
        else
            this.password = password;
    }

    
//  Métodos de Usuário
    
    public Cliente incluirCliente(){
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("-------------- Incluir Cliente ------------------");
        System.out.println("Digite o nome do Cliente: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF do Cliente: ");
        String cpfNumber = scanner.nextLine();
        System.out.println("Digite o Telefone do Cliente: ");
        String telefone = scanner.nextLine();
        System.out.println("Digite o Endereço do Cliente: ");
        String endereco = scanner.nextLine();
        System.out.println("Digite o ID do Cliente: ");
        String idCliente = scanner.nextLine();
        System.out.println("-------------------------------------------------");
        
        
        try{
//          Verificar se existe o caminho de diretórios até clientes
            if (!Files.exists(Path.of("\\registros\\clientes")))
//              Se não existir, criar os diretórios
                Files.createDirectories(Path.of("\\registros\\clientes"));
            
//          Verificar se existe um cliente cadastrado baseado no ID
            if(Cliente.existeCliente(idCliente))
//              Se existir, retornar esse cliente
                return Cliente.findCliente(idCliente);
        }
        catch(IOException e){
//          Tratar erro caso não seja possível criar diretórios
            System.err.println(e);
        }
        
//      Criar novo objeto Cliente caso esse ainda não esteja registrado
        Cliente cliente = new Cliente(idCliente, endereco, telefone, nome, cpfNumber);
        
        try{
//          Objeto conversor de objeto para Json
            Gson gson = new Gson();
            
//          Escrever um novo arquivo de Cliente no diretório nomeado por ID.json
            Files.writeString(Path.of("\\registros\\clientes", 
                    String.format("%s.json", cliente.getIdCliente())),
                    gson.toJson(cliente));
        }
        catch(IOException e){
//          Tratar exceção caso não seja possível escrever arquivo
            System.err.println(e);
            return null;
        }
        
//      Retornar Cliente registrado
        return cliente;
    }
    
    
//  Overrides
    
    @Override
    public String toString(){
        return String.format("%sNível de Acesso: %d\nSenha: %s\n", 
                super.toString(),getNivelDeAcesso(), getPassword());
    }
    
}
