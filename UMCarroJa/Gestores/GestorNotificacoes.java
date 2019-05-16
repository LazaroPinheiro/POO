package Gestores;

import ClassesBase.*;
import java.util.*;
import Exceptions.*;

public class GestorNotificacoes{
    
    // Variáveis de Instância
    
    private Map<Integer,List<Notificacao>> caixaNotificacoes;//int - nif

    // Construtores
    
    public GestorNotificacoes(){
        this.caixaNotificacoes = new HashMap<>();
    }

    public GestorNotificacoes(Map<Integer,List<Notificacao>> caixaNotificacoes) {
        setCaixaNotificacoes(caixaNotificacoes);
    }

    public GestorNotificacoes(GestorNotificacoes gestorNotificacoes){
        this.caixaNotificacoes = gestorNotificacoes.getCaixaNotificacoes();
    }

    // Gets
    
    public Map<Integer,List<Notificacao>> getCaixaNotificacoes(){
        Map<Integer,List<Notificacao>> aux = new HashMap<Integer,List<Notificacao>>();
        for(int nif : this.caixaNotificacoes.keySet()){
            List<Notificacao> notificacoes = this.caixaNotificacoes.get(nif);
            List<Notificacao> auxList = new ArrayList<>();
            for(Notificacao notificacao : notificacoes){
                auxList.add(notificacao.clone());
            }
            aux.put(nif,auxList);
        }
        return aux;
    }
    
    // Sets
    
    public void setCaixaNotificacoes(Map<Integer,List<Notificacao>> caixaNotificacoes){
        this.caixaNotificacoes = new HashMap<Integer,List<Notificacao>>();
        for(int nif : caixaNotificacoes.keySet()){
            List<Notificacao> notificacoes = caixaNotificacoes.get(nif);
            List<Notificacao> auxList = new ArrayList<>();
            for(Notificacao notificacao : notificacoes){
                auxList.add(notificacao.clone());
            }
            this.caixaNotificacoes.put(nif,auxList);
        }
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("----------------- GESTOR NOTIFICACOES -----------------\n");
        this.caixaNotificacoes.forEach((k,v) -> sb.append("\nNif: " + k + "\n" + v.toString() + "\n"));
        return sb.toString();
    }
    
    // Equals
    
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        GestorNotificacoes gestorNotificacoes = (GestorNotificacoes)o;
        
        return this.caixaNotificacoes.equals(gestorNotificacoes.getCaixaNotificacoes());
    }
    
    // Clone
    
    public GestorNotificacoes clone(){
        return new GestorNotificacoes(this);
    }
    
    // Adiciona uma Utilizador
    public void adicionaUtilizador(Utilizador u) throws UtilizadorJaExisteException{
        if(this.caixaNotificacoes.containsKey(u.getNif())){
            throw new UtilizadorJaExisteException("Utilizador com  o nif " + u.getNif() + "já existe no sistema!");
        }
        List<Notificacao> aux = new ArrayList<>();
        this.caixaNotificacoes.put(u.getNif(),aux);
    }
    
    
    //Adiciona uma notificação ao gestor de notificações
    public void adicionaNotificacao(Notificacao n){
        List<Notificacao> notificacoes = this.caixaNotificacoes.get(n.getDestinatario());
        if(!notificacoes.contains(n)){
            notificacoes.add(n.clone());
        }
    }
    
    
    // Vefifica se um utilizador tem notificações
    
    public boolean temNotificacoes (int nif) throws UtilizadorNaoExisteException{
        if(!this.caixaNotificacoes.containsKey(nif)){
            throw new UtilizadorNaoExisteException("O nif " + nif + "não existe no sistema!");
        }
        List<Notificacao> notificacoes = this.caixaNotificacoes.get(nif);
        return notificacoes.isEmpty();
    }
    
    // Devolve quantas notificações tem um utilizador
    
    public int quantasNotificacoes (int nif) throws UtilizadorNaoExisteException{
        if(!this.caixaNotificacoes.containsKey(nif)){
            throw new UtilizadorNaoExisteException("O nif " + nif + "não existe no sistema!");
        }
        List<Notificacao> notificacoes = this.caixaNotificacoes.get(nif);
        return notificacoes.size();
    }
    
    public List<Notificacao> getNotificacoes(int nif) throws UtilizadorNaoExisteException, NotificacaoNaoExisteException{
        if(!this.caixaNotificacoes.containsKey(nif)){
            throw new UtilizadorNaoExisteException("O nif " + nif + "não existe no sistema!");
        }
        List<Notificacao> aux = this.caixaNotificacoes.get(nif);
        if(aux.isEmpty()){
            throw new NotificacaoNaoExisteException("A sua caixa de notificações está vazia!");
        }
        List<Notificacao> notificacoes = new ArrayList<>();
        for(Notificacao notificacao : aux){
            notificacoes.add(notificacao);
        }
        return notificacoes;
    }
    
    //Apaga notificacao
    
    public void apagaNotificacao(Notificacao n) throws NotificacaoNaoExisteException{
        if(!this.caixaNotificacoes.containsValue(n)){
            throw new NotificacaoNaoExisteException("Ups! Noficacao não existe!");
        }
        this.caixaNotificacoes.get(n.getDestinatario()).remove(n);
    }
    
    // Apaga o gestor de notificações
    
    public void apagaCaixaNotificacoes(){
        this.caixaNotificacoes.clear();
    }
}

