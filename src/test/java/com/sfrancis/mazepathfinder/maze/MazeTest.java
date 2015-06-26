package com.sfrancis.mazepathfinder.maze;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.sfrancis.mazepathfinder.maze.Location.aLocation;
import static com.sfrancis.mazepathfinder.maze.MazePart.SPACE;
import static com.sfrancis.mazepathfinder.maze.MazePart.WALL;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MazeTest {

    private static final Location START_LOCATION = aLocation(0, 0);
    private static final Location FINISH_LOCATION = aLocation(1, 1);
    private static final List<List<MazePart>> MAZE_PARTS = new ArrayList<List<MazePart>>() {{
            add(asList(WALL, WALL, WALL));
            add(asList(SPACE, SPACE, SPACE));
    }};

    private DefaultMaze maze;

    @Before
    public void setup() throws Exception {
        maze = new DefaultMaze(START_LOCATION, FINISH_LOCATION, MAZE_PARTS);
    }

    @Test
    public void shouldReturnStartAndFinishLocation() throws Exception {
        assertThat(maze.getStart(), is(START_LOCATION));
        assertThat(maze.getFinish(), is(FINISH_LOCATION));
    }

    @Test
    public void shouldReturnTrueIfSpaceAtLocation() throws Exception {
        assertTrue(maze.isASpace(FINISH_LOCATION));
    }

    @Test
    public void shouldReturnFalseIfNotSpaceAtLocation() throws Exception {
        assertFalse(maze.isASpace(START_LOCATION));
    }

    @Test
    public void shouldReturnFalseIfLocationOutsideBounds() throws Exception {
        assertFalse(maze.isASpace(aLocation(2, 2)));
    }

    @Test
    public void shouldBeTrueForCornerLocations() throws Exception {
        boolean southWest = maze.isNotOutOfBounds(aLocation(0, 0));
        boolean northWest = maze.isNotOutOfBounds(aLocation(0, 1));
        boolean southEast = maze.isNotOutOfBounds(aLocation(2, 0));
        boolean northEast = maze.isNotOutOfBounds(aLocation(2, 1));

        assertThat(southWest, is(true));
        assertThat(northWest, is(true));
        assertThat(southEast, is(true));
        assertThat(northEast, is(true));
    }

    @Test
    public void shouldBeFalseIfLocationIsOutOfBounds() throws Exception {
        boolean westEdge = maze.isNotOutOfBounds(aLocation(-1, 0));
        boolean southEdge = maze.isNotOutOfBounds(aLocation(0, -1));
        boolean eastEdge = maze.isNotOutOfBounds(aLocation(3, 0));
        boolean northEdge = maze.isNotOutOfBounds(aLocation(2, 2));

        assertThat(westEdge, is(false));
        assertThat(southEdge, is(false));
        assertThat(eastEdge, is(false));
        assertThat(northEdge, is(false));
    }
}