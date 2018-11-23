package org.beelinelibgdx.exception;

public class BeelineLoadFileNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public BeelineLoadFileNotFoundException(String path) {
        super("Could not locate: " + path);
    }

}
