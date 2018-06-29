package com.machnickiadrian.webstore.exception;

/**
 * Exception thrown, when currently registering new user is trying to use
 * already existing username.
 *
 * @author Adrian Machnicki
 */
public class UsernameExistsException extends RuntimeException {
    private static final long serialVersionUID = -2536234004361591008L;
    private static final String MESSAGE = "User with that username exists already.";

    @Override
    public String getMessage() {
        return UsernameExistsException.MESSAGE;
    }
}