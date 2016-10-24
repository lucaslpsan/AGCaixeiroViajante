package br.AG.classes;

import java.util.ArrayList;

/**
 * Created by Lucas on 20/10/2016.
 */
public class No {
    char nome;
    ArrayList<Ligacao> ligacaoes = new ArrayList<>();


    public No() {
    }

    public No(char nome, ArrayList<Ligacao> ligacaoes) {
        this.nome = nome;
        this.ligacaoes = ligacaoes;
    }

    @Override
    public String toString() {
        return "Cidade " + nome + '{' + ligacaoes + '}';
    }

    public char getNome() {
        return nome;
    }

    public ArrayList<Ligacao> getLigacaoes() {
        return ligacaoes;
    }

}
