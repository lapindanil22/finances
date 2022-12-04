package com.example.finances.exceptions;

public class WrongSecretWordException extends RuntimeException {
    public WrongSecretWordException(String s) {
        super(s);
    }
}
