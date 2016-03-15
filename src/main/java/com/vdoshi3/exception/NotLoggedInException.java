package com.vdoshi3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NO_CONTENT, reason = "No JWT Found")
public class NotLoggedInException extends Exception{
	private static final long serialVersionUID = 1L;
}
