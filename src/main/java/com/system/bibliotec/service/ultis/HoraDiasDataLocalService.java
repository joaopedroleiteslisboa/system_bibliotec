package com.system.bibliotec.service.ultis;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class HoraDiasDataLocalService {

	private static long DEFAULT_QUANTIDADE_DIAS_MAXIMO_PARA_ANALISE_ATIVACAO_CONTA_USUARIO = 2;

	public static LocalTime horaLocal() {
		
			return LocalTime.now();

	}

	public static LocalDate dataLocal() {
		
		return LocalDate.now();

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

	public static LocalDate dataRenovacaoLocacao(LocalDate dataTerminoLocacao) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate localDateAnalyzed = null;

		LocalDate localDateCurrent = LocalDate.now();

		if (localDateCurrent.isBefore(dataTerminoLocacao) || dataTerminoLocacao.isEqual(localDateCurrent)) {

			LocalDate localDate_Plus10_Plus_dataTerminoLocacao = dataTerminoLocacao.plusDays(10);

			localDateAnalyzed = oBterProximoDiaUtil(localDate_Plus10_Plus_dataTerminoLocacao);

		}

		LocalDate dataValida = LocalDate.parse(localDateAnalyzed.toString(), dateTimeFormatter);
		return dataValida;

	}

	public static boolean isDataRenovacaoLocacaoValida(LocalDate dataTerminoLocacao) {

		boolean isDataValida = false;

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate dataTerminoLocacaoFormatada = LocalDate.parse(dataTerminoLocacao.format(dateTimeFormatter));

		LocalDate localDateCurrent = LocalDate.parse(LocalDate.now().format(dateTimeFormatter));

		if (localDateCurrent.isBefore(dataTerminoLocacaoFormatada)
				|| dataTerminoLocacaoFormatada.isEqual(localDateCurrent)) {

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

	public static boolean processoAtivacaoUsuarioEAtivo(Instant inst) {

		LocalDate dataProcessoAtivacaoUsuario = inst.atZone(ZoneId.of("America/Sao_Paulo")).toLocalDate();

		if (dataProcessoAtivacaoUsuario.isBefore(LocalDate.now())) {
			long diasPassados = ChronoUnit.DAYS.between(dataProcessoAtivacaoUsuario, LocalDate.now());
			if (diasPassados > DEFAULT_QUANTIDADE_DIAS_MAXIMO_PARA_ANALISE_ATIVACAO_CONTA_USUARIO) {
				return true;
			}
		}
		return false;

	}

}
