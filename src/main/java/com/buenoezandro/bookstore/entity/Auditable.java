package com.buenoezandro.bookstore.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public abstract class Auditable {
	
	@CreatedDate
	@Column(nullable = false)
	protected LocalDateTime createdDate;
	
	@LastModifiedDate
	@Column
	protected LocalDateTime lastModifiedDate;
	
	@PrePersist
	private void createdDate() {
		this.createdDate = LocalDateTime.now();
	}

}
