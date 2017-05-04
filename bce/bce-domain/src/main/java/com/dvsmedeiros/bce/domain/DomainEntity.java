package com.dvsmedeiros.bce.domain;

import java.util.Calendar;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@MappedSuperclass
@JsonInclude(Include.NON_NULL)
public class DomainEntity extends AbstractDomainEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Calendar insertionDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Calendar getInsertionDate() {
		return insertionDate;
	}

	public void setInsertionDate(Calendar insertionDate) {
		this.insertionDate = insertionDate;
	}

	@PrePersist
	public void prePersist() {
		this.insertionDate = Calendar.getInstance();
	}

}
