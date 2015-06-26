package com.sfrancis.mazepathfinder.maze;

public interface Maze {
    Location getStart();

    Location getFinish();

    boolean isASpace(Location location);

    boolean isNotOutOfBounds(Location location);
}
