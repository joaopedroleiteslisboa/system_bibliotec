package com.system.bibliotec.model.enums;

public enum Genero {

	MASCULINO("MASCULINO"), FEMININO("FEMININO"), NAO_INFORMADO("NAO INFORMADO");

	private String genero;

	Genero(String genero) {

		this.genero = genero;
	}

	public String getGenero() {
		return genero;
	}

	

	public static Genero fromValueString(String tipoCliente) {
		  
		  if (tipoCliente == null) { throw new IllegalArgumentException(); } 
		  
		  	for(Genero tipoClienteSalved : Genero.values()) {
		  		if (tipoCliente.equalsIgnoreCase(tipoClienteSalved.getGenero()))
		  { 
			  return tipoClienteSalved;
			  
			  } } throw new IllegalArgumentException();
		  
		  }

}
