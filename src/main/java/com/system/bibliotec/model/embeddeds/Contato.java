package com.system.bibliotec.model.embeddeds;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.system.bibliotec.config.ConstantsUtils;

@Getter
@Setter
@Embeddable
public class Contato {

    @NotNull(message = "O contato telefônico é obrigatório")
    @Size(max = 50)
    @Column(name = "celular")
    private String celular;

    @Size(max = 30)
    @Column(name = "telefoneResidencial")
    private String telefoneResidencial;

    @NotNull(message = "O Email é obrigatório")
    @Pattern(regexp = ConstantsUtils.REGEX_EMAIL)
    @Column(name = "email_1")
    private String email_1;

    @Pattern(regexp = ConstantsUtils.REGEX_EMAIL)
    @Column(name = "email_2")
    private String email_2;
}
