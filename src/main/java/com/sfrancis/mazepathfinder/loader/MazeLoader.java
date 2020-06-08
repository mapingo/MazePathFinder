package com.sfrancis.mazepathfinder.loader;

import com.sfrancis.mazepathfinder.maze.DefaultMaze;
import com.sfrancis.mazepathfinder.maze.Location;
import com.sfrancis.mazepathfinder.maze.Maze;
import com.sfrancis.mazepathfinder.maze.MazePart;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

import static com.sfrancis.mazepathfinder.maze.MazePart.SPACE;
import static com.sfrancis.mazepathfinder.maze.MazePart.WALL;

public class MazeLoader {
    public Maze createFrom(final Reader reader) {
        try {
            final BufferedReader bufferedReader = new BufferedReader(reader);
            final Location start = parseLocation(bufferedReader.readLine());
            final Location finish = parseLocation(bufferedReader.readLine());
            final List<List<MazePart>> mazeParts = bufferedReader.lines().map(this::createMaze).collect(Collectors.toList());

            return new DefaultMaze(start, finish, mazeParts);
        } catch (Exception e) {
            throw new InvalidMazeFileException("Failed to load maze file.", e);
        }
    }

    private Location parseLocation(final String line) {
        final String[] splitAtEquals = line.split("=");
        final String[] values = splitAtEquals[1].split(",");
        return Location.aLocation(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
    }

    private List<MazePart> createMaze(final String line) {
        return line.chars().mapToObj(c -> createMazePart((char) c)).collect(Collectors.toList());
    }

    private MazePart createMazePart(final char c) {
        switch (c) {
            case ' ':
                return SPACE;
            case '*':
                return WALL;
            default:
                throw new InvalidMazeFileException("Invalid maze file.");
        }
    }
}
