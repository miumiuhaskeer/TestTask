package org.example.testtask.exceptions;

/**
 * Throws if cat not found in database
 */
public class CatNotFoundException extends RuntimeException {

    public CatNotFoundException(String message) {
        super(message);
    }
}
