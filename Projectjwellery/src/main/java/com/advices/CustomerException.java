package com.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class CustomerException extends Exception{

	public CustomerException(String msg)
	{
		super(msg);
	}
}
