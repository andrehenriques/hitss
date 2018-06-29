package br.com.globalhitss.claro.domain.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.globalhitss.claro.domain.data.repository.AccountRepository;
import br.com.globalhitss.claro.domain.model.Account;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	public void create(final Account account) {
		accountRepository.save(account);
	}
	
	public Account getByUsername(final String username) {
		return accountRepository.findByusername(username);
	}
	

	public boolean existsEmail(final String email) {
		return Objects.isNull(accountRepository.getIdByEmail(email))? false: true;
	}

}
