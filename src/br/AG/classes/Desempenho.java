package br.AG.classes;

import java.util.List;

/**
 * Created by Lucas on 22/10/2016.
 */
public class Desempenho implements  Comparable<Desempenho>{
    int fitness;
    double distancia;
    List<No> cromossomo;
    List<Character> historico;

    public Desempenho(int fitness, double distancia, List<No> cromossomo, List<Character> historico) {
        this.fitness = fitness;
        this.distancia = distancia;
        this.cromossomo = cromossomo;
        this.historico = historico;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public List<No> getCromossomo() {
        return cromossomo;
    }

    public void setCromossomo(List<No> cromossomo) {
        this.cromossomo = cromossomo;
    }

    public List<Character> getHistorico() {
        return historico;
    }

    public void setHistorico(List<Character> historico) {
        this.historico = historico;
    }

    @Override
    public int compareTo(Desempenho desempenho) {
        if(this.distancia < desempenho.distancia){
            return -1;
        }
        if(this.distancia > desempenho.distancia){
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "{" + fitness + ", " + distancia + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Desempenho that = (Desempenho) o;

        if (fitness != that.fitness) return false;
        if (Double.compare(that.distancia, distancia) != 0) return false;
        return cromossomo != null ? cromossomo.equals(that.cromossomo) : that.cromossomo == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = fitness;
        temp = Double.doubleToLongBits(distancia);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (cromossomo != null ? cromossomo.hashCode() : 0);
        return result;
    }
}
