package com.system.bibliotec.service.validation;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.bibliotec.exception.EmailInvalidoException;
import com.system.bibliotec.exception.UsuarioBloqueadoException;
import com.system.bibliotec.exception.UsuarioNaoAtivadoException;
import com.system.bibliotec.exception.UsuarioNaoEncontrado;
import com.system.bibliotec.model.Usuario;

@Component
public class ValidaUsuarioImpl implements IvalidaUsuarioTriagemInicial {

    @Autowired
    private IValidaPessoa validadorCliente;

    @Override
    public void validadorUsuarioCliente(Usuario u) {
        // TODO Auto-generated method stub

        emailValido(u.getEmail());
        validadorCliente.validacaoFisicaEJuridica(u);

    }

    private void emailValido(String email) {
        // TODO Auto-generated method stub

        if (!new EmailValidator().isValid(email, null)) {
            throw new EmailInvalidoException(
                    "Email invalido. Operação não realizada por conter inconsistencia de dados no cadastro");
        }
    }

    private void validaDebitosECondicaoDoUsuario(Usuario u) {
        if (u.getBloqueado() || u.getMotivoBloqueio() != null && !u.getMotivoBloqueio().isEmpty()) {

            throw new UsuarioBloqueadoException(
                    u.saudacoes() + "Sua Conta estar Bloqueada devido " + u.getMotivoBloqueio().toString());
        }
        if (!u.isAtivo()) {

            throw new UsuarioNaoAtivadoException(
                    u.saudacoes() + "Sua conta não estar ativada. Será necessaria ativa-la pela recuperação de Senha.");
        }
    }

}
