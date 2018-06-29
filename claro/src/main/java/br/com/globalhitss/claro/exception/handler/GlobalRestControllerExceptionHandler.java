package br.com.globalhitss.claro.exception.handler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.globalhitss.claro.helper.ExceptionResponseHelper;
import br.com.globalhitss.claro.web.dto.ExceptionResponse;
import br.com.globalhitss.claro.web.exception.BadRequestException;
import br.com.globalhitss.claro.web.exception.ResourceAlreadyException;
import br.com.globalhitss.claro.web.exception.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalRestControllerExceptionHandler {
	
	@ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ExceptionResponse> handleEntityConflictException(EntityExistsException e, HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.CONFLICT, "", "XXXX", "YYYYY");
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
	
	@ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ResourceAlreadyException.class)
    public ResponseEntity<ExceptionResponse> handleResourceAlreadyException(ResourceAlreadyException e, HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.CONFLICT, request.getRequestURI(), "CONFLICT", "CONFLICT");
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND, "", "XXXX", "YYYYY");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
	
	@ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ExceptionResponse> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.FORBIDDEN, "", "XXXX", "YYYYY");
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ExceptionResponse> handleBadCredentialsException(BadCredentialsException e, HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.UNAUTHORIZED, "", "XXXX", "YYYYY");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ExceptionResponse> httpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST, request.getRequestURI(), "BAD_REQUEST..", "BAD_REQUEST..");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException e, HttpServletRequest request) {
		ExceptionResponse response = null;
		response = ExceptionResponseHelper.createExceptionResponse(HttpStatus.BAD_REQUEST, request, e, e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
		List<FieldError> fe = e.getBindingResult().getFieldErrors();
		List<String> erros = new ArrayList<>(fe.size());
		fe.stream().forEach(f->{
			String erro = String.format("%s: %s", f.getField(), f.getDefaultMessage());
			erros.add(erro);
		});
		ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST, "", "BAD_REQUEST", erros.toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	
	
	
}
