package services;


public abstract class ID {
    
    public static String gerarID(String base){
        
        String hash = String.format("%d", 
                base.hashCode() < 0 ? base.hashCode() * -1 : base.hashCode());

        return String.format("%s%s", 
                base.substring(0, base.indexOf(" ")),
                hash.substring(0, 4)); 
                
    }
    
    public static String gerarID(String base_1, String base_2){
        return String.format("%d", 
                (base_1.hashCode()/2) + (base_2.hashCode()/2));   
    }
    
}
