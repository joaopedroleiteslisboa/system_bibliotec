package com.system.bibliotec.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SystemError
 */



@Entity
@Table(name = "systemErrors")
@EntityListeners(AuditingEntityListener.class)
public class SystemError {
   
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@EqualsAndHashCode.Include
    private Long id;

	@Column(name = "done")
	private boolean done;

    @Column(name = "metodoError")
    private String metodoError;

    @Column(name = "usuarioError")
    private String usuarioError;

	@Column(name = "idUser")
	private String idUser;

    @Column(name = "classs")
    private String classs;

    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "operacao")
    private String operacao;

	@Generated("SparkTools")
	private SystemError(Builder builder) {
		this.id = builder.id;
		this.done = builder.done;
		this.metodoError = builder.metodoError;
		this.usuarioError = builder.usuarioError;
		this.idUser = builder.idUser;
		this.classs = builder.classs;
		this.descricao = builder.descricao;
		this.operacao = builder.operacao;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMetodoError() {
		return metodoError;
	}

	public void setMetodoError(String metodoError) {
		this.metodoError = metodoError;
	}

	public String getUsuarioError() {
		return usuarioError;
	}

	public void setUsuarioError(String usuarioError) {
		this.usuarioError = usuarioError;
	}

	public String getClasss() {
		return classs;
	}

	public void setClasss(String classs) {
		this.classs = classs;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	/**
	 * Creates builder to build {@link SystemError}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link SystemError}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Long id;
		private boolean done;
		private String metodoError;
		private String usuarioError;
		private String idUser;
		private String classs;
		private String descricao;
		private String operacao;

		public Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withDone(boolean done) {
			this.done = done;
			return this;
		}

		public Builder withMetodoError(String metodoError) {
			this.metodoError = metodoError;
			return this;
		}

		public Builder withUsuarioError(String usuarioError) {
			this.usuarioError = usuarioError;
			return this;
		}

		public Builder withIdUser(String idUser) {
			this.idUser = idUser;
			return this;
		}

		public Builder withClasss(String classs) {
			this.classs = classs;
			return this;
		}

		public Builder withDescricao(String descricao) {
			this.descricao = descricao;
			return this;
		}

		public Builder withOperacao(String operacao) {
			this.operacao = operacao;
			return this;
		}

		public SystemError build() {
			return new SystemError(this);
		}
	}


	
}