package br.com.globalhitss.claro.web.security.auth;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AccountAuthentication implements Authentication {
	private UserDetails userDetails;
	private boolean authenticated = false;
	
	public AccountAuthentication(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	@Override
	public String getName() {
		return userDetails.getUsername();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return userDetails.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		return userDetails.getUsername();
	}

	@Override
	public UserDetails getDetails() {
		return userDetails;
	}

	@Override
	public Object getPrincipal() {
		return userDetails.getUsername();
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
		this.authenticated = authenticated;
	}

}
