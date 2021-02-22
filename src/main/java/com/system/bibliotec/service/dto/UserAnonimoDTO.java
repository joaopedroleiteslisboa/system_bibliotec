package com.system.bibliotec.service.dto;

import static com.system.bibliotec.config.ConstantsUtils.DEFAULT_LANGUAGE;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.system.bibliotec.model.Permissao;
import com.system.bibliotec.model.Usuario;

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
