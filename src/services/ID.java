package services;

/**
 * <p>Classe responsável pelos métodos de geração de ID</p>
 * @author raulv
 * @since 1.0
 */
public abstract class ID {
    
/**
 * <p>Retorna um String unico gerado a partir da função hashcode aplicada em um argumento.</p>
 * @param base
 * @return String
 * @since 1.0
 */
    public static String gerarID(String base){
        return base.hashCode() < 0 ? 
                String.format("%s", base.hashCode() * -1) :
                String.format("%s", base.hashCode());       
    }
    
/**
 * <p>Retorna um String unico gerado a partir da função hashcode aplicada em dois argumentos.</p>
 * @param base_1
 * @param base_2
 * @return String
 * @since 1.0
 */
    public static String gerarID(String base_1, String base_2){
        long aux = (base_1.hashCode()/2) + (base_2.hashCode()/2);
        
        return String.format("%d", 
                (aux < 0) ? aux*-1 : aux);   
    }

//  Questão 03 - Sobrescrever o método toString() de todas as classes implementadas.
    @Override
    public String toString() {
        return "ID : Essa classe disponibiliza métodos para geração de um ID único.";
    }
}
