package br.com.globalhitss.claro.domain.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection="cellphones")
public class CellPhone {
	
	@Id
	private String id;
	
	@Indexed(unique = true)
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
	
	@Override
	public String toString() {
		return String.format("%s-%s", brand, model);
	}
}
