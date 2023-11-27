package com.system.bibliotec.repository.filter;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.system.bibliotec.model.enums.StatusPessoa;

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

    private StatusPessoa statusCliente;


}
