package com.system.bibliotec;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
	
	public static void main(String[] args) {
	
		
		String decoderr = "$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.";
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.matches("admin", decoderr));
	}
}
