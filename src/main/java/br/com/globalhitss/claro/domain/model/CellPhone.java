package br.com.globalhitss.claro.domain.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="cellphones")
public class CellPhone {
	
	@Id
	@Indexed(unique = true)
	private String code;
	
	private String model;
	private Double price;
	private String brand;
	private String photo;
	private LocalDate date;
}
