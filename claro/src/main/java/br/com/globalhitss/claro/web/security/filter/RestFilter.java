package br.com.globalhitss.claro.web.security.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.globalhitss.claro.config.RedisConfiguration;
import br.com.globalhitss.claro.domain.model.Account;
import br.com.globalhitss.claro.helper.JwtHelper;
import br.com.globalhitss.claro.helper.RedisKeysHelper;
import br.com.globalhitss.claro.web.exception.InvalidTokenException;
import br.com.globalhitss.claro.web.exception.TokenNotFoundException;
import br.com.globalhitss.claro.web.security.auth.AccountAuthentication;

@Component
public class RestFilter extends BaseFilter {
	
	@Autowired
	private RedisConfiguration redis;
	
	private Set<RequestMatcher> matchers = null;
	
	public RestFilter() {
		matchers = new HashSet<>();
	}
	
	public void filter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, TokenNotFoundException, InvalidTokenException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		if(noRequiresAuthentication(httpRequest)) {
			chain.doFilter(request, response);
			return;
		}
		
		String token = getToken(httpRequest);
		
		UserDetails userDetails = getValidUserDetailsByToken(token);
		
		AccountAuthentication accountAuthentication = new AccountAuthentication(userDetails);
		accountAuthentication.setAuthenticated(true);
		SecurityContextHolder.getContext().setAuthentication(accountAuthentication);
		
		request.setAttribute("user", userDetails);
		
        chain.doFilter(request, response);
        
        return;
	}

	private UserDetails getValidUserDetailsByToken(String token) {
		return getUserDetailsByToken(token);
		//return null;
	}
	
	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("X-Auth-Token");
		
		if(Objects.isNull(token) || token.trim().isEmpty()) {
			throw new TokenNotFoundException();
		}
		
		return token;
	}

	private boolean noRequiresAuthentication(HttpServletRequest request) {
		for(RequestMatcher matcher: matchers) {
			if(matcher.matches(request)) {
				return true;
			}
		}
		return false;
	}

	public void noRequiresAuthentication(HttpMethod method, String... paths) {
		for(String path: paths) {
			matchers.add(new AntPathRequestMatcher(path, method.name()));
		}
	}
	
	public UserDetails getUserDetailsByToken(String token) {
		String key = RedisKeysHelper.generateAuthtokenKey(token);
		Object tokemFromRedis =  redis.redisTemplate().opsForValue().get(key);
		
		if(Objects.isNull(tokemFromRedis)) {
			throw new InvalidTokenException("Token inválido");
		}
		
		DecodedJWT decoder = null;
		try {
			decoder = JwtHelper.getDecoder(token);
		} catch (JWTVerificationException e) {
			throw new InvalidTokenException(String.format("Tokem invalido [%s]", key));
		}
		Long id = decoder.getHeaderClaim("user.id").as(Long.class);
		String name = decoder.getHeaderClaim("user.name").asString();
		
		System.out.println("DEVO SETAR O Nome e o ID RestFilter");
		String issuer = decoder.getIssuer();
		Account user = new Account(id);
		user.setUsername(issuer);
		user.setName(name);
		

		return user;
	}

}
