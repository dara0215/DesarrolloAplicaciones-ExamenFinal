package com.querevalu.sisgestion.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.querevalu.sisgestion.entity.Infraction;

@Repository
public interface InfractionRepository extends JpaRepository<Infraction, Integer>{
	List<Infraction> findByDniContaining(String dni, Pageable page);
	Infraction findByDni(String dni);
}
