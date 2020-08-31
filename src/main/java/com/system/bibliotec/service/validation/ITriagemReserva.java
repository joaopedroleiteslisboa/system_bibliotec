package com.system.bibliotec.service.validation;

import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.Usuario;

public interface ITriagemReserva{


    void triagemReserva(Usuario u,  Livro l); //Todo: Implementar generics para ocultar uso de Entidade...
}
