package br.com.globalhitss.claro.web.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.globalhitss.claro.web.dto.ExceptionResponse;
import br.com.globalhitss.claro.web.exception.InvalidTokenException;
import br.com.globalhitss.claro.web.exception.TokenNotFoundException;

public abstract class BaseFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		((HttpServletResponse)response).setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		try {
			filter(request, response, chain);
			
		} catch(TokenNotFoundException e) {
			((HttpServletResponse)response).setStatus(HttpStatus.BAD_REQUEST.value());
			ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, "/path", "Sem token", "");
			String resonseString = mapper.writeValueAsString(exceptionResponse);
			
			((HttpServletResponse)response).getWriter().print(resonseString);
			
		} catch (InvalidTokenException e) {
			((HttpServletResponse)response).setStatus(HttpStatus.UNAUTHORIZED.value());
			ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.UNAUTHORIZED, "/path", "Token invalido", "");
			mapper.writeValueAsString(exceptionResponse);
			String resonseString = mapper.writeValueAsString(exceptionResponse);
			((HttpServletResponse)response).getWriter().print(resonseString);
			
		} catch(Exception e) {
			e.printStackTrace();
			((HttpServletResponse)response).setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, "/path", "Algum erro no servidor! :(", "");
			mapper.writeValueAsString(exceptionResponse);
			String resonseString = mapper.writeValueAsString(exceptionResponse);
			((HttpServletResponse)response).getWriter().print(resonseString);
		}
	}
	
	public abstract void filter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException;

}
