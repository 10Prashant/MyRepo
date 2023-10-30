package com.advices;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(value=HttpStatus.CONFLICT)
public class AdminException extends Exception{

	public AdminException(String msg)
	{
		super(msg);
	}
}
