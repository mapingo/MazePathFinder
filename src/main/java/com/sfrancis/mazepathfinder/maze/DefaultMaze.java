package com.sfrancis.mazepathfinder.maze;

import java.util.List;

import static com.sfrancis.mazepathfinder.maze.MazePart.SPACE;

public class DefaultMaze implements Maze {
    private final Location start;
    private final Location finish;
    private final List<List<MazePart>> mazeRepresentation;

    public DefaultMaze(final Location start, final Location finish, final List<List<MazePart>> mazeRepresentation) {
        this.start = start;
        this.finish = finish;
        this.mazeRepresentation = mazeRepresentation;
    }

    public Location getStart() {
        return Location.aLocation(start);
    }

    public Location getFinish() {
        return finish;
    }

    public boolean isASpace(final Location location) {
        return isNotOutOfBounds(location) && mazeRepresentation.get(location.getY()).get(location.getX()).equals(SPACE);
    }

    public boolean isNotOutOfBounds(final Location location) {
        final int x = location.getX();
        final int y = location.getY();

        return x >= 0 &&
                y >= 0 &&
                y < mazeRepresentation.size() &&
                x < mazeRepresentation.get(y).size();
    }
}
