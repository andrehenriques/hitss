package br.com.globalhitss.claro.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.globalhitss.claro.domain.data.repository.CellPhoneRepository;
import br.com.globalhitss.claro.domain.model.CellPhone;

@Service
public class CellPhoneService {
	
	@Autowired
	private CellPhoneRepository cellPhoneRepository;
	
	public CellPhone create(CellPhone cellPhone) {
		UUID uuid = UUID.nameUUIDFromBytes(cellPhone.toString().getBytes());
		cellPhone.setCode(uuid.toString());
		cellPhoneRepository.save(cellPhone);
		return cellPhone;
	}

	public List<CellPhone> getAll() {
		return cellPhoneRepository.findAll();
	}
	
	public CellPhone getByCode(String code) {
		return cellPhoneRepository.findById(code).get();
	}
	
}
