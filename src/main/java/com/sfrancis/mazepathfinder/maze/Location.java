package com.sfrancis.mazepathfinder.maze;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Location {
    private final int x;
    private final int y;
    private Location path;

    public static Location aLocation(final int x, final int y) {
        return new Location(x, y);
    }

    public static Location aLocation(final Location location) {
        return new Location(location.getX(), location.getY());
    }

    public static Location aLocationNorthOf(final Location location) {
        return new Location(location.getX(), location.getY() + 1);
    }

    public static Location aLocationSouthOf(final Location location) {
        return new Location(location.getX(), location.getY() - 1);
    }

    public static Location aLocationWestOf(final Location location) {
        return new Location(location.getX() - 1, location.getY());
    }

    public static Location aLocationEastOf(final Location location) {
        return new Location(location.getX() + 1, location.getY());
    }

    private Location(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPath(final Location location) {
        this.path = location;
    }

    public List<Location> getRoute() {
        final LinkedList<Location> route = new LinkedList<>();
        getRoute(route);
        return route;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        final Location location = (Location) o;
        return Objects.equals(x, location.x) &&
                Objects.equals(y, location.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    private void getRoute(final Deque<Location> stack) {
        stack.push(this);
        final boolean thereIsAPathNode = path != null;
        if (thereIsAPathNode) {
            path.getRoute(stack);
        }
    }
}
