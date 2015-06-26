package com.sfrancis.mazepathfinder;

import com.sfrancis.mazepathfinder.maze.Location;
import com.sfrancis.mazepathfinder.maze.Maze;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

public class DefaultMazePathFinder implements MazePathFinder {

    private final HashSet<Location> visitedLocations = new HashSet<>();
    private final Queue<Location> queuedLocations = new ArrayDeque<>();

    public List<Location> findPathThrough(final Maze maze) {
        visitedLocations.clear();
        queuedLocations.clear();
        queuedLocations.add(maze.getStart());

        while (moreLocationsToCheck() && finishLocationNotFound(maze)) {
            Location currentLocation = queuedLocations.remove();
            checkNeighbouringLocations(currentLocation, maze);
        }

        if (queuedLocations.isEmpty()) {
            throw new NoPathFoundThroughMazeException();
        }

        return queuedLocations.remove().getRoute();
    }

    private void checkNeighbouringLocations(final Location currentLocation, final Maze maze) {
        final Location northLocation = Location.aLocationNorthOf(currentLocation);
        final Location southLocation = Location.aLocationSouthOf(currentLocation);
        final Location westLocation = Location.aLocationWestOf(currentLocation);
        final Location eastLocation = Location.aLocationEastOf(currentLocation);

        checkLocation(northLocation, maze, currentLocation);
        checkLocation(southLocation, maze, currentLocation);
        checkLocation(westLocation, maze, currentLocation);
        checkLocation(eastLocation, maze, currentLocation);
    }

    private void checkLocation(final Location nextLocation, final Maze maze, final Location currentLocation) {
        final boolean hasNotBeenVisitedYet = !visitedLocations.contains(nextLocation);
        final boolean isNotOutsideOfMaze = maze.isNotOutOfBounds(nextLocation);
        final boolean isASpaceInTheMaze = isNotOutsideOfMaze && maze.isASpace(nextLocation);

        if (hasNotBeenVisitedYet && isNotOutsideOfMaze && isASpaceInTheMaze) {
            queuedLocations.offer(nextLocation);
            visitedLocations.add(nextLocation);
            nextLocation.setPath(currentLocation);
        }
    }

    private boolean finishLocationNotFound(final Maze maze) {
        return !maze.getFinish().equals(queuedLocations.peek());
    }

    private boolean moreLocationsToCheck() {
        return !queuedLocations.isEmpty();
    }
}
