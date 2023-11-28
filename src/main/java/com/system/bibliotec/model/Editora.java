package com.system.bibliotec.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_editoras")
public class Editora extends AbstractAuditingEntity {


    @NotBlank(message = "O nome é Obrigatorio")
    @Size(min = 3, max = 60)
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "Uma descricao seria ideal")
    @Size(max = 5000)
    @Column(name = "descricao")
    private String descricao;

    @JsonBackReference
    @OneToMany(mappedBy = "editora", orphanRemoval = false, fetch = FetchType.EAGER)
    private List<Livro> livros;
}
