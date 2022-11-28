package projetolanchonete;

import entities.Administrador;
import entities.Cliente;
import entities.Funcionario;

public class ProjetoLanchonete {

    public static void main(String[] args) {
       
        Funcionario a = new Funcionario(0, "Laura", "43256709856");
        Cliente b = new Cliente(0, "Rua xuxa", "40028922", "Júlia", "12345678990");
        Administrador c = new Administrador(0, "Bianca", "09876543210");
        
        System.out.println(a);
        System.out.println("\n");
        System.out.println(b);
        System.out.println("\n");
        System.out.println(c);
       
    }
    
}
