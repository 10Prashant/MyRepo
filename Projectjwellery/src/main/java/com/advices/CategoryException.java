package com.advices;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class CategoryException extends Exception{

	public CategoryException(String msg)
	{
		super(msg);
	}

}
