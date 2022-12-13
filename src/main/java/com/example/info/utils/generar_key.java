package com.example.info.utils;

public class generar_key {
	
	public static int generar_key() { 
		int numero; 
		
		numero=(int) Math.floor((Math.random()*((40-20)+1))+20); 
		
		while (numero<20 || numero >36)
		{ 
			numero=(int) Math.floor((Math.random()*((40-20)+1))+20); 
		}
		return numero; 
	}

}
