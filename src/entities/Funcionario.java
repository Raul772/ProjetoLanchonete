
package entities;


public class Funcionario extends Pessoa{
    
    
    private long idFuncionario;
    
    public Funcionario(){}

    public Funcionario(long idFuncionario, String nome, String cpf) {
        super(nome, cpf);
        setIdFuncionario(idFuncionario);
    }

    public long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
    
     @Override
    public String toString() {
        return String.format("%sID Funcionário: %d\n", 
                super.toString(), getIdFuncionario());
    }
}
