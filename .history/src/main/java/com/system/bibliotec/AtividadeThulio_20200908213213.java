package com.system.bibliotec;

import java.util.Scanner;

public class AtividadeThulio {
  public static void main(String[] args) {
      
    Scanner myObj = new Scanner(System.in);

    System.out.println("###########################################################");
    System.out.println("###########################################################");
    System.out.println("###########################################################");
    System.out.println("ATIVIDADE : Elaboração de Algoritmo - MAIOR DE TODOS");
    System.out.println("###########################################################");
    System.out.println("###########################################################");
    System.out.println("###########################################################");

    System.out.println();
    System.out.println();
    System.out.println();

    System.out.println("INFORME O PRIMEIRO NUMERO ");

    int numero1 = myObj.nextInt();

    System.out.println();
    System.out.println();
    System.out.println();

    System.out.println("INFORME O SEGUNDO NUMERO ");

    int numero2 = myObj.nextInt();

    System.out.println();
    System.out.println();

    System.out.println("INFORME O TERCEIRO NUMERO ");

    int numero3 = myObj.nextInt();

    System.out.println();
    System.out.println();


    boolean primeiroMaior = (numero1 > numero2 && numero1 > numero3)? true: false;
    boolean segundoMaior = (numero1 > numero2)? true: false;
    boolean terceiroMaior = (numero2 > numero1)? true: false;
    
    if(iguais){
      System.out.println("PRIMEIRO NUMERO É IGUAL AO SEGUNDO");
    }else if(n1IsMaior && !n2IsMaior){
     
      System.out.println("PRIMEIRO NUMERO É MAIOR DO QUE O SEGUNDO ");

    }else if(n2IsMaior && !n1IsMaior){
      System.out.println("SEGUNDO NUMERO É MAIOR DO QUE O PRIMEIRO ");
    }



    
    System.out.println("###########################################################");
    System.out.println("###########################################################");
    System.out.println("###########################################################");
    System.out.println("------------------------------------------------------------");
    System.out.println("###########################################################");
    System.out.println("###########################################################");
    System.out.println("###########################################################");
    
    
  }
}

