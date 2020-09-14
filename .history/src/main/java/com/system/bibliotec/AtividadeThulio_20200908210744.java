package com.system.bibliotec;

import java.util.Scanner;

public class AtividadeThulio {
  public static void main(String[] args) {
      
    Scanner myObj = new Scanner(System.in);

    System.out.println("######################################################");
    System.out.println("######################################################");
    System.out.println("######################################################");
    System.out.println("ATIVIDADE : Elaboração de Algoritmo - MAIOR, MENOR OU IGUAL");
    System.out.println("######################################################");
    System.out.println("######################################################");
    System.out.println("######################################################");

    // String input
    String name = myObj.nextLine();

    // Numerical input
    int age = myObj.nextInt();
    double salary = myObj.nextDouble();

    // Output input by user
    System.out.println("Name: " + name);
    System.out.println("Age: " + age);
    System.out.println("Salary: " + salary);
  }
}
