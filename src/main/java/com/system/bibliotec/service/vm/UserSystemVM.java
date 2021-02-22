package com.system.bibliotec.service.vm;

import static com.system.bibliotec.config.ConstantsUtils.DEFAULT_LANGUAGE;

import java.util.stream.Collectors;

import com.system.bibliotec.model.Permissao;
import com.system.bibliotec.model.Usuario;

public class UserSystemVM extends UserAnonimoVM {


    public static final String MESSAGEM_ERRO_CADASTRO_USUARIO = "DETECTAMOS INCONSISTENCIA DE INFORMAÇÕES EM SEU CADASTRO. COMUNICAR EQUIPE ADMINISTRADORA DO SISTEMA COM URGENCIA";


    public String matricula;

    public String cargo;


    public UserSystemVM(Usuario user) {
        super(user);


        this.matricula = (user.getFuncionario() != null && user.getFuncionario().getMatricula() != null
                && !user.getFuncionario().getMatricula().isEmpty()) ? user.getFuncionario().getMatricula() : MESSAGEM_ERRO_CADASTRO_USUARIO;


        this.cargo = (user.getFuncionario() != null && user.getFuncionario().getCargo() != null &&
                user.getFuncionario().getCargo().getNome() != null
                && !user.getFuncionario().getCargo().getNome().isEmpty())
                ? user.getFuncionario().getCargo().getNome() : MESSAGEM_ERRO_CADASTRO_USUARIO;


    }


}