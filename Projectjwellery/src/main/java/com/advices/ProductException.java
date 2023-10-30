package com.advices;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class ProductException extends Exception{
	
	public ProductException(String msg)
	{
		super(msg);
	}

}
