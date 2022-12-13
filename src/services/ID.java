package services;


public abstract class ID {
    
//    Sobrecarga do método gerar ID com 1 Parâmetro
    public static String gerarID(String base){
        return base.hashCode() < 0 ? 
                String.format("%s", base.hashCode() * -1) :
                String.format("%s", base.hashCode());       
    }
    
//    SObrecarga do método gerar ID com 2 Parâmetros
    public static String gerarID(String base_1, String base_2){
        return String.format("%d", 
                (base_1.hashCode()/2) + (base_2.hashCode()/2));   
    }
    
}
