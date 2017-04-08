package com.dvsmedeiros.bce.domain;

import java.util.Calendar;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

@MappedSuperclass
public class DomainEntity extends AbstractDomainEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Calendar insertionDate;
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@PrePersist
	public void prePersist(){
		this.insertionDate = Calendar.getInstance();
	}

}
