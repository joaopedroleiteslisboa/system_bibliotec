package com.system.bibliotec.service.validation;

import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.enums.StatusLivro;

/**
 * Contrato de Validação da Entidade/Modelo Livro {@link Livro}.
 */

public interface IValidaLivro {

    void validaLivro(Livro livro);

    void validaLivro(Long id);

    void validaLivro(Integer estoque);


}

