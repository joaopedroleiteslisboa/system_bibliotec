package com.system.bibliotec.service.ultis;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class HoraDiasDataLocalService {

	public static LocalTime horaLocal() {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("kk:mm:ss");
		String localTime = LocalTime.now().toString();
		LocalTime horaFormatada = LocalTime.parse(localTime, dateTimeFormatter);

		return horaFormatada;

	}

	public static LocalDate dataLocal() {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		CharSequence localDate = LocalDate.now().toString();
		LocalDate dataFormatada = LocalDate.parse(localDate, dateTimeFormatter);

		return dataFormatada;

	}

	public static LocalDate dataReservaLimite() {

		LocalDate localDatePlus3 = LocalDate.now().plusDays(3);

		LocalDate localDateComDiaUtil = oBterProximoDiaUtil(localDatePlus3);

		return localDateComDiaUtil;

	}

	public static LocalDate dataLocacaoDevolucao() {

		LocalDate localDatePlus10 = LocalDate.now().plusDays(10);

		LocalDate localDateAnalyzed = oBterProximoDiaUtil(localDatePlus10);

		return localDateAnalyzed;

	}

	public static LocalDate dataRenovacaoLocacao(LocalDate dataTerminoEmprestimo) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate localDateAnalyzed = null;

		LocalDate localDateCurrent = LocalDate.now();

		if (localDateCurrent.isBefore(dataTerminoEmprestimo) || dataTerminoEmprestimo.isEqual(localDateCurrent)) {

			LocalDate localDate_Plus10_Plus_dataTerminoEmprestimo = dataTerminoEmprestimo.plusDays(10);

			localDateAnalyzed = oBterProximoDiaUtil(localDate_Plus10_Plus_dataTerminoEmprestimo);

		}

		LocalDate dataValida = LocalDate.parse(localDateAnalyzed.toString(), dateTimeFormatter);
		return dataValida;

	}

	public static boolean isDataRenovacaoLocacaoValida(LocalDate dataTerminoEmprestimo) {

		boolean isDataValida = false;

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate dataTerminoEmprestimoFormatada = LocalDate.parse(dataTerminoEmprestimo.format(dateTimeFormatter));

		LocalDate localDateCurrent = LocalDate.parse(LocalDate.now().format(dateTimeFormatter));

		if (localDateCurrent.isBefore(dataTerminoEmprestimoFormatada)
				|| dataTerminoEmprestimoFormatada.isEqual(localDateCurrent)) {

			isDataValida = true;

		}

		return isDataValida;

	}

	private static LocalDate oBterProximoDiaUtil(LocalDate dataAgendada) {

		if (dataAgendada.getDayOfWeek() == DayOfWeek.SATURDAY) {

			dataAgendada = dataAgendada.plusDays(2);

		} else if (dataAgendada.getDayOfWeek() == DayOfWeek.SUNDAY) {

			dataAgendada = dataAgendada.plusDays(1);

		}

		return dataAgendada;

	}

}
