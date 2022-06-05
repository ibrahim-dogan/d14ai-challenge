package com.example.d14aichallenge.exceptions;

public class CitizenNotFoundException extends RuntimeException {
    public CitizenNotFoundException(Long id) {
        super("Could not find citizen: " + id);
    }
}
