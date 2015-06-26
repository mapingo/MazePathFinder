package com.sfrancis.mazepathfinder.loader;

import com.sfrancis.mazepathfinder.MazePathFinder;
import com.sfrancis.mazepathfinder.MazePathFinderFactory;
import com.sfrancis.mazepathfinder.matcher.IsPath;
import com.sfrancis.mazepathfinder.maze.Location;
import com.sfrancis.mazepathfinder.maze.Maze;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.sfrancis.mazepathfinder.matcher.IsPath.aPath;
import static com.sfrancis.mazepathfinder.maze.Location.aLocation;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MazeLoaderTest {

    @Test
    public void shouldCreateAMazeFromAReader() throws Exception {
        Reader reader = givenAValidMaze();
        Maze maze = new MazeLoader().createFrom(reader);
        assertThat(maze.getStart(), is(aLocation(0, 4)));
        assertThat(maze.getFinish(), is(aLocation(10, 7)));
        assertThat(maze.isASpace(aLocation(2, 6)), is(true));
        assertThat(maze.isASpace(aLocation(7, 3)), is(false));
    }

    @Test(expected = InvalidMazeFileException.class)
    public void shouldFailToLoadInvalidFile() throws Exception {
        InputStreamReader reader = givenAnInvalidMaze();
        new MazeLoader().createFrom(reader);
    }

    @Test
    public void shouldCreateAMazeAndSolve() throws Exception {
        Reader reader = givenAValidMaze();
        Maze maze = new MazeLoader().createFrom(reader);
        MazePathFinder mazePathFinder = MazePathFinderFactory.aMazePathFinder();
        List<Location> pathThrough = mazePathFinder.findPathThrough(maze);
        assertThat(pathThrough, is(expectedPath()));
    }

    private InputStreamReader givenAValidMaze() throws UnsupportedEncodingException {
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/TestMaze.txt");
        return new InputStreamReader(resourceAsStream, "UTF8");
    }

    private InputStreamReader givenAnInvalidMaze() throws UnsupportedEncodingException {
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/InvalidMaze.txt");
        return new InputStreamReader(resourceAsStream, "UTF8");
    }

    private IsPath expectedPath() {
        return aPath()
                .with(0, 4)
                .with(1, 4)
                .with(1, 5)
                .with(2, 5)
                .with(3, 5)
                .with(3, 4)
                .with(3, 3)
                .with(4, 3)
                .with(5, 3)
                .with(6, 3)
                .with(6, 2)
                .with(6, 1)
                .with(7, 1)
                .with(8, 1)
                .with(8, 2)
                .with(9, 2)
                .with(10, 2)
                .with(10, 3)
                .with(10, 4)
                .with(9, 4)
                .with(8, 4)
                .with(8, 5)
                .with(8, 6)
                .with(9, 6)
                .with(10, 6)
                .with(10, 7);
    }
}