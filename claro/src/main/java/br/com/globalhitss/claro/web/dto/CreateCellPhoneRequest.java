package br.com.globalhitss.claro.web.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

public class CreateCellPhoneRequest {
	
	@Getter @Setter
	private String model;
	
	@Getter @Setter
	private Double price;
	
	@Getter @Setter
	private String brand;
	
	@Getter @Setter
	private String photo;
	
	@Getter @Setter
	private String date;
	
	public CreateCellPhoneRequest() {
	}
}
