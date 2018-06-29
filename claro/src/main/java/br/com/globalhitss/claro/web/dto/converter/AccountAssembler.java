package br.com.globalhitss.claro.web.dto.converter;

import org.springframework.stereotype.Component;

import br.com.globalhitss.claro.domain.model.Account;
import br.com.globalhitss.claro.web.dto.CreateAccountDTO;

@Component
public class AccountAssembler {

	public Account toAccount(CreateAccountDTO createAccountDTO) {
		Account account = new Account();
		account.setUsername(createAccountDTO.email);
		account.setPassword(createAccountDTO.rawPassword);
		account.setName(createAccountDTO.name);
		return account;
	}
	
}
