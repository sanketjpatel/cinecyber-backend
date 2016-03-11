package com.vdoshi3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason = "Invalid JWT")
public class InvalidSignatureException extends Exception{
	private static final long serialVersionUID = 1L;
}
