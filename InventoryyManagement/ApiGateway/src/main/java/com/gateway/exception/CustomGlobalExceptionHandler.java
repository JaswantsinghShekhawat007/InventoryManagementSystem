package com.gateway.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.google.common.net.HttpHeaders;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	
    	List<String> errors  = ex.getBindingResult().getAllErrors()
    			.stream()
    			.map(x -> x.getDefaultMessage())
    			.collect(Collectors.toList());
		return new ResponseEntity<Object>(errors, status);
	}
	
	
	@ExceptionHandler(AuthorizationDeniedException.class)
	public ResponseEntity<String> handleAuthorizationDeniedException(AuthorizationDeniedException ade){
		return new ResponseEntity<String>(ade.getLocalizedMessage(), HttpStatus.FORBIDDEN);
	} 
	
	@ExceptionHandler(MissingAuthorizationHeaderException.class)
	public ResponseEntity<String> handleMissingAuthorizationHeaderException(MissingAuthorizationHeaderException mahe){
		return new ResponseEntity<String>(mahe.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	} 
	
}
