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
    boolean segundoMaior = (numero2 > numero1 && numero2 > numero3)? true: false;
    boolean terceiroMaior = (numero3 > numero1 && numero3 > numero2)? true: false;
    
    if(primeiroMaior){
      System.out.println("PRIMEIRO NUMERO É O MAIOR");
    }else if(segundoMaior){
     
      System.out.println("O SEGUNDO NUMERO É O MAIOR");

    }else if(terceiroMaior){
      System.out.println("O TERCEIRO NUMERO É O MAIOR");
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

