package com.system.bibliotec;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
	
	public static void main(String[] args) {
	
		
		
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("mobileADMIN"));
		System.out.println();
		System.out.println();
		
		System.out.println(encoder.matches("webADMIN", "$2a$10$eEPHedaBm5onHbuHxQu3COtr8t7z4KqTIfLbNCaNhIShhJNuvvRJO"));
		
		
	}
}
