package com.sbt.javaschool.rnd.refactoring2;

import com.sbt.javaschool.rnd.refactoring2.Orientation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrientationTest {

    @Test
    void turnClockwise() {
        Orientation orientation = Orientation.NORTH;

        assertEquals(Orientation.EAST, orientation.turnClockwise());
        assertEquals(Orientation.SOUTH, orientation.turnClockwise());
        assertEquals(Orientation.WEST, orientation.turnClockwise());
        assertEquals(Orientation.NORTH, orientation.turnClockwise());
    }

}