package com.sfrancis.mazepathfinder;

public class MazePathFinderFactory {
    public static MazePathFinder aMazePathFinder() {
        return new DefaultMazePathFinder();
    }
}
