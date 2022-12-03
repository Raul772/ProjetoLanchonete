package entities;

import com.google.gson.Gson;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.Scanner;

public class Administrador extends Usuario{
    
    
//  Sobrecargas de Construtor
    
    public Administrador(){
        super(2);
    }
    
    public Administrador(String nome, String cpf,String user, String password) {
        super(nome, cpf, 2, user, password);
    }

//  Getters e Setters
    
    
//  Métodos de Administrador
    
    public Funcionario incluirFuncionario(){
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("-------------- Incluir Funcionario ------------------");
        System.out.println("Digite o nome do Funcionario: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF do Funcionario: ");
        String cpfNumber = scanner.nextLine();
        System.out.println("Digite o User do Funcionario: ");
        String userFuncionario = scanner.nextLine();
        System.out.println("Digite a senha do Funcionario: ");
        String senha = scanner.nextLine();
        System.out.println("-------------------------------------------------");
       
        
        try{
//          Verificar se existe o caminho de diretórios até funcionarios
            if (!Files.exists(Path.of("\\registros\\funcionarios")))
//              Se não existir, criar os diretórios
                Files.createDirectories(Path.of("\\registros\\funcionarios"));
            
//          Verificar se existe um Funcionario cadastrado baseado no User
            if(Funcionario.existeFuncionario(userFuncionario))
//              Se existir, retornar esse Funcionario
                return Funcionario.findFuncionario(userFuncionario);
        }
        catch(IOException e){
//          Tratar erro caso não seja possível criar diretórios
            System.err.println(e);
        }
        
//      Criar novo objeto Funcionario caso esse ainda não esteja registrado
        Funcionario funcionario = new Funcionario( nome,
                cpfNumber, userFuncionario, senha);
        
        try{
//          Objeto conversor de objeto para Json
            Gson gson = new Gson();
            
//          Escrever um novo arquivo de Funcionario no diretório nomeado por ID.json
            Files.writeString(Path.of("\\registros\\funcionarios", 
                    String.format("%s.json", userFuncionario)),
                    gson.toJson(funcionario));
        }
        catch(IOException e){
//          Tratar exceção caso não seja possível escrever arquivo
            System.err.println(e);
            return null;
        }
        
//      Retornar Funcionario registrado
        return funcionario;
    }
    
    public Administrador incluirAdministrador(){
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("-------------- Incluir Administrador ------------------");
        System.out.println("Digite o nome do Administrador: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF do Administrador: ");
        String cpfNumber = scanner.nextLine();
        System.out.println("Digite o User do Administrador: ");
        String userAdministrador = scanner.nextLine();
        System.out.println("Digite a senha do Administrador: ");
        String senha = scanner.nextLine();
        System.out.println("-------------------------------------------------");
       
        
        try{
//          Verificar se existe o caminho de diretórios até Administradores
            if (!Files.exists(Path.of("\\registros\\administradores")))
//              Se não existir, criar os diretórios
                Files.createDirectories(Path.of("\\registros\\administradores"));
            
//          Verificar se existe um administrador cadastrado baseado no User
            if(Administrador.existeAdministrador(userAdministrador))
//              Se existir, retornar esse administrador
                return Administrador.findAdministrador(userAdministrador);
        }
        catch(IOException e){
//          Tratar erro caso não seja possível criar diretórios
            System.err.println(e);
        }
        
//      Criar novo objeto Administrador caso esse ainda não esteja registrado
        Administrador administrador = new Administrador( nome,
                cpfNumber, userAdministrador, senha);
        
        try{
//          Objeto conversor de objeto para Json
            Gson gson = new Gson();
            
//          Escrever um novo arquivo de Administrador no diretório nomeado por ID.json
            Files.writeString(Path.of("\\registros\\administradores", 
                    String.format("%s.json", userAdministrador)),
                    gson.toJson(administrador));
        }
        catch(IOException e){
//          Tratar exceção caso não seja possível escrever arquivo
            System.err.println(e);
            return null;
        }
        
//      Retornar Administrador registrado
        return administrador;
    }
    
    public static boolean existeAdministrador(String user){

        Path filePath = Path.of("\\registros\\administradores", 
                    String.format("%s.json", user));
        
        return (Files.exists(filePath));
    }
    
    public boolean existeAdministrador(){
        
        Path filePath = Path.of("\\registros\\administradores", 
                        String.format("%s.json", this.getUser()));
        
        return (Files.exists(filePath));
    }
    
    public static Administrador findAdministrador(String user) throws IOException{
        Gson gson = new Gson();
        Path filePath = Path.of("\\registros\\administradores", 
                            String.format("%s.json", user));
        
        if (existeAdministrador(user)){
            return gson.fromJson(Files.readString(filePath), Administrador.class);
        }else{
            throw new IOException("Não foi possível encontrar o registro.");
        }
    }
    
    
//  Overrides
    
    @Override
    public String toString() {
        return String.format("%sID Administrador: %s\n", 
                super.toString(), getUser());
    }
    
}
