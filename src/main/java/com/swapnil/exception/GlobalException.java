package com.swapnil.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalException {

	
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyError> UserException(UserException em,WebRequest req){
		
		MyError myerror=new MyError(em.getMessage(), LocalDate.now(), req.getDescription(false));
		
		return new ResponseEntity<MyError>(myerror, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(EventException.class)
	public ResponseEntity<MyError> EventException(EventException em,WebRequest req){
		
		MyError myerror=new MyError(em.getMessage(), LocalDate.now(), req.getDescription(false));
		
		return new ResponseEntity<MyError>(myerror, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyError> methodException(MethodArgumentNotValidException em){
		
		MyError myerror=new MyError(em.getBindingResult().getFieldError().getDefaultMessage(), LocalDate.now(), "validation failed");
		
		return new ResponseEntity<MyError>(myerror, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyError> nohandllerException(NoHandlerFoundException em,WebRequest req){
		
		MyError myerror=new MyError(em.getMessage(), LocalDate.now(), req.getDescription(false));
		
		return new ResponseEntity<MyError>(myerror, HttpStatus.BAD_REQUEST);
	}
	
	
}
