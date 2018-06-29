package br.com.globalhitss.claro.web.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.globalhitss.claro.domain.model.Account;
import br.com.globalhitss.claro.web.security.repository.AccountDetailsRepository;

@Service
public class AccountDetailsService implements UserDetailsService {
	
	@Autowired
	private AccountDetailsRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account authenticated = accountRepository.findByUsername(username);
		return authenticated;
	}

}
