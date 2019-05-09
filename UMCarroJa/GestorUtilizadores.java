import java.util.*;
/**
 * Escreva a descrição da classe GestorUtilizador aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 */
public class GestorUtilizadores {
    private Collection<Utilizador> utilizadores;


    public GestorUtilizadores() {
        this.utilizadores = new HashSet<>();
    }

    public GestorUtilizadores(Collection<Utilizador> utilizadores) {
        setUtilizadores(utilizadores);
    }

    public GestorUtilizadores(GestorUtilizadores gUtilizadores) {
        gUtilizadores.getUtilizadores();
    }

    public Collection<Utilizador> getUtilizadores() {
        Collection<Utilizador> aux = new HashSet<>();
        for (Utilizador u : this.utilizadores) {
            aux.add(u.clone());
        }
        return aux;
    }

    public void setUtilizadores(Collection<Utilizador> utilizadores) {
        this.utilizadores = new HashSet<>();
        for (Utilizador u : utilizadores) {
            this.utilizadores.add(u.clone());
        }
    }


    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        GestorUtilizadores gUtilizadores = (GestorUtilizadores)o;
        
        return this.utilizadores.equals(gUtilizadores.getUtilizadores()); 
    }
    
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-------------- Utilizadores --------------\n");
        for(Utilizador u: this.utilizadores){
            sb.append(u.toString());
        }
        return sb.toString();
    }
    
    
    public GestorUtilizadores clone(){
        return new GestorUtilizadores(this);
    }
    
    //-------------
    
    public void insereUtilizador(Utilizador u) throws UtilizadorExistenteException{
        if(this.utilizadores.contains(u)){
            throw new UtilizadorExistenteException(u);
        }
        this.utilizadores.add(u);
    }
    
    
    public void removeUtilizador(Utilizador u){
        if(this.utilizadores.contains(u)){
            this.utilizadores.remove(u);
        }
    }
    
    public void libertaUtilizadores(){
        this.utilizadores.clear();
    }
}