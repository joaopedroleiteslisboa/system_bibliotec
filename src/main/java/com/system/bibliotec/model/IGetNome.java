package com.system.bibliotec.model;

import org.apache.commons.lang3.StringUtils;

public interface IGetNome {

    default String getNome() {
        return StringUtils.EMPTY;
    }
}
