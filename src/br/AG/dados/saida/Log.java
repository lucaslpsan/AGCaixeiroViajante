package br.AG.dados.saida;


import br.AG.dados.Constantes;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Lucas on 22/10/2016.
 */
public class Log{

    PrintWriter out = new PrintWriter("log.txt");
    SimpleDateFormat sdf;

    public Log() throws IOException {
        out.println("Arquivo de Log - AGCaixeiroViajante v.0.6 ¯\\_(ツ)_/¯ ");
        out.println("Lucas Lopes© 2016 \n");
        out.println("Gerações criadas: " + Constantes.GERACOES.getValor());
        out.println("Início - " + horario() + "\n");
    }

    public void escrever(String string){
        out.println(string);
    }

    public void fecharLog(){
        out.println("\nFinal - " + horario());
        out.close();
    }

    private String horario(){
        sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        Date hora = Calendar.getInstance().getTime();
        String dataFormatada = sdf.format(hora);

        return dataFormatada;
    }
}
