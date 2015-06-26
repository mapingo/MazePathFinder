package com.sfrancis.mazepathfinder;

import com.sfrancis.mazepathfinder.maze.DefaultMaze;
import com.sfrancis.mazepathfinder.maze.Location;
import com.sfrancis.mazepathfinder.maze.MazePart;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.sfrancis.mazepathfinder.maze.Location.aLocation;
import static com.sfrancis.mazepathfinder.maze.MazePart.SPACE;
import static com.sfrancis.mazepathfinder.maze.MazePart.WALL;
import static com.sfrancis.mazepathfinder.matcher.IsPath.aPath;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MazePathFinderTest {

    private static final List<List<MazePart>> SOUTH_TO_NORTH_MAZE_PARTS = new ArrayList<List<MazePart>>() {{
        add(asList(WALL, SPACE, WALL));
        add(asList(WALL, SPACE, WALL));
        add(asList(WALL, SPACE, WALL));
    }};
    private static final List<List<MazePart>> WEST_TO_EAST_MAZE_PARTS = new ArrayList<List<MazePart>>() {{
        add(asList(WALL, WALL, WALL));
        add(asList(SPACE, SPACE, SPACE));
        add(asList(WALL, WALL, WALL));
    }};

    private MazePathFinder mazePathFinder;

    @Before
    public void setup() throws Exception {
        mazePathFinder = MazePathFinderFactory.aMazePathFinder();
    }

    @Test
    public void shouldFindPathFromSouthToNorth() throws Exception {
        List<Location> path = mazePathFinder.findPathThrough(aSouthToNorthMaze());

        assertThat(path, is(aPath()
                        .with(1, 0)
                        .with(1, 1)
                        .with(1, 2)
        ));
    }

    @Test
    public void shouldFindPathFromNorthToSouth() throws Exception {
        List<Location> path = mazePathFinder.findPathThrough(aNorthToSouthMaze());

        assertThat(path, is(aPath()
                        .with(1, 2)
                        .with(1, 1)
                        .with(1, 0)
        ));
    }

    @Test
    public void shouldFindPathWestToEastMaze() throws Exception {
        List<Location> path = mazePathFinder.findPathThrough(aWestToEastMaze());

        assertThat(path, is(aPath()
                        .with(0, 1)
                        .with(1, 1)
                        .with(2, 1)
        ));
    }

    @Test
    public void shouldFindPathFromEastToWestMaze() throws Exception {
        DefaultMaze maze = aEastToWestMaze();
        List<Location> path = mazePathFinder.findPathThrough(maze);

        assertThat(path, is(aPath()
                        .with(2, 1)
                        .with(1, 1)
                        .with(0, 1)
        ));
    }

    @Test
    public void shouldFindPathInComplexMaze() throws Exception {
        List<Location> path = mazePathFinder.findPathThrough(aComplexMaze());

        assertThat(path, is(aPath()
                        .with(0, 2)
                        .with(1, 2)
                        .with(1, 1)
                        .with(2, 1)
                        .with(3, 1)
                        .with(3, 2)
                        .with(4, 2)
                        .with(4, 3)
                        .with(4, 4)
        ));
    }

    @Test(expected = NoPathFoundThroughMazeException.class)
    public void shouldThrowExceptionIfNoPathFoundThroughMaze() throws Exception {
        DefaultMaze maze = aMazeWithNoPathToFinish();
        mazePathFinder.findPathThrough(maze);
    }

    private DefaultMaze aSouthToNorthMaze() {
        Location start = aLocation(1, 0);
        Location finish = aLocation(1, 2);
        return new DefaultMaze(start, finish, SOUTH_TO_NORTH_MAZE_PARTS);
    }

    private DefaultMaze aNorthToSouthMaze() {
        Location start = aLocation(1, 2);
        Location finish = aLocation(1, 0);
        return new DefaultMaze(start, finish, SOUTH_TO_NORTH_MAZE_PARTS);
    }

    private DefaultMaze aWestToEastMaze() {
        Location start = aLocation(0, 1);
        Location finish = aLocation(2, 1);
        return new DefaultMaze(start, finish, WEST_TO_EAST_MAZE_PARTS);
    }

    private DefaultMaze aEastToWestMaze() {
        Location start = aLocation(2, 1);
        Location finish = aLocation(0, 1);
        return new DefaultMaze(start, finish, WEST_TO_EAST_MAZE_PARTS);
    }

    private DefaultMaze aComplexMaze() {
        Location start = aLocation(0, 2);
        Location finish = aLocation(4, 4);
        List<List<MazePart>> mazeRepresentation = new ArrayList<List<MazePart>>() {{
            add(asList(SPACE, SPACE, WALL, SPACE, SPACE));
            add(asList(WALL, SPACE, SPACE, SPACE, WALL));
            add(asList(SPACE, SPACE, WALL, SPACE, SPACE));
            add(asList(WALL, SPACE, WALL, WALL, SPACE));
            add(asList(SPACE, SPACE, SPACE, WALL, SPACE));
        }};

        return new DefaultMaze(start, finish, mazeRepresentation);
    }

    private DefaultMaze aMazeWithNoPathToFinish() {
        Location start = aLocation(2, 1);
        Location finish = aLocation(0, 1);
        List<List<MazePart>> mazeRepresentation = new ArrayList<List<MazePart>>() {{
            add(asList(WALL, WALL, WALL));
            add(asList(SPACE, WALL, SPACE));
            add(asList(WALL, WALL, WALL));
        }};

        return new DefaultMaze(start, finish, mazeRepresentation);
    }
}
