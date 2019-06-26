package com.system.bibliotec.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

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
public class ReservaFilter {

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dataReserva;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dataLimite;

	
}
