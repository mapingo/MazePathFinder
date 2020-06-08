package com.sfrancis.mazepathfinder.maze;

public interface Maze {
    Location getStart();

    Location getFinish();

    boolean isASpace(final Location location);

    boolean isNotOutOfBounds(final Location location);
}
