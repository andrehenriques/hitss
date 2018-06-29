package br.com.globalhitss.claro.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.globalhitss.claro.helper.ExceptionResponseHelper;
import br.com.globalhitss.claro.web.dto.ExceptionResponse;

@RestControllerAdvice
public class InternalServerErrorExceptionHandler {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e, HttpServletRequest request) {
		e.printStackTrace();
		ExceptionResponse response = null;
		response = ExceptionResponseHelper.createExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, request, e, "Algun erro interno1");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
		e.printStackTrace();
		ExceptionResponse response = null;
		response = ExceptionResponseHelper.createExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, request, e, "Algun erro interno2");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
    
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
		e.printStackTrace();
		ExceptionResponse response = null;
		response = ExceptionResponseHelper.createExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, request, e, "Algun erro interno3");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(NullPointerException e, HttpServletRequest request) {
		e.printStackTrace();
		ExceptionResponse response = null;
		response = ExceptionResponseHelper.createExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, request, e, "Algun erro interno4");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
}
