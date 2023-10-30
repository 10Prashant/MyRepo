package com.advices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class PaymentException extends Exception{
	
	public PaymentException(String msg)
	{
		super(msg);
	}

}
