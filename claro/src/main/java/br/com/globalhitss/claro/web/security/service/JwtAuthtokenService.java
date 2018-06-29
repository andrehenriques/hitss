package br.com.globalhitss.claro.web.security.service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.globalhitss.claro.config.RedisConfiguration;
import br.com.globalhitss.claro.domain.model.Account;
import br.com.globalhitss.claro.helper.JwtHelper;
import br.com.globalhitss.claro.helper.RedisKeysHelper;

@Service
public class JwtAuthtokenService {
	
	@Autowired
	private RedisConfiguration redis;
	
	
	public String createAndSaveAuthtokenFromAuthentication(Authentication authentication) {
		Account details = (Account) authentication.getDetails();
		
		String authtoken = createTokenFromUsername(details);
		
		//String tokensKey = RedisKeysHelper.generateAuthtokensKey(authentication.getPrincipal().toString());
		String tokenKey = RedisKeysHelper.generateAuthtokenKey(authtoken);
		
		//redis.redisTemplate().opsForList().leftPush(tokensKey, authtoken);
		redis.redisTemplate().opsForValue().set(tokenKey, authtoken);
		
		Date expire = Date.from(Instant.now().plusSeconds(60*60*60*24*365));
		
		//redis.redisTemplate().expireAt(tokensKey, expire);
		redis.redisTemplate().expireAt(tokenKey, expire);
		
		return authtoken;
	}
	
	

	public String createTokenFromUsername(Account userDetails) {
		Map<String, Object> header = new HashMap<>();
		
		header.put("user.id", userDetails.getId());
		header.put("user.name", userDetails.getName());
		
		return JwtHelper.createHash("Auth", userDetails.getUsername(), null, header);
	}

}
