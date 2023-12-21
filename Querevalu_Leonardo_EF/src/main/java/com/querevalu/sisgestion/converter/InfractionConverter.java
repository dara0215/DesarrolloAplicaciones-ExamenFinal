package com.querevalu.sisgestion.converter;

import org.springframework.stereotype.Component;

import com.querevalu.sisgestion.dto.InfractionDto;
import com.querevalu.sisgestion.entity.Infraction;

@Component
public class InfractionConverter extends AbstractConverter<Infraction,InfractionDto>{
	@Override
	public InfractionDto fromEntity(Infraction entities) {
		if(entities == null) return null;
		return InfractionDto.builder()
				.id(entities.getId())
				.dni(entities.getDni())
				.plate(entities.getPlate())
				.date(entities.getDate())
				.infraction(entities.getInfraction())
				.description(entities.getDescription())
				.build();
	}
	
	@Override
	public Infraction fromDTO(InfractionDto dto) {
		if(dto == null) return null;
		return Infraction.builder()
				.id(dto.getId())
				.dni(dto.getDni())
				.plate(dto.getPlate())
				.date(dto.getDate())
				.infraction(dto.getInfraction())
				.description(dto.getDescription())
				.build();
	}
}
