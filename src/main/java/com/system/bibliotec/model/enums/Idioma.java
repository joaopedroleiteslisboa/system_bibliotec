package com.system.bibliotec.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Idioma {

	ESPANHOL("Espanhol"), INGLES("Ingles"), ARABE("Arabe"), PORTUGUES("Portugues"),
	BENGALI("Bengali"), RUSSO("Russo"), JAPONES("Japones"), PUNJABI("Punjabi");

	private String idioma;

	Idioma(String idioma) {

		this.idioma = idioma;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setidIoma(String idioma) {
		this.idioma = idioma;
	}

	@JsonCreator
	public static Idioma fromValueString(String idioma) {
		if (idioma == null) {
			throw new IllegalArgumentException();
		}
		for (Idioma idiomaSalved : values()) {
			if (idioma.equals(idiomaSalved.getIdioma())) {
				return idiomaSalved;
			}
		}
		throw new IllegalArgumentException();
	}

}
