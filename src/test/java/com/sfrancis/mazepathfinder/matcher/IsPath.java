package com.sfrancis.mazepathfinder.matcher;

import com.sfrancis.mazepathfinder.maze.Location;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.LinkedList;
import java.util.List;

import static com.sfrancis.mazepathfinder.maze.Location.aLocation;

public class IsPath extends TypeSafeMatcher<List<Location>> {
    List<Location> expectedLocations = new LinkedList<>();

    @Override
    protected boolean matchesSafely(List<Location> locations) {
        return locations.equals(expectedLocations);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(expectedLocations.toString());
    }

    public static IsPath aPath() {
        return new IsPath();
    }

    public IsPath with(Location location) {
        expectedLocations.add(location);
        return this;
    }

    public IsPath with(int x, int y) {
        expectedLocations.add(aLocation(x, y));
        return this;
    }
}
