package br.AG.dados;

/**
 * Created by Lucas on 23/10/2016.
 */
public enum Constantes {
    GERACOES(20000);

    int valor;


    Constantes(int i) {
        this.valor = i;
    }

    public int getValor(){
        return valor;
    }
}
