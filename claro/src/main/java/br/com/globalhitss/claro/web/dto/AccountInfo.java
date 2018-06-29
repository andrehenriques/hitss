package br.com.globalhitss.claro.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class AccountInfo {
	private Long id;
	private String name;
	private String email;
	
	public AccountInfo() {
	}
	
	
}
