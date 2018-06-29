package br.com.globalhitss.claro.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.globalhitss.claro.helper.ExceptionResponseHelper;
import br.com.globalhitss.claro.web.dto.ExceptionResponse;

public class GenericExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ExceptionResponse> handleJWTVerificationException(JWTVerificationException e, HttpServletRequest request) {
		e.printStackTrace();
		ExceptionResponse response = null;
		response = ExceptionResponseHelper.createExceptionResponse(HttpStatus.BAD_REQUEST, request, e, "Erro ao processar o hash");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	
}
