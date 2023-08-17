package org.jsp.api.exception;

import org.jsp.api.service.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShoppingCartApiExceptionHandler {
@ExceptionHandler(IdNotFoundException.class)
public ResponseEntity<ResponseStructure<String>> handleINFE(IdNotFoundException exception){
	ResponseStructure<String> s=new ResponseStructure<>();
	s.setBody("user not found");
	s.setMessage(exception.getMessage());
	s.setCode(HttpStatus.NOT_FOUND.value());
	return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.NOT_FOUND);
	
}
@ExceptionHandler(CrNotFoundException.class)
public ResponseEntity<ResponseStructure<String>> handleCNFX(CrNotFoundException exception){
	ResponseStructure<String> s=new ResponseStructure<>();
	s.setBody("user not found");
	s.setMessage(exception.getMessage());
	s.setCode(HttpStatus.NOT_FOUND.value());
	return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.NOT_FOUND);
	
}
}
