package com.system.bibliotec.model;

import org.apache.commons.lang3.StringUtils;

public interface IGetGenero {

	default <T> T getGenero() {
		return (T) StringUtils.EMPTY;
	}

	default String saudacoes() {

		return StringUtils.EMPTY;
	}

}
