import java.util.*;
import java.time.LocalDate;
/**
 * Escreva a descrição da classe Proprietario aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Proprietario extends Utilizador
{
    //variaveis de instancia
    private double classificacao;
    private Collection <Double> classificacoes;
    
    
    /**
     * Construtores da classe Proprietario.
     * Declaracao dos construtores por omissao (vazio),
     * parametrizado e de copia.
     */
  
    /**
     * Construtor por omissao de Proprietario.
     */
    public Proprietario(){
        super();
        this.classificacao = 0;
        this.classificacoes = new ArrayList<>();
    }
    
    /**
     * Construtor parametrizado de Proprietario.
     * Aceita como parametros o email, o nome, a password,
     * a morada e a data de nascimento de um Proprietario.
     */
    public Proprietario(String email, String password, String nome, int nif, String morada, LocalDate dataNascimento, 
                        double classificacao, Collection<Double> classificacoes){
        super(email, password, nome, nif, morada);
        this.classificacao = classificacao;
        setClassificacoes(classificacoes);
    }
    
    
    public Proprietario(Proprietario proprietario){
        super(proprietario);
        this.classificacao = proprietario.getClassificacao();
        this.classificacoes = proprietario.getClassificacoes();
    }

    // Gets
    
    public double getClassificacao(){
        return this.classificacao;
    }
    
    public Collection<Double> getClassificacoes(){
        Collection<Double> aux = new ArrayList<Double>();
        for (double i : this.classificacoes){
            aux.add(i);
        }
        return aux;
    }
    
    // Sets
    
    public void setClassificacao (double classificacao){
        this.classificacao = classificacao;
    }
    
    public void setClassificacoes (Collection<Double> newClassificacoes){
        this.classificacoes = new ArrayList<Double>();
        for(double i : newClassificacoes){
            this.classificacoes.add(i);
        }
    }
    
    // Equals
    
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        
        Proprietario p = (Proprietario)o;
        
        return super.equals(p) && this.classificacao == p.getClassificacao() && this.classificacoes.equals(p.getClassificacoes());
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Classificacao:   " + this.classificacao + ";\n");
        return sb.toString();
    }
    
    // Clone
    
    public Proprietario clone(){
        return new Proprietario(this);
    }
    
    
    //-----------------------------------------------------
    
    public void novaAvaliacao(double avaliacao) throws AvaliacaoInvalidaException{
        if(avaliacao < 0 || avaliacao > 100){
            throw new AvaliacaoInvalidaException("" + avaliacao);
        }
        if(this.classificacoes.isEmpty()){
            this.classificacoes.add(this.classificacao);
        }
        this.classificacao = 0;
        this.classificacoes.add(avaliacao);
        for(double n : this.classificacoes){
            this.classificacao += n;
        }
        this.classificacao /=  this.classificacoes.size();
    }
    
    public void confirmaAluguer(Aluguer a){
        if(a.getEstado().equals(EstadoAluguer.Espera)){
            a.setEstado(EstadoAluguer.Alugado);
        }
    }
}
