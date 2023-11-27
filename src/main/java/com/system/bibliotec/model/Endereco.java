package com.system.bibliotec.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;



@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enderecos")
public class Endereco extends AbstractAuditingEntity {

    @Column(name = "cep")
    @Size(min = 8, max = 10)
    private String cep;

    @NotBlank(message = "O campo Logradouro é Obrigatório")
    @Size(min = 4, max = 200)
    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "numero")
    @NotBlank(message = "O Campo numero é obrigatorio")
    private String numero;

    @Column(name = "complemento", length = 100)
    private String complemento;

    @NotBlank(message = "O Campo Bairro é Necessario")
    @Column(name = "bairro")
    @Size(min = 4, max = 60)
    private String bairro;

    @NotBlank(message = "O Campo Cidade é Necessario")
    @Size(min = 3, max = 40)
    @Column(name = "cidade")
    private String cidade;

    @NotBlank(message = "O Campo UF é Necessario. Ex: PB, PE RJ")
    @Size(min = 2, max = 2)
    @Column(name = "uf")
    private String uf;

    @Column(name = "ibge")
    private String ibge;


}
