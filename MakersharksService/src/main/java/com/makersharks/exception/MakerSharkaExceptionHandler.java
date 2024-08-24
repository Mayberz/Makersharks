package com.makersharks.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class MakerSharkaExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> validatorExceptionHandler(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			String fieldName = error.getField();
			String errorMessage = error.getDefaultMessage();
			errorMap.put(fieldName, errorMessage);
		});
		return errorMap;

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGlobalException(Exception ex) {
		return new ResponseEntity<>("something went wrong! Please try again later :)",
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
