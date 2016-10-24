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
        No aleloUsadoPesquisa = null;

        List<Character> historico = new ArrayList<Character>();
        //historico = new ArrayList<Character>();
        //historico.clear();

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
                    //Se for, significa que o alelo atual não possui ligação com o próximo alelo do cromosso, neste ponto vou avançar nos aos nós ligados à este até achar um alelo que ligue(só os nós ligados diretos)
                    /*valor += ligacao.getValor();
                    fitness++;
                    historico.add(aleloAtual.getNome());

                    No noProximo;
                    double menorValor = 0.0;
                    int menorFitness = 0;

                    //Vou pesquisar se o próximo nó do cromossomo está em algum nó ligado ao nó atual
                    for (Ligacao ligacao1 : aleloAtual.getLigacaoes()) {
                        noProximo = pesquisa(no, ligacao1.getProximoNo()); //Obtém o nó que está na ligação do atual
                        if (noProximo == null)
                            break;

                        for (Ligacao ligacaoPesquisado : noProximo.getLigacaoes()) {
                            if (ligacaoPesquisado.getProximoNo() == aleloProximo && (ligacaoPesquisado.getValor() < menorValor || menorValor == 0.0)) {
                                menorValor = ligacaoPesquisado.getValor();
                                menorFitness = 1;
                                aleloUsadoPesquisa = noProximo;
                            }
                        }
                    }

                    if (menorFitness == 0) {
                        falha = true;
                        break;
                    } else {
                        valor += menorValor;
                        fitness += menorFitness;
                        historico.add(aleloUsadoPesquisa.getNome());
                    }*/
                    falha = true; //Algum alelo desse cromossomo não tem ligação com o próximo
                    break;
                }
            }
        }

        if (!falha) {
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

    /*private static void crossover(List<No> primeiro, List<No> segundo){
        int alelos = primeiro.size() - 1;//O índice do array começa em zero, temos que subtrair 1 do tamanho do array para que a variável seja usada como índice
        int quant = alelos;//quant é responsável pela carga dos números na lista
        int aleloAleatorio, valorAleatorio;

        //lista guardará os números que representam os alelos
        List<Integer> lista = new ArrayList<>();
        //Esse while carrega a lista com os números dos alelos, em sequência
        while (quant >= 0){
            lista.add(quant--);
        }

        Collections.shuffle(lista); //Essa função embaralhará nossa lista, assim os números serão aleatórios e não repetidos.

        for(;alelos > 0 ; alelos--) {//Começa a partir do último alelo
            aleloAleatorio = lista.get(alelos);
            valorAleatorio = (int) (Math.random() * 10); //Essa fórmula irá gerar um número aleatório entre 0 e 9

            if(valorAleatorio <= 4){
                primeiro.set(alelos, segundo.get(aleloAleatorio));
            }
            if(valorAleatorio >= 5){
                segundo.set(alelos, primeiro.get(aleloAleatorio));
            }
        }
    }*/

    /*private static void crossover(List<No> primeiro, List<No> segundo){
        //repetiçao primeiro.size()
        int alelos = primeiro.size() - 1;//O índice do array começa em zero, temos que subtrair 1 do tamanho do array para que a variável seja usada como índice
        int valorAleatorio;
        int aleloAleatorio;

        List<Integer> lista = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(lista);

        for(;alelos > 0 ; alelos--) {//Começa a partir do último alelo
            valorAleatorio = (int) (Math.random() * 30); //Essa fórmula irá gerar um número aleatório entre 0 e 29
            aleloAleatorio = lista.get(alelos);

            //Se o valor estiver entre 0 e 9 (10 números possíveis), o primeiro cromossomo receberá o alelo correspondente do segundo
            if(valorAleatorio < 10) {
                primeiro.set(alelos, segundo.get(aleloAleatorio));
            }//Se o valor estiver entre 20 e 29 (10 números possíveis), o segundo cromossomo receberá o alelo correspondente do primeiro
            if (valorAleatorio  > 19){
                segundo.set(alelos, primeiro.get(aleloAleatorio));
            }//Se o valor estiver entre 10 e 19 (10 números possíveis), não haverá mudança nesse alelo
        }
    }*/


    //E que os jogos comecem, HAHAHAHA!
}
