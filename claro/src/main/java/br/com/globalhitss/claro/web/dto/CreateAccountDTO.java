package br.com.globalhitss.claro.web.dto;

import javax.validation.constraints.NotNull;

public class CreateAccountDTO {
	@NotNull
	public String name;
	
	@NotNull
	public String email;
	
	@NotNull
	public String rawPassword;
}
