package com.system.bibliotec.service.operacoes;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.service.dto.ManagedUserDTO;
import com.system.bibliotec.service.dto.UserAnonimoDTO;
import com.system.bibliotec.service.operacoes.auditor.IAuditorTokenDeUsuarioDoContexto;

@Service
public interface IOperacaoUsuario extends IAuditorTokenDeUsuarioDoContexto {

    public Usuario activateRegistration(String key);

    public Optional<Usuario> completePasswordReset(String newPassword, String confirmPassword, String key);

    public Optional<Usuario> requestPasswordReset(String mail);

    public Usuario registrarUsuarioAnonimo(ManagedUserDTO userDTO);

    // Todo: mudar implementação para vincular a um usuario existente no lugar de
    // cadastrar do zero
    public Usuario registrarUsuarioSistema(Object userDTO);

    // Todo: mudar implementação para vincular a um usuario existente no lugar de
    // cadastrar do zero
    public Usuario criarUsuarioSistema(Object userDTO);

    public Object obterUsuarioCorrente();

    public void updateUserAnonimo(UserAnonimoDTO usuarioDTO);

    public void changePassword(String currentClearTextPassword, String newPassword);

    public Usuario findOneByUsuarioContexto();

    public Usuario findById(Long id);

    public Usuario findOneByEmailOrUsername(String emailOrUsername);

    public void removeNotActivatedUsers();

    default void bloqueadorDeAcesso(boolean bool, String motivo, String username) {
        return;
    }
}
