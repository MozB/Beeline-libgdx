package org.beelinelibgdx.exception;

public class BeelineDeleteFailureException extends Exception {

    private static final long serialVersionUID = 1L;

    public BeelineDeleteFailureException(String path) {
        super("Could not delete: " + path);
    }

}
