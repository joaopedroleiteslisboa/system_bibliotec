package com.system.bibliotec.repository.filter;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.system.bibliotec.model.enums.StatusPessoa;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PessoaFilter {

    private String nome;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimentoDe;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimentoAte;

    @Enumerated(EnumType.STRING)
    private StatusPessoa statusCliente;


}
