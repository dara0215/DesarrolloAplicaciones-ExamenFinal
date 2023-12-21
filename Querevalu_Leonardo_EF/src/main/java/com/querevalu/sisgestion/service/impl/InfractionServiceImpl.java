package com.querevalu.sisgestion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querevalu.sisgestion.entity.Infraction;
import com.querevalu.sisgestion.exception.GeneralServiceException;
import com.querevalu.sisgestion.exception.NoDataFoundException;
import com.querevalu.sisgestion.exception.ValidateServiceException;
import com.querevalu.sisgestion.repository.InfractionRepository;
import com.querevalu.sisgestion.service.InfractionService;
import com.querevalu.sisgestion.validator.InfractionValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InfractionServiceImpl implements InfractionService{

	@Autowired
	private InfractionRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Infraction> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		} catch (NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Infraction> findByDni(String dni, Pageable page) {
		try {
			return repository.findByDniContaining(dni,page);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Infraction findById(int id) {
		try {
			Infraction record = repository.findById(id).orElseThrow(
					()->new NoDataFoundException("There is no record with this ID"));
			return record;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Infraction save(Infraction infraction) {
		try {
			InfractionValidator.save(infraction);
			if(repository.findByDni(infraction.getDni()) != null) {
				throw new ValidateServiceException("There is already an infraction with the DNI: " + infraction.getDni());
			}
			infraction.setState(true);
			Infraction record = repository.save(infraction);
			return record;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Infraction update(Infraction infraction) {
		try {
			InfractionValidator.save(infraction);
			Infraction record = repository.findById(infraction.getId()).orElseThrow(
					()->new NoDataFoundException("There is no record with this ID"));
			Infraction duplicate = repository.findByDni(infraction.getDni());
			if(duplicate != null && duplicate.getId() != infraction.getId()) {
				throw new ValidateServiceException("There is already an infraction with the DNI: " + infraction.getDni());
			}
			record.setDni(infraction.getDni());
			record.setPlate(infraction.getPlate());
			record.setDate(infraction.getDate());
			record.setInfraction(infraction.getInfraction());
			record.setDescription(infraction.getDescription());
			repository.save(record);
			return record;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			Infraction record = repository.findById(id).orElseThrow(
					()->new NoDataFoundException("There is no record with this ID"));
			repository.delete(record);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

}
