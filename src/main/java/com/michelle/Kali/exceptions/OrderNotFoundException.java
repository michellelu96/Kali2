package com.michelle.Kali.exceptions;

@SuppressWarnings("serial")
public class OrderNotFoundException extends IllegalArgumentException {
    public OrderNotFoundException(String msg) {
        super(msg);
    }
}
