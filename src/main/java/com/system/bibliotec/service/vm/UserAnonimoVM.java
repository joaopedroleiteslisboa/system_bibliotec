package com.system.bibliotec.service.vm;

import static com.system.bibliotec.config.ConstantsUtils.DEFAULT_LANGUAGE;

import java.util.List;
import java.util.stream.Collectors;

import com.system.bibliotec.model.Permissao;
import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.service.dto.PessoaDTO;

public class UserAnonimoVM extends PessoaDTO {
    public static final String MESSAGEM_ERRO_CADASTRO_USUARIO = "DETECTAMOS INCONSISTENCIA DE INFORMAÇÕES EM SEU CADASTRO. COMUNICAR EQUIPE ADMINISTRADORA DO SISTEMA COM URGÊNCIA";

    public String username;

    public String email;

    public String imageUrl;

    public boolean ativo = false;

    public String langKey = DEFAULT_LANGUAGE;


    public String tipoUsuario;

    public String statusPessoa;

    public String tipoPessoa;

    public UserAnonimoVM(Usuario user) {


        this.username = user.getUserName();
        this.email = (user.getEmail() != null && !user.getEmail().isEmpty()) ? user.getEmail() : MESSAGEM_ERRO_CADASTRO_USUARIO;
        this.nome = (user.getNome() != null && !user.getNome().isEmpty()) ? user.getNome() : MESSAGEM_ERRO_CADASTRO_USUARIO;
        this.sobreNome = (user.getSobreNome() != null && !user.getSobreNome().isEmpty()) ? user.getSobreNome() : MESSAGEM_ERRO_CADASTRO_USUARIO;
        this.cpf = ((user.getCpf() != null && !user.getCpf().isEmpty()) ? user.getCpf() : null);
        this.cnpj = ((user.getCnpj() != null && !user.getCnpj().isEmpty()) ? user.getCnpj() : null);
        this.ativo = user.isAtivo();
        this.imageUrl = ((user.getImageUrl() != null && !user.getImageUrl().isEmpty()) ? user.getImageUrl() : MESSAGEM_ERRO_CADASTRO_USUARIO);
        this.langKey = user.getLangKey();
        this.dataNascimento = ((user.getDataNascimento() != null) ? user.getDataNascimento().toString() : MESSAGEM_ERRO_CADASTRO_USUARIO);
        this.genero = user.getGenero().getGenero();

        this.statusPessoa = (user.getStatusPessoa() != null)
                ? user.getStatusPessoa().toString()
                : MESSAGEM_ERRO_CADASTRO_USUARIO;

        this.tipoPessoa = (user.getTipoPessoa() != null)
                ? user.getTipoPessoa().toString()
                : MESSAGEM_ERRO_CADASTRO_USUARIO;

        this.tipoUsuario = (user.getTipo() != null && !user.getTipo().getTipo().isEmpty()) ? user.getTipo().getTipo() : MESSAGEM_ERRO_CADASTRO_USUARIO;

        this.ibge = (user.getEndereco() != null
                && !user.getEndereco().getIbge().isEmpty())
                ? user.getEndereco().getIbge()
                : null;

        this.rua = (user.getEndereco() != null
                && !user.getEndereco().getLogradouro().isEmpty())
                ? user.getEndereco().getLogradouro()
                : MESSAGEM_ERRO_CADASTRO_USUARIO;

        this.bairro = (user.getEndereco() != null
                && !user.getEndereco().getBairro().isEmpty()) ? user.getEndereco().getBairro()
                : MESSAGEM_ERRO_CADASTRO_USUARIO;

        this.cep = (user.getEndereco() != null
                && !user.getEndereco().getCep().isEmpty()) ? user.getEndereco().getCep()
                : MESSAGEM_ERRO_CADASTRO_USUARIO;

        this.cidade = (user.getEndereco() != null
                && !user.getEndereco().getCidade().isEmpty()) ? user.getEndereco().getCidade()
                : MESSAGEM_ERRO_CADASTRO_USUARIO;

        this.numero = (user.getEndereco() != null
                && !user.getEndereco().getNumero().isEmpty()) ? user.getEndereco().getNumero()
                : MESSAGEM_ERRO_CADASTRO_USUARIO;

        this.uf = (user.getEndereco() != null
                && !user.getEndereco().getUf().isEmpty()) ? user.getEndereco().getUf() : MESSAGEM_ERRO_CADASTRO_USUARIO;

        this.complemento = (user.getEndereco() != null
                && !user.getEndereco().getComplemento().isEmpty())
                ? user.getEndereco().getComplemento()
                : " ";

        this.telefoneResidencial = (user.getContato() != null
                && !user.getContato().getTelefoneResidencial().isEmpty())
                ? user.getContato().getTelefoneResidencial()
                : " ";

        this.celular = (user.getContato() != null
                && !user.getContato().getCelular().isEmpty()) ? user.getContato().getCelular()
                : MESSAGEM_ERRO_CADASTRO_USUARIO;


    }


}
