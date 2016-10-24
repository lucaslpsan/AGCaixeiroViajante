package br.AG.dados.entrada;

import br.AG.classes.Ligacao;
import br.AG.classes.No;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lucas on 20/10/2016.
 */
public class Dados {
    //Esse método retorna uma lista com todos os nós e seus respectivas ligações, os valores foram retirados do exemplo.
    public static List<No> carregaMapa() {
        List mapa = new ArrayList<No>(); //Lista de nós
        No no;


        //Cada nó será uma cidade, nele constará todos as ligações que possui, sendo eles cidades ligadas ao nó e suas distâncias.

        //Cidade A
        no = new No('A', new ArrayList<Ligacao>(Arrays.asList(new Ligacao('B', 1.0),
                                                              new Ligacao('C', 1.0),
                                                              new Ligacao('D', 1.5))
                                                ));

        mapa.add(no); //Após a criação da cidade, ela é adicionada ao mapa

        //Cidade B
        no = new No('B', new ArrayList<Ligacao>(Arrays.asList(new Ligacao('A', 1.0),
                                                              new Ligacao('C', 2.0),
                                                              new Ligacao('D', 4.0),
                                                              new Ligacao('E', 3.0))
                                                 ));

        mapa.add(no);

        //Cidade C
        no = new No('C', new ArrayList<Ligacao>(Arrays.asList(new Ligacao('A', 1.0),
                                                              new Ligacao('B', 2.0),
                                                              new Ligacao('D', 3.0),
                                                              new Ligacao('F', 3.0),
                                                              new Ligacao('H', 4.0),
                                                              new Ligacao('J', 5.0))
                                                 ));

        mapa.add(no);

        //Cidade D
        no = new No('D', new ArrayList<Ligacao>(Arrays.asList(new Ligacao('A', 1.5),
                                                              new Ligacao('B', 4.0),
                                                              new Ligacao('C', 3.0),
                                                              new Ligacao('E', 1.5),
                                                              new Ligacao('F', 3.0),
                                                              new Ligacao('G', 4.0))
                                                ));

        mapa.add(no);

        //Cidade E
        no = new No('E', new ArrayList<Ligacao>(Arrays.asList(new Ligacao('B', 3.0),
                                                              new Ligacao('D', 1.5),
                                                              new Ligacao('F', 1.0),
                                                              new Ligacao('G', 1.0))
                                                ));

        mapa.add(no);

        //Cidade F
        no = new No('F', new ArrayList<Ligacao>(Arrays.asList(new Ligacao('C', 3.0),
                                                              new Ligacao('D', 3.0),
                                                              new Ligacao('E', 1.0),
                                                              new Ligacao('G', 2.0),
                                                              new Ligacao('H', 4.0),
                                                              new Ligacao('I', 1.0),
                                                              new Ligacao('J', 3.0))
                                                ));

        mapa.add(no);

        //Cidade G
        no = new No('G', new ArrayList<Ligacao>(Arrays.asList(new Ligacao('D', 4.0),
                                                              new Ligacao('E', 1.0),
                                                              new Ligacao('F', 2.0))
                                                ));

        mapa.add(no);

        //Cidade H
        no = new No('H', new ArrayList<Ligacao>(Arrays.asList(new Ligacao('C', 4.0),
                                                              new Ligacao('F', 4.0),
                                                              new Ligacao('I', 3.0),
                                                              new Ligacao('J', 1.0))
                                                ));

        mapa.add(no);

        //Cidade I
        no = new No('I', new ArrayList<Ligacao>(Arrays.asList(new Ligacao('F', 1.0),
                                                              new Ligacao('H', 3.0),
                                                              new Ligacao('J', 1.0))
                                                ));

        mapa.add(no);

        //Cidade J
        no = new No('J', new ArrayList<Ligacao>(Arrays.asList(new Ligacao('C', 5.0),
                                                              new Ligacao('F', 3.0),
                                                              new Ligacao('H', 1.0),
                                                              new Ligacao('I', 1.0))
                                                ));

        mapa.add(no);

        return mapa;
    }
}
