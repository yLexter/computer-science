package Java.ExerciciosLab;

import java.util.*;

// Exercico 1 e questão 3 da prova
 public class Date {
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
  
    public Date(int dia, int mes, int ano) {
  
      if (dia > 0 && dia <= 31)
        this.dia = dia;
  
      if (mes > 0 && mes <= this.meses.length)
        this.mes = mes;
  
      if (ano > 0)
        this.ano = ano;
  
    }
  
    void displayDate() {
      String mes = this.mes <= 0 ? this.meses[0] : this.meses[this.mes - 1];
  
      System.out.printf("%d de %s de %d", this.dia, mes, this.ano);
    }
  
    public static void test() {
  
        new Date(2,2,2003).displayDate();
        new Date(-2,-2,-2003).displayDate();
          
    }
  
  }

