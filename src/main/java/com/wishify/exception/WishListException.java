package com.wishify.exception;

public class WishListException extends  RuntimeException{
    public WishListException() {
    }

    public WishListException(String message) {
        super(message);
    }
}
