package com.easy.course.resources.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.easy.course.services.exception.DataBaseException;
import com.easy.course.services.exception.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

/*
 * anotation q vai interceptar as exceções q acontecerem para q esse obj possa executar o tratamento
 */
@ControllerAdvice
public class ResourceExceptionHandler {

	/*
	 * vai interceptar qq exceção Reource..., e vai executar o tratamento do metodo
	 * q é montar o standardError e publicá-lo
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
		}
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> dataBase(DataBaseException e, HttpServletRequest request) {
		String error = "Database error ";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
		}	
}
