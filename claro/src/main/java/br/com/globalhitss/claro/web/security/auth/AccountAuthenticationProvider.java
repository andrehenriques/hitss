package br.com.globalhitss.claro.web.security.auth;


import java.util.Objects;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.globalhitss.claro.helper.BCryptyHelper;
import br.com.globalhitss.claro.web.security.service.AccountDetailsService;

@Component
public class AccountAuthenticationProvider implements AuthenticationProvider {
	Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private AccountDetailsService accountDetailsService;

	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
	    String password = (String) authentication.getCredentials();
	    
	    if(username.trim().isEmpty() || password.trim().isEmpty()) {
	    	throw new AuthenticationCredentialsNotFoundException("Verifique os campo[user/pass]");
	    }
	    
	    UserDetails userDetails = accountDetailsService.loadUserByUsername(username);
	    
	    if(Objects.isNull(userDetails)) {
	    	throw new BadCredentialsException("Bad credentials.");
	    }
	    
	    if(!BCryptyHelper.matches(password, userDetails.getPassword())) {
	    	throw new BadCredentialsException("Bad credentials");
	    }
	    
	    if(!userDetails.isEnabled()) {
	    	throw new DisabledException("User is disabled");
	    }
	    
	    Authentication accountAuthentication = new AccountAuthentication(userDetails);
	    accountAuthentication.setAuthenticated(true);
	    return accountAuthentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
