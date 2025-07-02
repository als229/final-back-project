package com.kh.finalproject.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kh.finalproject.exception.exceptions.DuplicateNicknameException;
import com.kh.finalproject.exception.exceptions.DuplicateUserEmailException;
import com.kh.finalproject.exception.exceptions.DuplicateUserIdException;
import com.kh.finalproject.exception.exceptions.DuplicateUserNickameException;
import com.kh.finalproject.exception.exceptions.EmailCodeException;
import com.kh.finalproject.exception.exceptions.InvaildFindIdException;
import com.kh.finalproject.exception.exceptions.InvaildFindPwException;
import com.kh.finalproject.exception.exceptions.InvaildPasswordException;
import com.kh.finalproject.exception.exceptions.InvalidTokenException;
import com.kh.finalproject.exception.exceptions.LoginFailedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(DuplicateUserIdException.class)
	public ResponseEntity<?> handlerDuplicateUserIdException(DuplicateUserIdException e){
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	
	@ExceptionHandler(DuplicateUserNickameException.class)
	public ResponseEntity<?> handlerDuplicateUserNickameException(DuplicateUserNickameException e){
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	
	@ExceptionHandler(DuplicateUserEmailException.class)
	public ResponseEntity<?> handlerDuplicateUserEmailException(DuplicateUserEmailException e){
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	@ExceptionHandler(LoginFailedException.class)
	public ResponseEntity<?> handlerLoginFailedException(LoginFailedException e){
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<?> handlerInvalidTokenException(InvalidTokenException e){
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	@ExceptionHandler(InvaildFindIdException.class)
	public ResponseEntity<?> handlerInvaildFindIdException(InvaildFindIdException e){
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	@ExceptionHandler(InvaildFindPwException.class)
	public ResponseEntity<?> handlerInvaildFindPwException(InvaildFindPwException e){
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	@ExceptionHandler(EmailCodeException.class)
	public ResponseEntity<?> handlerEmailCodeException(EmailCodeException e){
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	@ExceptionHandler(InvaildPasswordException.class)
	public ResponseEntity<?> handlerInvaildPasswordException(InvaildPasswordException e){
		Map<String, String> error = new HashMap<>();
		error.put("code", "E401_INVALID_PASSWORD");
		error.put("message", e.getMessage());
		
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	@ExceptionHandler(DuplicateNicknameException.class)
	public ResponseEntity<?> handlerDuplicateNicknameException(DuplicateNicknameException e){
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	
	
}
