package br.com.globalhitss.claro.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.globalhitss.claro.domain.model.CellPhone;
import br.com.globalhitss.claro.domain.service.CellPhoneService;
import br.com.globalhitss.claro.web.dto.CellPhoneResumeDto;
import br.com.globalhitss.claro.web.dto.CreateCellPhoneRequest;
import br.com.globalhitss.claro.web.dto.converter.CellPhoneConverter;

@RestController
@RequestMapping(path="/mobile", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
public class CellPhoneController {
	
	@Autowired
	private CellPhoneService cellPhoneService;
	
	@Autowired
	private CellPhoneConverter converter;
	
	@RequestMapping(method= {RequestMethod.POST, RequestMethod.OPTIONS})
	public ResponseEntity<?> create(@Valid @RequestBody CreateCellPhoneRequest createCellPhoneRequest) {
		System.out.println("");
		CellPhone cellPhone = converter.cellPhoneRequestToCellPhoneEntity(createCellPhoneRequest);
		cellPhoneService.create(cellPhone);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@RequestMapping(method= {RequestMethod.GET, RequestMethod.OPTIONS})
	public ResponseEntity<?> getAll() {
		List<CellPhone> cellPhones = cellPhoneService.getAll();
		List<CellPhoneResumeDto> resumes = null;
		resumes = cellPhones.stream()
				.map(cellPhone->converter.toTellPhoneResume(cellPhone))
				.collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(resumes);
	}
	
	@RequestMapping(path="/{code}", method= {RequestMethod.GET, RequestMethod.OPTIONS})
	public ResponseEntity<?> getByCode(String code) {
		CellPhone cellPhone = cellPhoneService.getByCode(code);
		return ResponseEntity.status(HttpStatus.OK).body(converter.toTellPhoneResume(cellPhone));
	}
}
