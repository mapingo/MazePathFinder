package com.sfrancis.mazepathfinder;

import com.sfrancis.mazepathfinder.maze.Location;
import com.sfrancis.mazepathfinder.maze.Maze;

import java.util.List;

public interface MazePathFinder {
    List<Location> findPathThrough(final Maze maze);
}
