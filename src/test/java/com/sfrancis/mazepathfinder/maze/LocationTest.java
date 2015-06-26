package com.sfrancis.mazepathfinder.maze;

import org.junit.Test;

import static com.sfrancis.mazepathfinder.matcher.IsPath.aPath;
import static com.sfrancis.mazepathfinder.maze.Location.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void shouldReturnXAndYFromLocation() throws Exception {
        Location location = aLocation(1, 2);
        assertThat(location.getX(), is(1));
        assertThat(location.getY(), is(2));
    }

    @Test
    public void shouldReturnPathFromStartToFinishLocation() throws Exception {
        Location start = aLocation(0, 0);
        Location middle = aLocation(1, 0);
        Location finish = aLocation(2, 0);

        middle.setPath(start);
        finish.setPath(middle);

        assertThat(finish.getRoute(), is(aPath()
                        .with(start)
                        .with(middle)
                        .with(finish))
        );
    }

    @Test
    public void shouldReturnTrueIfTwoLocationsAreEqual() throws Exception {
        Location firstLocation = aLocation(6, 8);
        Location secondLocation = aLocation(6, 8);

        assertTrue(firstLocation.equals(secondLocation));
    }

    @Test
    public void shouldReturnFalseIfTwoLocationsHaveDifferentY() throws Exception {
        Location firstLocation = aLocation(6, 9);
        Location secondLocation = aLocation(6, 8);

        assertFalse(firstLocation.equals(secondLocation));
    }

    @Test
    public void shouldReturnFalseIfTwoLocationsHaveDifferentX() throws Exception {
        Location firstLocation = aLocation(7, 8);
        Location secondLocation = aLocation(6, 8);

        assertFalse(firstLocation.equals(secondLocation));
    }

    @Test
    public void shouldReturnCorrectHashCodeForLocation() throws Exception {
        Location location = aLocation(2, 2);
        assertThat(location.hashCode(), is(1025));
    }

    @Test
    public void shouldReturnCorrectToStringRepresentation() throws Exception {
        Location location = aLocation(2, 3);
        assertThat(location.toString(), is("Location{x=2, y=3}"));
    }

    @Test
    public void shouldReturnALocationNorthOfTheGivenLocation() throws Exception {
        Location currentLocation = aLocation(0, 0);
        Location northLocation = aLocationNorthOf(currentLocation);
        assertThat(northLocation, is(aLocation(0, 1)));
    }

    @Test
    public void shouldReturnALocationSouthOfTheGivenLocation() throws Exception {
        Location currentLocation = aLocation(0, 0);
        Location northLocation = aLocationSouthOf(currentLocation);
        assertThat(northLocation, is(aLocation(0, -1)));
    }

    @Test
    public void shouldReturnALocationWestOfTheGivenLocation() throws Exception {
        Location currentLocation = aLocation(0, 0);
        Location northLocation = aLocationWestOf(currentLocation);
        assertThat(northLocation, is(aLocation(-1, 0)));
    }

    @Test
    public void shouldReturnALocationEastOfTheGivenLocation() throws Exception {
        Location currentLocation = aLocation(0, 0);
        Location northLocation = aLocationEastOf(currentLocation);
        assertThat(northLocation, is(aLocation(1, 0)));
    }

    @Test
    public void shouldReturnALocationEqualToGivenLocation() throws Exception {
        Location initialLocation = aLocation(1, 1);
        Location copyOfLocation = aLocation(initialLocation);
        assertThat(copyOfLocation, is(initialLocation));
    }
}