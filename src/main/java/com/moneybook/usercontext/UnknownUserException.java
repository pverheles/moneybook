package com.moneybook.usercontext;

public class UnknownUserException extends RuntimeException {

    public UnknownUserException(String message) {
        super(message);
    }
}
