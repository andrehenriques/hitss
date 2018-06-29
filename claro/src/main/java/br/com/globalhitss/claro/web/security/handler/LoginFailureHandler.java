package br.com.globalhitss.claro.web.security.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.globalhitss.claro.web.dto.ExceptionResponse;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    	ObjectMapper mapper = new ObjectMapper();
    	PrintWriter writer = response.getWriter();
    	((HttpServletResponse)response).setContentType("application/json");
    	writer.write(exception.getMessage());
        String resonseString = null;
        
    	if(exception.getClass().isAssignableFrom(DisabledException.class)) {
	        ((HttpServletResponse)response).setStatus(HttpStatus.UNAUTHORIZED.value());
			ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.UNAUTHORIZED, "/path", exception.getMessage(), "");
			resonseString = mapper.writeValueAsString(exceptionResponse);
			
    	} else if(exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
    		((HttpServletResponse)response).setStatus(HttpStatus.BAD_REQUEST.value());
			ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, "/login", "BadCredentialsException", "");
			resonseString = mapper.writeValueAsString(exceptionResponse);
			
    	} else if(exception.getClass().isAssignableFrom(AuthenticationCredentialsNotFoundException.class)) {
    		((HttpServletResponse)response).setStatus(HttpStatus.BAD_REQUEST.value());
			ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, "/path", "BadCredentialsException", "");
			resonseString = mapper.writeValueAsString(exceptionResponse);
			
    	} else if(exception.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
    		((HttpServletResponse)response).setStatus(HttpStatus.BAD_REQUEST.value());
			ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, "/path", "Usuário não encontrado", "");
			resonseString = mapper.writeValueAsString(exceptionResponse);
    	}
    	
    	writer.write(resonseString);
    	writer.flush();
    	
       
    }
    
}
