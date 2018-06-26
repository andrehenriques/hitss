package br.com.globalhitss.claro.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.globalhitss.claro.domain.service.CellPhoneService;
import br.com.globalhitss.claro.web.dto.CreateCellPhoneRequest;

@RestController
@RequestMapping(path="/mobile")
public class CellPhoneController {
	
	@Autowired
	private CellPhoneService cellPhoneService;
	
	@PostMapping
	public ResponseEntity<?> create(@Valid CreateCellPhoneRequest createCellPhoneRequest) {
		return null;
	}
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		return null;
	}
	
	@GetMapping("/{code}")
	public ResponseEntity<?> getByCode(String code) {
		return null;
	}
}
