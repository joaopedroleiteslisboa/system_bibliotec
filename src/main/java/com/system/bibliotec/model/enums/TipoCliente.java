package com.system.bibliotec.model.enums;

public enum TipoCliente {

	FISICA("Fisica"), JURIDICA("Juridica");

	private String tipoCliente;

	TipoCliente(String tipoCliente) {

		this.tipoCliente = tipoCliente;
	}

	public String gettipoCliente() {
		return tipoCliente;
	}

	

	
	  public static TipoCliente fromValueString(String tipoCliente) {
		  
	  if (tipoCliente == null) { throw new IllegalArgumentException(); } 
	  
	  	for(TipoCliente tipoClienteSalved : TipoCliente.values()) {
	  		if (tipoCliente.equalsIgnoreCase(tipoClienteSalved.gettipoCliente()))
	  { 
		  return tipoClienteSalved;
		  
		  } } throw new IllegalArgumentException();
	  
	  }
	 
}
