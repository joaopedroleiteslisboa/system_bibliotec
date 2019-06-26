package com.system.bibliotec.repository.filter;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.system.bibliotec.model.enums.StatusLocacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LocacaoFilter {

	@Enumerated(EnumType.STRING)
	private StatusLocacao statusLocacao;

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
