package com.system.bibliotec.repository.pessoa;


import com.system.bibliotec.model.Pessoa;

public interface FindOneByCpfQuery {


    public Pessoa findOneByCpf(String cpf);


}
