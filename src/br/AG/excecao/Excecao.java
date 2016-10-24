package br.AG.excecao;

/**
 * Created by Lucas on 23/10/2016.
 */
public class Excecao extends Exception {

    public Excecao(String msg){
        super(msg);
    }

    public Excecao(String msg, Exception e){
        super(msg, e);
    }
}
