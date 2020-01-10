package com.system.bibliotec;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
	
	public static void main(String[] args) {
	
		
		
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("mobileADMIN"));
		System.out.println();
		System.out.println();
		
		System.out.println(encoder.matches("mobileADMIN", "$2a$10$uyQJCv.8OMNpBUQB5hXHNuFped8lLqlhsH8uFwTQARDJBDMtlvQ7q"));
		
		
	}
}
