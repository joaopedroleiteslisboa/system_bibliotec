package com.system.bibliotec;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriadorSenha {

    public static void main(String[] args) {
        // TODO Auto-generated method stub


        System.out.println("gerador senha");

        String senha = "@admin";


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println(encoder.encode(senha));
        System.out.println();
        System.out.println();


        //test
        System.out.println(encoder.matches("@admin", "$2a$10$eEPHedaBm5onHbuHxQu3COtr8t7z4KqTIfLbNCaNhIShhJNuvvRJO"));


    }

}
