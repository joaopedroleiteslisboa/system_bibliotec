package com.system.bibliotec.service.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.security.auth.login.AccountException;

import com.system.bibliotec.exception.PermissoesNaoInformadaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.system.bibliotec.exception.CargoInexistenteException;
import com.system.bibliotec.model.Endereco;
import com.system.bibliotec.model.Funcionario;
import com.system.bibliotec.model.Permissao;
import com.system.bibliotec.model.Pessoa;
import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.model.embeddeds.Contato;
import com.system.bibliotec.model.enums.Genero;
import com.system.bibliotec.model.enums.StatusPessoa;
import com.system.bibliotec.model.enums.TipoPessoa;
import com.system.bibliotec.repository.CargoRepository;
import com.system.bibliotec.repository.PermissoesRepositorio;
import com.system.bibliotec.repository.TipoUsuarioVORepository;
import com.system.bibliotec.service.dto.ManagedUserDTO;
import com.system.bibliotec.service.dto.UserAnonimoDTO;

import com.system.bibliotec.service.ultis.RandomUtils;
import com.system.bibliotec.service.vm.UserAnonimoVM;
import com.system.bibliotec.service.vm.UserSystemVM;


@Component
public class MapeadorEcriadorUsuario {

    private static final String DEFAULT_TIPO_USUARIO_SISTEMA = "FUNCIONARIO-GERAL";

    private static final String DEFAULT_TIPO_USUARIO_ANONIMO = "ANONIMO";

    private static final Set<String> DEFAULT_PERMISSOES_USUARIO_SISTEMA_ANONIMO = Stream
            .of("ROLE_CADASTRAR_LIVRO", "ROLE_PESQUISAR_LIVRO", "ROLE_CADASTRAR_PESSOA", "ROLE_PESQUISAR_PESSOA",
                    "ROLE_PESQUISAR_LOCACAO", "ROLE_PESQUISAR_RESERVA", "ROLE_PESQUISAR_CLIENT_APP", "ROLE_USER_SYSTEM")
            .collect(Collectors.toSet());

    private static final Set<String> DEFAULT_PERMISSOES_USUARIO_ANONIMO = Stream
            .of("ROLE_PESQUISAR_LIVRO", "ROLE_PESQUISAR_LOCACAO", "ROLE_PESQUISAR_RESERVA", "ROLE_CADASTRAR_LOCACAO",
                    "ROLE_CADASTRAR_RESERVA", "ROLE_USER_ANONIMO")
            .collect(Collectors.toSet());
    private static final boolean DEFAULT_ATIVO = true;

    private static final boolean DEFAULT_CRIACAO_USUARIO_INATIVO = false;

    @Autowired
    private PermissoesRepositorio permissaoRepositorio;

    @Autowired
    private TipoUsuarioVORepository tipoUsuarioVORepository;

    @Autowired
    private CargoRepository cargoRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public UserSystemVM userToUserSystemVM(Usuario user) {
        return new UserSystemVM(user);
    }

    public UserAnonimoVM userToUserAnonimoVM(Usuario user) {
        return new UserAnonimoVM(user);
    }

    public List<UserSystemVM> usersToUserSystemVMs(List<Usuario> users) {
        return users.stream().filter(Objects::nonNull).map(this::userToUserSystemVM).collect(Collectors.toList());
    }

    public List<UserAnonimoVM> usersToUserAnonimosVMs(List<Usuario> users) {
        return users.stream().filter(Objects::nonNull).map(this::userToUserAnonimoVM).collect(Collectors.toList());
    }

    public Usuario criadorUsuarioAnonimoPorDTO(ManagedUserDTO userDTO) {
        if (userDTO == null) {
            return null;
        } else {


            Usuario user = new Usuario();

            Endereco end = new Endereco();

            Contato c = new Contato();

            user.setUserName(userDTO.getUserName());
            user.setImageUrl(userDTO.getImageUrl());
            user.setEmail(userDTO.getEmail());
            user.setAtivo(false);
            user.setLangKey(userDTO.getLangKey());

//            String senhaCriptografada = passwordEncoder.encode(userDTO.getPassword());
            String senhaCriptografada = userDTO.getPassword();
            user.setSenha(senhaCriptografada);

            Set<Permissao> permissoes = DEFAULT_PERMISSOES_USUARIO_ANONIMO.stream()
                    .map(permissaoRepositorio::findOneByDescricaoIgnoreCase).filter(Optional::isPresent)
                    .map(Optional::get).collect(Collectors.toSet());
            user.setPermissoes(permissoes);

            tipoUsuarioVORepository.findOneByTipoIgnoreCase(DEFAULT_TIPO_USUARIO_ANONIMO).ifPresent(user::setTipo);

            user.setNome(userDTO.getNome());
            user.setSobreNome(userDTO.getSobreNome());
            user.setDataNascimento((userDTO.getDataNascimento() != null && !userDTO.getDataNascimento().isEmpty())
                    ? LocalDate.parse(userDTO.getDataNascimento(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    : null);

            user.setAtivo(false);
            user.setChaveAtivacao(RandomUtils.generateActivationKey());
            user.setCpf(userDTO.getCpf());
            user.setCnpj(userDTO.getCnpj());
            user.setStatusPessoa(StatusPessoa.ADIMPLENTE);

            if (userDTO.getTipoPessoa() != null && !userDTO.getTipoPessoa().isEmpty()) {
                user.setTipoPessoa((TipoPessoa.fromValueString(userDTO.getTipoPessoa()) != null)
                        ? TipoPessoa.fromValueString(userDTO.getTipoPessoa())
                        : TipoPessoa.FISICA);
            }

            if (userDTO.getGenero() != null && !userDTO.getGenero().isEmpty()) {
                user.setGenero((Genero.fromValueString(userDTO.getGenero()) != null)
                        ? Genero.fromValueString(userDTO.getGenero())
                        : Genero.NAO_INFORMADO);
            }

            end.setLogradouro(userDTO.getRua());
            end.setBairro(userDTO.getBairro());
            end.setCep(userDTO.getCep());
            end.setCidade(userDTO.getCidade());
            end.setComplemento(userDTO.getComplemento());
            end.setIbge(userDTO.getIbge());
            end.setNumero(userDTO.getNumero());
            end.setUf(userDTO.getUf());

            user.setEndereco(end);

            c.setCelular(userDTO.getCelular());
            c.setEmail_1(userDTO.getEmail());
            c.setEmail_2(userDTO.getEmail2());
            c.setTelefoneResidencial(userDTO.getTelefoneResidencial());

            user.setContato(c);

            return user;

        }

    }

    /**
     * @param userDTO
     * @return
     */
    public Usuario criadorUsuarioSistemaPorDTO(Object userDTO) {

        // Todo: Mudar implementação para vincular usuario como funcionario... em vez de
        // cadastro de usuario.
        return null;

    }

}
