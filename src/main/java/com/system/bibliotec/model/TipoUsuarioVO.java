package com.system.bibliotec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipoUsuarioVO")
public class TipoUsuarioVO extends AbstractAuditingEntity {


    /**
     *
     */
    private static final long serialVersionUID = -8280908297718251722L;
    @Column(name = "tipo", unique = true)
    private String tipo;
}
