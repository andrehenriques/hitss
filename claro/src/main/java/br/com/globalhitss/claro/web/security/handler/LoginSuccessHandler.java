package br.com.globalhitss.claro.web.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import br.com.globalhitss.claro.domain.model.Account;
import br.com.globalhitss.claro.web.security.service.JwtAuthtokenService;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private JwtAuthtokenService jwtAuthtokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest hsr, HttpServletResponse res, Authentication authentication) throws IOException, ServletException {
    	Assert.notNull(authentication, "authentication não pode ser definida");
    	
    	String authtoken = jwtAuthtokenService.createAndSaveAuthtokenFromAuthentication(authentication);
    	
    	Account account = (Account) authentication.getDetails();
    	
    	String json = String.format("{\"id\":\"%s\",\"authToken\":\"%s\", \"username\":\"%s\", \"name\":\"%s\"}", account.getId(), authtoken, account.getUsername(), account.getName());
    	res.addHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
    	res.getWriter().println(json);
        res.getWriter().flush();
    }
    
}
