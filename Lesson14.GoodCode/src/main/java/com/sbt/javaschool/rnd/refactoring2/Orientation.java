package com.sbt.javaschool.rnd.refactoring2;

public enum Orientation {

    NORTH(0), WEST(3), SOUTH(2), EAST(1);

    private final int counter;
    private int currentOrientation = 0;

    Orientation(int inc) {
        this.counter = inc;
    }

        public Orientation turnClockwise() {
            currentOrientation++;
            if (currentOrientation >= values().length) {
                currentOrientation = 0;
            }
            return getValue(currentOrientation);
        }

        private Orientation getValue(int val) {
        for (Orientation o : values()) {
            if ( val == o.counter) {
                return o;
            }
        }
        return null;
        }
}
