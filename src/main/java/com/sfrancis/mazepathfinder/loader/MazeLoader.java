package com.sfrancis.mazepathfinder.loader;

import com.sfrancis.mazepathfinder.maze.DefaultMaze;
import com.sfrancis.mazepathfinder.maze.Location;
import com.sfrancis.mazepathfinder.maze.Maze;
import com.sfrancis.mazepathfinder.maze.MazePart;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

public class MazeLoader {
    public Maze createFrom(Reader reader) {
        try {
            BufferedReader bufferedReader = new BufferedReader(reader);
            Location start = parseLocation(bufferedReader.readLine());
            Location finish = parseLocation(bufferedReader.readLine());
            List<List<MazePart>> mazeParts = bufferedReader.lines().map(this::createMaze).collect(Collectors.toList());
            return new DefaultMaze(start, finish, mazeParts);
        } catch(Exception e) {
            throw new InvalidMazeFileException("Failed to load maze file.", e);
        }
    }

    private Location parseLocation(String line) {
        String[] splitAtEquals = line.split("=");
        String[] values = splitAtEquals[1].split(",");
        return Location.aLocation(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
    }

    private List<MazePart> createMaze(String line) {
        return line.chars().mapToObj(c -> createMazePart((char) c)).collect(Collectors.toList());
    }

    private MazePart createMazePart(char c) {
        switch(c) {
            case ' ': return MazePart.SPACE;
            case '*': return MazePart.WALL;
            default: throw new InvalidMazeFileException("Invalid maze file.");
        }
    }
}
