package com.system.bibliotec;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
	
	public static void main(String[] args) {
	
		String s = "joao pedro";
		
		System.out.println(s.length());
		
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//	System.out.println(encoder.encode("@pedroleite"));
		System.out.println();
		System.out.println();
		
		System.out.println(encoder.matches("@joao", "$2a$10$2DRDf6cgsKV43M.b4oDKyuY6FknXu1doaHwyXNJIsvJpQsA6D5qQ."));
		
		
	}
}
