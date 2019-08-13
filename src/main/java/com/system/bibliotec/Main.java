package com.system.bibliotec;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.env.RandomValuePropertySource;

public class Main {
	

	
	public static void main(String[] args) {
		
			
		System.out.println(generateActivationKey());
			
		
		}
		
	 public static String generateActivationKey() {
		 final int DEF_COUNT = 10; 
		   return RandomStringUtils.randomNumeric(DEF_COUNT);
	 }
	     
		 
	
		 
}
