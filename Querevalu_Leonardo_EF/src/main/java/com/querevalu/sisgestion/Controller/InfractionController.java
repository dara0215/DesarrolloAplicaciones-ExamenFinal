package com.querevalu.sisgestion.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.querevalu.sisgestion.converter.InfractionConverter;
import com.querevalu.sisgestion.dto.InfractionDto;
import com.querevalu.sisgestion.entity.Infraction;
import com.querevalu.sisgestion.service.InfractionService;
import com.querevalu.sisgestion.util.WrapperResponse;

@RestController
@RequestMapping("/infractions")
public class InfractionController {
	@Autowired
	private InfractionService service;
	
	@Autowired
	private InfractionConverter converter;
	
	@GetMapping()
	public ResponseEntity<List<InfractionDto>> findAll(
			@RequestParam(value = "name", required = false, defaultValue = "")String dni,
			@RequestParam(value = "offset", required = false, defaultValue = "0")int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5")int pageSize){
		
			Pageable page = PageRequest.of(pageNumber,pageSize);
			List<Infraction> infractions;
			infractions = (dni == null) ? service.findAll(page) : service.findByDni(dni, page);
			List<InfractionDto> articlesDto = converter.fromEntity(infractions);
			return new WrapperResponse(true,"success",articlesDto).createResponse(HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity <WrapperResponse<InfractionDto>> findById(@PathVariable("id") int id){
		Infraction infraction = service.findById(id);
		InfractionDto infractionDto = converter.fromEntity(infraction);
		return new WrapperResponse<InfractionDto>(true,"success",infractionDto).createResponse(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity <InfractionDto> create(@RequestBody InfractionDto infractionDto){
		Infraction record = service.save(converter.fromDTO(infractionDto));
		InfractionDto recordDto = converter.fromEntity(record);
		return new WrapperResponse(true,"success",recordDto).createResponse(HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity <InfractionDto> update(@PathVariable("id") int id,
			@RequestBody InfractionDto infractionDto){
		Infraction record = service.update(converter.fromDTO(infractionDto));
		InfractionDto recordDto = converter.fromEntity(record);
		return new WrapperResponse(true,"success",recordDto).createResponse(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity <InfractionDto> delete(@PathVariable("id") int id){
		service.delete(id);
		return new WrapperResponse(true,"success",null).createResponse(HttpStatus.OK);
	}
}
