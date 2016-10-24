package br.AG.classes;

import br.AG.dados.Constantes;
import br.AG.dados.saida.Log;
import br.AG.excecao.Excecao;

import java.io.IOException;
import java.util.*;

import static br.AG.dados.entrada.Dados.carregaMapa;

/**
 * Created by Lucas on 20/10/2016.
 *
 * TODO - Cromossomo ou cromossoma?
 */
public class Principal {

    static List<Desempenho> desempenhos = new ArrayList<Desempenho>();
    static List<Desempenho> rankFinal = new ArrayList<Desempenho>();
    static Log log;

    public static void main(String args[]) throws IOException, Excecao {
        log = new Log();
        List<No> mapa = carregaMapa();


        //System.out.println(mapa);

        log.escrever("Grafo de referência: " + mapa.toString() + "\n");

        //Com todas as cidades e caminhos carregados, cada cidade a partir de agora será um alelo
        //Serão 6 cromossomos(indivíduos) por geração, sendo 20000 geracões ao total

        List<No> c1, c2, c3, c4, c5, c6, cPai1, cPai2, cPai3;
        c1 = new ArrayList<>(mapa);
        c2 = new ArrayList<>(mapa);
        /*
        c3 = new ArrayList<>(mapa);
        c4 = new ArrayList<>(mapa);
        c5 = new ArrayList<>(mapa);
        c6 = new ArrayList<>(mapa);
        */


        //crossover(c1, c2);
        //1º geração será gerada aleatóriamente.

        Collections.shuffle(c1);
        Collections.shuffle(c2);
        /*
        Collections.shuffle(c3);
        Collections.shuffle(c4);
        Collections.shuffle(c5);
        Collections.shuffle(c6);
        */

        //Gerações
        for (int g = 0; g < Constantes.GERACOES.getValor(); g++) {

            teste(c1);
            teste(c2);
            //teste(c3);
            //teste(c4);
            //teste(c5);
            //teste(c6);

            //Temos que retirar os cromossomos que por ventura sejam iguais

            desempenhos = new ArrayList<Desempenho>(new HashSet<>(desempenhos)); //Vou usar a interface HashSet que não permiter criar Collections com valores duplicados

            Collections.sort(desempenhos); //Ordena a lista de desempenho por valor

            //Os dois primeiros(mais rápidos) serão considerados cromossomos pais, e gerarão outros 2 indivíduos para mais uma geração
            try {

                if (!desempenhos.isEmpty()) {
                    if (desempenhos.size() == 2) {
                        cPai1 = desempenhos.get(0).getCromossomo();
                        cPai2 = desempenhos.get(1).getCromossomo();

                        //cPai3 = desempenhos.get(2).getCromossomo();


                        //Cruzamento dos cromossomos pais ( ͡° ͜ʖ ͡°)
                        c1 = cruzamento(cPai1, cPai2); // 1 - 2
                        c2 = cruzamento(cPai2, cPai1); // 2 - 1
                        //c3 = cruzamento(cPai2, cPai3); // 2 - 3
                        //c4 = cruzamento(cPai3, cPai2); // 3 - 2
                        //c5 = cruzamento(cPai1, cPai3); // 1 - 3
                        //c6 = cruzamento(cPai3, cPai1); // 3 - 1

                        rankFinal.add(desempenhos.get(0));
                        rankFinal.add(desempenhos.get(1));

                    } else {
                        System.out.println("\nEncontrado! = " + desempenhos);
                        log.escrever("\nCromossomo Encontrado:[fitness,distância] = " + desempenhos);

                        rankFinal.add(desempenhos.get(0));

                        desempenhos.clear();

                        c1 = new ArrayList<>(mapa);
                        c2 = new ArrayList<>(mapa);

                        //Se não tiver pais suficientes então a próxima geração será de cromossomos mutantes(aleatórios) - vou embaralhar tudo, hehehe
                        Collections.shuffle(c1);
                        Collections.shuffle(c2);
                    }
                } else {
                    c1 = new ArrayList<>(mapa);
                    c2 = new ArrayList<>(mapa);

                    //Se não tiver pais suficientes então a próxima geração será de cromossomos mutantes(aleatórios) - vou embaralhar tudo, hehehe
                    Collections.shuffle(c1);
                    Collections.shuffle(c2);
                }

            } catch (Exception e) {
                throw new Excecao("Aconteceu um errinho! Tente novamente, vai que funciona... ¯\\_(ツ)_/¯ ", e);
            }
        }

        Collections.sort(rankFinal);
        System.out.println("\nCromossomo Vencedor:[fitness,distância] = " + rankFinal.get(0) + " Sequência: " + rankFinal.get(0).getHistorico());
        log.escrever("\nCromossomo Vencedor:[fitness,distância] = " + rankFinal.get(0) + " Sequência: " + rankFinal.get(0).getHistorico());

        log.fecharLog();
    }

    private static void teste(List<No> no) {
        //Controle do for abaixo
        No aleloAtual;
        char aleloProximo;
        boolean falha = false;

        double valor = 0;
        int fitness = 0;

        List<Character> historico = new ArrayList<Character>();


        for (int i = 0; i < no.size(); i++) {
            aleloAtual = no.get(i);
            if (i < no.size() - 1) {
                aleloProximo = no.get(i + 1).getNome();
            } else {
                aleloProximo = no.get(0).getNome();
            }

            for (Ligacao ligacao : aleloAtual.getLigacaoes()) {
                if (ligacao.getProximoNo() == aleloProximo) {
                    valor += ligacao.getValor();
                    fitness++;
                    historico.add(aleloAtual.getNome());
                    break;
                }

                if (aleloAtual.getLigacaoes().get(aleloAtual.getLigacaoes().size() - 1).equals(ligacao)) { ///Meio grande, mas... verifica se a ligacao que está em teste é a última do alelo
                    falha = true; //Algum alelo desse cromossomo não tem ligação com o próximo, ou seja a sequência é inválida
                    break;
                }
            }
        }

        if (!falha) { //Se o cromossomo é funcional então é escrito no log e registrado na lista de desempenho
            historico.add(no.get(0).getNome());

            System.out.println("\n" + no);
            log.escrever(no.toString());
            System.out.println(valor);
            log.escrever(Double.toString(valor));
            System.out.println(fitness);
            log.escrever(Integer.toString(fitness));
            System.out.println("\n" + historico);
            log.escrever("\n" + historico.toString() + "\n");

            desempenhos.add(new Desempenho(fitness, valor, no, historico));
        }
    }

    private static List<No> cruzamento(List<No> lista1, List<No> lista2) {//Criando um novo cromossomo, a partir de dois pais, ele vai conter alelos intercaladamente(um alelo de um, um alelo de outro) :D
        int count = 0;
        List<No> resultado = new ArrayList<No>();

        while (count < lista1.size()) { //Meio ruim, mas funciona, faz o controle de índices na criação, senão iria levantar exceção
            resultado.add(lista1.get(count++));
            if (count < lista1.size()) {
                resultado.add(lista2.get(count++));
            }
        }

        return resultado;
    }

    private static No pesquisa(List<No> lista, char nomePesquisado) {//Retorna um nó que foi pesquisado pelo nome
        for (No no : lista) {
            if (no.getNome() == nomePesquisado)
                return no;
        }
        return null;
    }

    //E que os jogos comecem, HAHAHAHA!
}
