package com.algaworks.osworks.api.exception_handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.osworks.api.exception_handler.Problema.Campo;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request
		) {
		
		List<Campo> campos = ex.getBindingResult().getAllErrors()
			.stream()
			.map(error -> {
				return new Problema.Campo(
						((FieldError) error).getField(), 
						error.getDefaultMessage()
				);
			})
			.collect(Collectors.toList());
	
	
		Problema problema = new Problema();
		
		problema.setStatus(status.value());
		problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
		problema.setDataHora(LocalDateTime.now());
		problema.setCampos(campos);
		
		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}
	
}
