package com.michelle.Kali.exceptions;

@SuppressWarnings("serial")
public class ProductNotExistException extends IllegalArgumentException {
	public ProductNotExistException(String msg) {
		super(msg);
	}
}
