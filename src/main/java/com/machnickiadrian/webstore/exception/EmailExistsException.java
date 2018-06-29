package com.machnickiadrian.webstore.exception;

/**
 * Exception thrown, when currently registering new user is trying to use
 * already existing email.
 *
 * @author Adrian Machnicki
 */
public class EmailExistsException extends RuntimeException {
    private static final long serialVersionUID = -2909583045070281638L;
    private static final String MESSAGE = "User with that email address exists already.";

    @Override
    public String getMessage() {
        return EmailExistsException.MESSAGE;
    }

}