package com.system.bibliotec.service.dto;

import static com.system.bibliotec.config.ConstantsUtils.DEFAULT_LANGUAGE;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserAnonimoDTO extends PessoaDTO {


    public String userName;

    @NotNull(message = "O seu email é Obrigatorio")
    public String email;

    public String email2;

    public String imageUrl;

    public String langKey = DEFAULT_LANGUAGE;

    @NotNull(message = "É necessario informar sua modalidade se é Jurdica ou Fisica")
    public String tipoPessoa;


}
