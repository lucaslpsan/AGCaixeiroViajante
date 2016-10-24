package br.AG.classes;

/**
 * Created by Lucas on 20/10/2016.
 */
public class Ligacao {
    char proximoNo;
    double valor;

    public Ligacao() {
    }

    public Ligacao(char proximoNo, double valor) {
        this.proximoNo = proximoNo;
        this.valor = valor;
    }

    public char getProximoNo() {
        return proximoNo;
    }

    public void setProximoNo(char proximoNo) {
        this.proximoNo = proximoNo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "[" + proximoNo +
                ", " + valor + ']';
    }
}
