package com.system.bibliotec.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public  class AbstractAuditingEntity implements Serializable, IGetId ,IGetNome, IGetGenero, IGetDataPrevisaoTerminoOperacao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7879582121840012026L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected Long id;

	@CreatedBy
	@Column(name = "created_by", nullable = false, length = 70, updatable = false)
	@JsonIgnore
	private String createdBy;

	@CreatedDate
	@Column(name = "created_date", updatable = false)
	@JsonIgnore
	private Instant createdDate = Instant.now();

	@LastModifiedBy
	@Column(name = "last_modified_by", length = 70)
	@JsonIgnore
	private String lastModifiedBy;

	@LastModifiedDate
	@Column(name = "last_modified_date")
	@JsonIgnore
	private Instant lastModifiedDate = Instant.now();


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AbstractAuditingEntity that = (AbstractAuditingEntity) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
