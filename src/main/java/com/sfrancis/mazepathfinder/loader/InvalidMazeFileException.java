package com.sfrancis.mazepathfinder.loader;

public class InvalidMazeFileException extends RuntimeException {
    public InvalidMazeFileException(String message) {
        super(message);
    }

    public InvalidMazeFileException(String message, Exception e) {
        super(message, e);
    }
}
