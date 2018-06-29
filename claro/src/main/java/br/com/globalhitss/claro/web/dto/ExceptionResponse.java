package br.com.globalhitss.claro.web.dto;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
	private int status;
	private String msg;
	private String  cause;
	private String path;
	
	public ExceptionResponse(HttpStatus status, String path, String msg, String cause) {
		this.status = status.value();
		this.path = path;
		this.msg = msg;
		this.cause = cause;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status.value();
	}

	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

}
