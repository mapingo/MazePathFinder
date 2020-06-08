package com.sfrancis.mazepathfinder.loader;

public class InvalidMazeFileException extends RuntimeException {
    public InvalidMazeFileException(final String message) {
        super(message);
    }

    public InvalidMazeFileException(final String message, final Exception e) {
        super(message, e);
    }
}
