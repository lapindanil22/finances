package com.example.finances.exceptions;

public class ExpiredAccountException extends RuntimeException {
    public ExpiredAccountException(String s) {
        super(s);
    }
}
