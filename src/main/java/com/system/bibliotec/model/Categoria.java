package com.system.bibliotec.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categorias")
public class Categoria extends AbstractAuditingEntity {


    @NotBlank(message = "Este campo não pode ficar em branco")
    @Column(name = "nome", unique = true, length = 40)
    private String nome;

    @JsonBackReference
    @ManyToMany(mappedBy = "categorias", fetch = FetchType.EAGER)
    private List<Livro> livros;
}
