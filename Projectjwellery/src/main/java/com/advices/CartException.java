package com.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class CartException extends Exception{

	public CartException(String msg)
	{
		super(msg);
	}

}
