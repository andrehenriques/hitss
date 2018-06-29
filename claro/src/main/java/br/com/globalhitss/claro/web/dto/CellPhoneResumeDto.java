package br.com.globalhitss.claro.web.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

public class CellPhoneResumeDto {
	@Getter @Setter
	private String code;
	
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
}
