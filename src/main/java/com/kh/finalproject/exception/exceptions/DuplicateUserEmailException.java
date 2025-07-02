package com.kh.finalproject.exception.exceptions;

public class DuplicateUserEmailException extends RuntimeException{
	
	public DuplicateUserEmailException(String message) {
		super(message);
	}
}
