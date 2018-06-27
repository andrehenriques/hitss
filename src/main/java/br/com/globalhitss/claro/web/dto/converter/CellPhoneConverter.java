package br.com.globalhitss.claro.web.dto.converter;

import org.springframework.stereotype.Component;

import br.com.globalhitss.claro.domain.model.CellPhone;
import br.com.globalhitss.claro.web.dto.CellPhoneResumeDto;
import br.com.globalhitss.claro.web.dto.CreateCellPhoneRequest;

@Component
public class CellPhoneConverter {

	public CellPhone  cellPhoneRequestToCellPhoneEntity(final CreateCellPhoneRequest cellPhoneRequest) {
		CellPhone cellPhone = new CellPhone();
		cellPhone.setModel(cellPhoneRequest.getModel());
		cellPhone.setPhoto(cellPhoneRequest.getPhoto());
		cellPhone.setBrand(cellPhoneRequest.getBrand());
		cellPhone.setDate(cellPhoneRequest.getDate());
		cellPhone.setPrice(cellPhoneRequest.getPrice());
		return cellPhone;
	}
	
	public CellPhoneResumeDto toTellPhoneResume(final CellPhone cellPhone) {
		CellPhoneResumeDto cellPhoneResumeDto = new CellPhoneResumeDto();
		cellPhoneResumeDto.setModel(cellPhone.getModel());
		cellPhoneResumeDto.setPhoto(cellPhone.getPhoto());
		cellPhoneResumeDto.setBrand(cellPhone.getBrand());
		cellPhoneResumeDto.setDate(cellPhone.getDate());
		cellPhoneResumeDto.setPrice(cellPhone.getPrice());
		cellPhoneResumeDto.setCode(cellPhone.getCode());
		return cellPhoneResumeDto;
	}
	
}
