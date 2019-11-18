package com.system.bibliotec.repository.dto.projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ResumoLivro {
	
	private Long id;

	private String nome;

	private String edicao;
		
	private String descricao;

	private String isbn13;

	private Integer numeroPaginas;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataPublicacao;

	private BigDecimal preco;
	
	
	
}
