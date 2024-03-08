package com.system.bibliotec; //crie seu pacote

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotDynamicReturnThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        // Exemplo de captura de retorno dinamicamente por streams

        ExecutorService executorThreadMain = Executors.newSingleThreadExecutor(r -> {
            final Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName("Thread configurada para MAIN");
            return thread;
        });


        ExecutorService executorServicegroup1 = Executors.newCachedThreadPool(r -> {
            final Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName("Thread configurada para o grupo 1");
            return thread;
        });

        ExecutorService executorServicegroup2 = Executors.newCachedThreadPool(r -> {
            final Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName("Thread configurada para o grupo 2");
            return thread;
        });


//  ###########     GRUPO 1  ###########

        long startProgram = System.currentTimeMillis();

        CompletableFuture<UserMockVONotDynamicReturn> group1future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread 1 started of group 1");
            try {
                Thread.sleep(19000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println();
            System.out.println("Thread 1 finished of group 1");
            return new UserMockVONotDynamicReturn("Thread 1 result of group 1", 1);
        }, executorServicegroup1);

        CompletableFuture<UserMockVONotDynamicReturn> group1future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread 2 started of group 1");
            try {
                Thread.sleep(17000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println();
            System.out.println("Thread 2 finished of group 1");

            return new UserMockVONotDynamicReturn("Thread 2 result of group 1", 2);
        }, executorServicegroup1);

        CompletableFuture<UserMockVONotDynamicReturn> group1future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread 3 started of group 1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println();
            System.out.println("Thread 3 finished of group 1");
            return new UserMockVONotDynamicReturn("Thread 3 result of group 1", 3);
        },executorServicegroup1);

//  ###########     GRUPO 2  ###########

        CompletableFuture<UserMockVONotDynamicReturn> group2future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread 1 started of group 2");
            try {
                Thread.sleep(18000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println();
            System.out.println("Thread 1 finished of group 2");
            return new UserMockVONotDynamicReturn("Thread 1 result of group 2", 4);
        }, executorServicegroup2);

        CompletableFuture<UserMockVONotDynamicReturn> group2future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread 2 started of group 2");
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println();
            System.out.println("Thread 2 finished of group 2");

            return new UserMockVONotDynamicReturn("Thread 2 result of group 2", 5);
        }, executorServicegroup2);

        CompletableFuture<UserMockVONotDynamicReturn> group2future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread 3 started of group 2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println();
            System.out.println("Thread 3 finished of group 2");
            return new UserMockVONotDynamicReturn("Thread 3 result of group 2", 6);
        },executorServicegroup2);

        System.out.println();
        System.out.println();
        System.out.println();

        CompletableFuture.allOf(

                group1future1,
                group1future2,
                group1future3,

                group2future1,
                group2future2,
                group2future3
        ).join();

        metodoStatic(group1future1.get());
        metodoStatic(group1future2.get());
        metodoStatic(group1future3.get());
        metodoStatic(group2future1.get());
        metodoStatic(group2future2.get());
        metodoStatic(group2future3.get());

        System.out.println("waiting time - " + (System.currentTimeMillis() - startProgram) +" ms");
        System.out.println("Finish check mock thread not dynamic");
        System.out.println("Finish program");

    }

    private static void metodoStatic(UserMockVONotDynamicReturn userMockVONotDynamicReturn) {
        System.out.println(userMockVONotDynamicReturn);
    }

}

class UserMockVONotDynamicReturn {

    public String name;

    public int age;


    public UserMockVONotDynamicReturn(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserMockVO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
