package com.auth.exceptions;

//import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.google.common.net.HttpHeaders;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
//		FieldErrorResponse fieldErrorResponse = new FieldErrorResponse();
//
//		List<CustomFieldError> fieldErrors = new ArrayList<>();
//		
//		ex.getBindingResult().getAllErrors().forEach((error) -> {
//			CustomFieldError fieldError = new CustomFieldError();
//			fieldError.setField(((FieldError) error).getField());
//			fieldError.setMessage(error.getDefaultMessage());
//			fieldErrors.add(fieldError);
//		});
//
//		fieldErrorResponse.setFieldErrors(fieldErrors);
//		return new ResponseEntity<>(fieldErrorResponse, status);		
		
    	
    	List<String> errors  = ex.getBindingResult().getAllErrors()
    			.stream()
    			.map(x -> x.getDefaultMessage())
    			.collect(Collectors.toList());
		return new ResponseEntity<Object>(errors, status);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUsernameNotFoundException(UserNotFoundException unfe){
		return new ResponseEntity<String>(unfe.getLocalizedMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<String> handleUserAlreadyExistException(UserAlreadyExistException unfe){
		return new ResponseEntity<String>(unfe.getLocalizedMessage(), HttpStatus.IM_USED);
	}
	
}
