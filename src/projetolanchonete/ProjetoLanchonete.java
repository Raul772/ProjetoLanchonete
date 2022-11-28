package projetolanchonete;

import entities.Administrador;
import entities.Cliente;
import entities.Funcionario;
import java.io.IOException;

public class ProjetoLanchonete {

    public static void main(String[] args) throws IOException {
       
        
        Cliente a = new Cliente(0, "Rua xuxa", "40028922", "Júlia", "12345678990");
        Administrador b = new Administrador(0, "Bianca", "09876543210");
        Funcionario c = b.incluirFuncionario(0, "Laura", "423424234346");
        Funcionario d = b.incluirFuncionario(1, "Bianca", "12312313236");
        Funcionario e = b.incluirFuncionario(2, "Larissa", "4323454656");
        Funcionario f = b.incluirFuncionario(3, "Xuxa Meneghel", "43378463746");
        
        System.out.println(a);
        System.out.println("\n");
        System.out.println(b);
        System.out.println("\n");
        System.out.println(c);
        System.out.println("\n");
        System.out.println(d);
        System.out.println("\n");
        System.out.println(e);
        System.out.println("\n");
        System.out.println(f);
       
    }
    
}
