package Java.ExerciciosLab.Ap1;
import java.util.*;
import java.time.*;

// Exercico 1 e questão 3 da prova
public class CustomDate  {
    private int dia;
    private int mes;
    private int ano;
    public String[] meses = {
            "Janeiro",
            "Fevereiro",
            "Março",
            "Abril",
            "Maio",
            "Junho",
            "Julho",
            "Agosto",
            "Setembro",
            "Outubro",
            "Novembro",
            "Dezembro"
    };

    public CustomDate(int dia, int mes, int ano) {
            this.setDia(dia);
            this.setMes(mes);
            this.setAno(ano);
    }

    public CustomDate () {
        LocalDateTime now = LocalDateTime.now();
        setDia(now.getDayOfMonth());
        setMes(now.getMonth().getValue());
        setAno(now.getYear());
    }

    public CustomDate(long timeUnix) {
        Instant time = Instant.ofEpochMilli(timeUnix);
        LocalDateTime date = LocalDateTime.ofInstant(time, ZoneId.systemDefault());

        this.setDia(date.getDayOfMonth());
        this.setMes(date.getMonth().getValue());
        this.setAno(date.getYear());
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {

      if (dia > 0 && dia <= 31)
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        if (mes > 0 && mes <= this.meses.length)
           this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
      if (ano > 0)
        this.ano = ano;
    }



    void displayDate() {
        String mes = this.mes == 0 ? this.meses[0] : this.meses[this.mes - 1];

        System.out.printf("%d de %s de %d\n", this.dia, mes, this.ano);
    }

    public static void main(String[] args) {

        CustomDate d1 = new CustomDate(893482340000L);
        CustomDate d2 = new CustomDate();

        d1.displayDate();
        d2.displayDate();

    }



}

