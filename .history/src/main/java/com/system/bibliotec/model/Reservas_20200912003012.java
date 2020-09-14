package com.system.bibliotec.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.system.bibliotec.model.enums.Status;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.annotation.Generated;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservas")
public class Reservas extends AbstractAuditingEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4258702459699708485L;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "kk:mm:ss")
	@DateTimeFormat(pattern = "kk:mm:ss")
	@Column(name = "horaReserva")
	private LocalTime horaReserva;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dataReserva")
	private LocalDate dataReserva;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dataPrevisaoTermino")
	private LocalDate dataPrevisaoTermino;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "kk:mm:ss")
	@DateTimeFormat(pattern = "kk:mm:ss")
	@Column(name = "horaReserva")
	private LocalTime horaRetiradaLivro;


	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dataPrevisaoTermino")
	private LocalDate dataPrevisaoTermino;


	@OneToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	@OneToOne
	@JoinColumn(name = "idLivro")
	private Livro livro;

	@Generated("SparkTools")
	private Reservas(Builder builder) {
		this.id = builder.id;
		this.status = builder.status;
		this.horaReserva = builder.horaReserva;
		this.dataReserva = builder.dataReserva;
		this.dataPrevisaoTermino = builder.dataLimite;
		this.usuario = builder.usuario;
		this.livro = builder.livro;
	}

	

	/**
	 * Creates builder to build {@link Reservas}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link Reservas}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Long id;
		private Status status;
		private LocalTime horaReserva;
		private LocalDate dataReserva;
		private LocalDate dataLimite;
		private Usuario usuario;
		private Livro livro;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withStatus(Status status) {
			this.status = status;
			return this;
		}

		public Builder withHoraReserva(LocalTime horaReserva) {
			this.horaReserva = horaReserva;
			return this;
		}

		public Builder withDataReserva(LocalDate dataReserva) {
			this.dataReserva = dataReserva;
			return this;
		}

		public Builder withDataLimite(LocalDate dataLimite) {
			this.dataLimite = dataLimite;
			return this;
		}

		public Builder withUsuario(Usuario usuario) {
			this.usuario = usuario;
			return this;
		}

		public Builder withLivro(Livro livro) {
			this.livro = livro;
			return this;
		}

		public Reservas build() {
			return new Reservas(this);
		}
	}

	@Override
	public String toString() {
		return "Reserva [status=" + status + ", horaReserva=" + horaReserva + ", dataReserva=" + dataReserva
				+ ", dataLimite=" + dataPrevisaoTermino + ", usuario=" + usuario.getNome() + ", livro=" + livro.getNome() + "Ed " + livro.getEdicao() + "]";
	}
	


}
