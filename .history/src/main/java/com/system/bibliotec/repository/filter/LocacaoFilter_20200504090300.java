package com.system.bibliotec.repository.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.system.bibliotec.model.enums.Status;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LocacaoFilter {

	@Enumerated(EnumType.STRING)
	private Status statusLocacao;

	@JsonFormat(pattern = "hh:mm:ss")
	@DateTimeFormat(pattern = "hh:mm:ss")
	private LocalTime horaLocacao;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataLocacao;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataTerminoLocacao;
	
}
