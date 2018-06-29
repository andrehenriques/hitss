package br.com.globalhitss.claro.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.globalhitss.claro.domain.model.Account;
import br.com.globalhitss.claro.web.dto.AccountInfo;
import lombok.extern.java.Log;

@Log
public abstract class BaseController {
	
	public AccountInfo getAccount() {
		Account ud = (Account) getAuthentication().getDetails();
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setEmail(ud.getUsername());
		accountInfo.setName(ud.getName());
		accountInfo.setId(ud.getId());
		log.info(accountInfo.toString());
        return accountInfo;
    }
	
	public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
