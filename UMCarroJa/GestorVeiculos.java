
import java.util.*;

public class GestorVeiculos{
    
    // Variáveis de Instância
    
    private Map<Integer,Veiculos> gestor;
    
    
    Comparator<Veiculo> comparaPrecos = (a,b) -> {if(a.getPreco() == b.getPreco()) return 0;
                                                  if(a.getPreco() > b.getPreco()) return 1;
                                                  else return -1;
                                                 };
               
                                                 
                                                 
    // Construtores
    
    public GestorVeiculos(){
        this.gestor = new HashMap<>();   
    }
    
    public GestorVeiculos (GestorVeiculos gv){
        this.gestor = gv.getGestor();
    }
    
    public GestorVeiculos (Map<Integer,Veiculos> gestor){
        setGestor(gestor);
    }
    
    // Gets
    
    public Map<Integer,Veiculos> getGestor(){
        Map<Integer,Veiculos> aux = new HashMap<Integer,Veiculos>();
        this.gestor.forEach((k,v) -> aux.put(k,v.clone()));
        return aux;
    }
    
    // Sets
    
    public void setGestor (Map<Integer,Veiculos> newGestor){
        this.gestor = new HashMap<Integer,Veiculos>();
        newGestor.forEach((k,v) -> this.gestor.put(k,v.clone())); 
    }
    
    // Clone
    
    public GestorVeiculos clone(){
        return new GestorVeiculos(this);
    }
    
    // Equals
    
    public boolean equals (Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        
        GestorVeiculos gv = (GestorVeiculos)o;
        
        return this.gestor.equals(gv.getGestor());
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("---------- Gestor Veículos ----------\n");
        this.gestor.forEach((k,v) -> sb.append(v.toString()));
        return sb.toString();
    }
    
    // Adiciona um veículo
    
    public void insereVeiculo (Veiculo v){
        Veiculos aux = this.gestor.get(v.getNif());
        aux.addVeiculo(v);
    }
    
    // remove um veículo
    
    public void removeVeiculo (Veiculo v){
        Veiculos aux = this.gestor.get(v.getNif());
        aux.removeVeiculo(v);
    }
    
    // Liberta o Gestor de Veículos
    
    public void libertaGestor(){
        this.gestor.clear();
    }
    
    
    
}