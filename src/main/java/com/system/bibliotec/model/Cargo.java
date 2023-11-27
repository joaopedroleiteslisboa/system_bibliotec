package com.system.bibliotec.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@Table(name = "cargos")
public class Cargo extends AbstractAuditingEntity {


    @NotBlank(message = "Um codigo do Ministerio do trabalho é necessario")
    @Size(max = 80)
    @Column(name = "codigo")
    private String codigo;

    @NotBlank(message = "Um nome é necessario")
    @Size(max = 80, min = 3)
    @Column(name = "nome")
    private String nome;


}
