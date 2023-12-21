package com.querevalu.sisgestion.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class InfractionDto {
	private int id;
	private String dni;
	private Date date;
	private String plate;
	private String infraction;
	private String description;
}
