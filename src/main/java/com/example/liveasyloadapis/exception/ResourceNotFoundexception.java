package com.example.liveasyloadapis.exception;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class ResourceNotFoundexception {
	public ResourceNotFoundexception(String message){
    	super();
    }

}
