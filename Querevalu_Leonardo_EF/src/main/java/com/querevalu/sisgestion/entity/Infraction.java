package com.querevalu.sisgestion.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table (name = "infractions")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Infraction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true, nullable = false, length = 8)
	private String dni;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(nullable = false, length = 7)
	private String plate;

	@Column(nullable = false, length = 200)
	private String infraction;
	
	@Column(length = 255)
	private String description;
	
	@Column(nullable = false)
	private Boolean state;

	@Column(name = "create_at", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createAt;
	
	@Column(name = "update_at")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updateAt;
}
