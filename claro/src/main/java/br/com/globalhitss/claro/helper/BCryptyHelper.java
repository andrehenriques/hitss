package br.com.globalhitss.claro.helper;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptyHelper {
	
	private static BCryptPasswordEncoder cryptPasswordEncoder;
	
	@PostConstruct
	public void cryptPasswordEncoder() {
		cryptPasswordEncoder = new BCryptPasswordEncoder();
	}
	
	public static String encode(String rawPassword) {
		System.out.println(rawPassword);
		System.out.println(cryptPasswordEncoder);
		return cryptPasswordEncoder.encode(rawPassword);
	}
	
	public static boolean matches(CharSequence rawPassword, String encodedPassword) {
		return cryptPasswordEncoder.matches(rawPassword, encodedPassword);
	}
	
}
