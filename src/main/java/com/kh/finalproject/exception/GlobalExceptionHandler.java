package com.kh.finalproject.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kh.finalproject.exception.exceptions.DatabaseException;
import com.kh.finalproject.exception.exceptions.DuplicateNicknameException;
import com.kh.finalproject.exception.exceptions.DuplicateUserEmailException;
import com.kh.finalproject.exception.exceptions.DuplicateUserIdException;
import com.kh.finalproject.exception.exceptions.DuplicateUserNickameException;
import com.kh.finalproject.exception.exceptions.EmailCodeException;
import com.kh.finalproject.exception.exceptions.FileUploadException;
import com.kh.finalproject.exception.exceptions.InvaildFindIdException;
import com.kh.finalproject.exception.exceptions.InvaildFindPwException;
import com.kh.finalproject.exception.exceptions.InvaildPasswordException;
import com.kh.finalproject.exception.exceptions.InvalidRequestException;
import com.kh.finalproject.exception.exceptions.InvalidTokenException;
import com.kh.finalproject.exception.exceptions.LoginFailedException;
import com.kh.finalproject.exception.exceptions.ServiceException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(DuplicateUserIdException.class)
	public ResponseEntity<?> handlerDuplicateUserIdException(DuplicateUserIdException e){
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	
	@ExceptionHandler(DuplicateUserNickameException.class)
	public ResponseEntity<?> handlerDuplicateUserNickameException(DuplicateUserNickameException e){
		
		Map<String,String> error = new HashMap<>();
		error.put("code", "E400_DUPLICATION_NICKNAME");
		error.put("message", e.getMessage());
		
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(DuplicateUserEmailException.class)
	public ResponseEntity<?> handlerDuplicateUserEmailException(DuplicateUserEmailException e){
		
		Map<String,String> error = new HashMap<>();
		error.put("code", "E400_DULPICATION_EMAIL");
		error.put("message", e.getMessage());
		
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	@ExceptionHandler(LoginFailedException.class)
	public ResponseEntity<?> handlerLoginFailedException(LoginFailedException e){
		
		Map<String, String> error = new HashMap<>();
		error.put("code", "E401_INVALID_ID or PW");
		error.put("message", e.getMessage());
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<?> handlerInvalidTokenException(InvalidTokenException e){
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	@ExceptionHandler(InvaildFindIdException.class)
	public ResponseEntity<?> handlerInvaildFindIdException(InvaildFindIdException e){
		
		Map<String, String> error = new HashMap<>();
		error.put("code", "E404_INVALID_ACCOUNT");
		error.put("message", e.getMessage());
	
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	@ExceptionHandler(InvaildFindPwException.class)
	public ResponseEntity<?> handlerInvaildFindPwException(InvaildFindPwException e){
		
		Map<String, String> error = new HashMap<>();
		error.put("code", "E404_INVALID_ACCOUNT");
		error.put("message", e.getMessage());
		
		
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
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
		
		Map<String,String> error = new HashMap<>();
		error.put("code", "E400_DUPLICATION_NICKNAME");
		error.put("message", e.getMessage());
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

//-------------------------------------------------------------------------------------------------------------------------------
	
	// content 관련 
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<?> handleInvalidRequestException(InvalidRequestException e) {
        Map<String, String> error = new HashMap<>();
        error.put("code", "E400_INVALID_REQUEST");
        error.put("message", e.getMessage());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<?> handleFileUploadException(FileUploadException e) {
        Map<String, String> error = new HashMap<>();
        error.put("code", "E500_FILE_UPLOAD_FAILED");
        error.put("message", e.getMessage());
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<?> handleDatabaseException(DatabaseException e) {
        Map<String, String> error = new HashMap<>();
        error.put("code", "E500_DATABASE_ERROR");
        error.put("message", e.getMessage());
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> handleServiceException(ServiceException e) {
        Map<String, String> error = new HashMap<>();
        error.put("code", "E500_SERVICE_ERROR");
        error.put("message", e.getMessage());
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

//-------------------------------------------------------------------------------------------------------------------------------
	
	
}

