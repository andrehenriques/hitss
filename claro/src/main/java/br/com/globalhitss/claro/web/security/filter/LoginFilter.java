package br.com.globalhitss.claro.web.security.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import br.com.globalhitss.claro.web.exception.BadRequestException;
import br.com.globalhitss.claro.web.security.handler.LoginFailureHandler;
import br.com.globalhitss.claro.web.security.handler.LoginSuccessHandler;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	public LoginFilter(LoginSuccessHandler loginSuccessHandler, LoginFailureHandler loginFailureHandler) {
		super(new AntPathRequestMatcher("/login", "POST"));
		setAuthenticationSuccessHandler(loginSuccessHandler);
		setAuthenticationFailureHandler(loginFailureHandler);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
		String line = null;
		while ((line = br.readLine()) != null) {
		    sb.append(line + "\n");
		}
		br.close();
		
		ObjectMapper mapper = new ObjectMapper();
		LoginInfo info = null;
		try {
			info = mapper.readValue(sb.toString(), LoginInfo.class);
		} catch (UnrecognizedPropertyException | JsonParseException e) {
			e.printStackTrace();
			throw new BadRequestException("Invalid Data - Filter");
		}

		if (info.username == null) {
			info.username = "";
		}

		if (info.password == null) {
			info.password = "";
		}

		info.username = info.username.trim();

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(info.username, info.password);
		
		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}
	
	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
	    super.setAuthenticationManager(authenticationManager);
	}
	
	protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}


}
