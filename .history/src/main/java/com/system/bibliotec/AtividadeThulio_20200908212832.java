package com.system.bibliotec;

import java.util.Scanner;

public class AtividadeThulio {
  public static void main(String[] args) {
      
    Scanner myObj = new Scanner(System.in);

    System.out.println("##################################################################");
    System.out.println("##################################################################");
    System.out.println("##################################################################");
    System.out.println("ATIVIDADE : Elaboração de Algoritmo - POSITIVO, NEGATIVO OU ZERO");
    System.out.println("##################################################################");
    System.out.println("##################################################################");
    System.out.println("##################################################################");

    System.out.println();


    System.out.println("INFORME UM VALOR ");

    int numero = myObj.nextInt();

    System.out.println();
    System.out.println();

    boolean zero = (numero == 0)? true: false;
    boolean maiorQueZero = (numero > 0)? true: false;
    boolean menorQueZero = (numero < 0)? true: false;
    
    if(zero){
      System.out.println("O NUMERO É ZERO");
    }else if(maiorQueZero){
     
      System.out.println("O NUMERO É MAIOR DO QUE ZERO ");

    }else if(menorQueZero){
      System.out.println("O NUMERO É MENOR DO QUE ZERO ");
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

