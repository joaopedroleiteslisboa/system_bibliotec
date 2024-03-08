package com.system.bibliotec;

import java.time.Duration;
import java.util.Random;

public class ThreadExample {

    public static void main(String[] args) {
        // Cria 5 threads
        Thread t1 = new Thread(new MyRunnable());
        Thread t2 = new Thread(new MyRunnable());
        Thread t3 = new Thread(new MyRunnable());
        Thread t4 = new Thread(new MyRunnable());
        Thread t5 = new Thread(new MyRunnable());

        // Inicia as threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        // Espera até as threads terminarem
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // Imprime os valores retornados pelas threads
        System.out.println("Valor retornado pela thread 1: " + t1.getName());
        System.out.println("Valor retornado pela thread 2: " + t2.getName());
        System.out.println("Valor retornado pela thread 3: " + t3.getName());
        System.out.println("Valor retornado pela thread 4: " + t4.getName());
        System.out.println("Valor retornado pela thread 5: " + t5.getName());
    }
}

class MyRunnable implements Runnable {
    int min = 1; // Minimum value of range
    int max = 30; // Maximum value of range

    // Print the min and max

    // Generate random int value from min to max
    int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
    // Printing the generated random numbers

    public void run() {
        // Faça alguma coisa aqui
        try {
            Thread.sleep(Duration.ofSeconds(random_int));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Retorne um valor
        System.out.println("Valor retornado pela thread " + Thread.currentThread().getName());
    }
}
