package com.univalle.cincuentazo.models;

public class NoHayCartasException extends RuntimeException {
    public NoHayCartasException(String message) {
        super(message);
    }
}
