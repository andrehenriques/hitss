package br.com.globalhitss.claro.helper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import br.com.globalhitss.claro.web.dto.ExceptionResponse;


public final class ExceptionResponseHelper {
	
	public static ExceptionResponse createExceptionResponse(HttpStatus status, HttpServletRequest request, Exception e, String msg) {
		return createExceptionResponse(status, request, e.getMessage(), msg);
	}
	
	public static ExceptionResponse createExceptionResponse(HttpStatus status, HttpServletRequest request, String cause, String msg) {
		final ExceptionResponse er = new ExceptionResponse(status, request.getRequestURI(), msg, cause);
		return er;
	}
	
}
