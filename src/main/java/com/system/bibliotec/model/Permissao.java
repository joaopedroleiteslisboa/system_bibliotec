package com.system.bibliotec.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permissao")
public class Permissao extends AbstractAuditingEntity {


    /**
     *
     */
    private static final long serialVersionUID = -66603401216756291L;
    @Column(name = "descricao")
    private String descricao;


}
