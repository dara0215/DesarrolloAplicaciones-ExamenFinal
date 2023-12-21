package com.querevalu.sisgestion.service;

import java.util.List;

import com.querevalu.sisgestion.entity.Infraction;
import org.springframework.data.domain.Pageable;

public interface InfractionService {
	public List<Infraction> findAll(Pageable page);
	public List<Infraction> findByDni(String dni, Pageable page);
	public Infraction findById(int id);
	public Infraction save(Infraction infraction);
	public Infraction update(Infraction infraction);
	public void delete(int id);
}
