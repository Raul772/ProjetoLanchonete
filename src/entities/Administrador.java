
package entities;

public class Administrador extends Pessoa{
    
    protected static long id = 0;
    private long idAdministrador;
    
    public Administrador(){}

    public Administrador(String nome, String cpf) {
        super(nome, cpf);
        setIdAdministrador();
    }
    
    public Administrador(long idAdministrador, String nome, String cpf) {
        super(nome, cpf);
        setIdAdministrador(idAdministrador);
    }

    public void setIdAdministrador(long idAdministrador) {
        this.idAdministrador = idAdministrador;
    }
    
     public void setIdAdministrador() {
        this.idAdministrador = id + 1;
        Administrador.id = this.idAdministrador;
    }
    
    public long getIdAdministrador() {
        return idAdministrador;
    }
    
    @Override
    public String toString() {
        return String.format("%sID Administrador: %d\n", 
                super.toString(), getIdAdministrador());
    }
    
}
