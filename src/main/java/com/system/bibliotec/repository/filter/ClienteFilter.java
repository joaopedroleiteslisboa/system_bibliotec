package com.system.bibliotec.repository.filter;

import com.system.bibliotec.model.enums.StatusCliente;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
public class ClienteFilter {

	private String nome;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimentoDe;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimentoAte;

	@Enumerated(EnumType.STRING)
	private StatusCliente statusCliente;

	

	
}
