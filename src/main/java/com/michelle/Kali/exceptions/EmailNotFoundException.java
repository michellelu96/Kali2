package com.michelle.Kali.exceptions;

@SuppressWarnings("serial")
public class EmailNotFoundException extends IllegalArgumentException{
	public EmailNotFoundException(String msg) {
		super(msg);
	}

}
