package com.system.bibliotec.service.validation;

import com.system.bibliotec.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.bibliotec.model.Endereco;
import com.system.bibliotec.model.Pessoa;
import com.system.bibliotec.service.validation.fields.IValidaCpf;
import com.system.bibliotec.service.validation.fields.IValidaEndereco;
import com.system.bibliotec.service.validation.fields.IvalidaCnpj;

@Component
public class ValidaPessoaImpl implements IValidaPessoa {


    @Autowired
    private IValidaCpf servicoValidacaoCpf;

    @Autowired
    private IvalidaCnpj servicoValidacaoCNPJ;

    @Autowired
    private IValidaEndereco servicoValidaEndereco;


    @Override
    public void validacaoFisicaEJuridica(Usuario pessoa) {
        // TODO Auto-generated method stub

        validaEndereco(pessoa.getEndereco());

        if (pessoa.isJuridica()) {
            validaCnpj(pessoa.getCnpj());
        } else {
            validaCpf(pessoa.getCpf());
        }
    }


    public void validaCpf(String cpf) {
        // TODO Auto-generated method stub
        servicoValidacaoCpf.validaCpf(cpf);


    }


    public void validaCnpj(String cnpj) {
        // TODO Auto-generated method stub

        servicoValidacaoCNPJ.validaCnpj(cnpj);

    }

    public void validaEndereco(Endereco endereco) {
        // TODO Auto-generated method stub

        servicoValidaEndereco.validaEndereco(endereco);
    }


}
