package com.sbt.javaschool.rnd.refactoring2;

import com.sbt.javaschool.rnd.refactoring2.Orientation;
import com.sbt.javaschool.rnd.refactoring2.Tractor;
import com.sbt.javaschool.rnd.refactoring2.TractorInDitchException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TractorTest {

    @Test
    public void testShouldMoveForward(){
        Tractor tractor = new Tractor();
        tractor.move("F");
        assertEquals(0, tractor.getPositionX());
        assertEquals(1, tractor.getPositionY());
    }

    @Test
    public void testShouldTurn(){
        Tractor tractor = new Tractor();
        tractor.move("T");
        assertEquals(Orientation.EAST, tractor.getOrientation());
        tractor.move("T");
        assertEquals(Orientation.SOUTH, tractor.getOrientation());
        tractor.move("T");
        assertEquals(Orientation.WEST, tractor.getOrientation());
        tractor.move("T");
        assertEquals(Orientation.NORTH, tractor.getOrientation());
    }
    @Test
    public void testShouldTurnAndMoveInTheRightDirection(){
        Tractor tractor = new Tractor();
        tractor.move("T");
        tractor.move("F");
        assertEquals(1, tractor.getPositionX());
        assertEquals(0, tractor.getPositionY());
        tractor.move("T");
        tractor.move("F");
        assertEquals(1, tractor.getPositionX());
        assertEquals(-1, tractor.getPositionY());
        tractor.move("T");
        tractor.move("F");
        assertEquals(0, tractor.getPositionX());
        assertEquals(-1, tractor.getPositionY());
        tractor.move("T");
        tractor.move("F");
        assertEquals(0, tractor.getPositionX());
        assertEquals(0, tractor.getPositionY());
    }

    @Test
    public void testShouldThrowExceptionIfFallsOffPlateau(){
        Tractor tractor = new Tractor();
        tractor.move("F");
        tractor.move("F");
        tractor.move("F");
        tractor.move("F");
        tractor.move("F");
        try{
            tractor.move("F");
            fail("Tractor was expected to fall off the plateau");
        }catch(TractorInDitchException expected){
        }
    }
}