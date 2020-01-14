package com.sbt.javaschool.rnd.refactoring2;

public class Tractor {
    private Position position = new Position();
    private final Position area = new Position( 5, 5 );
    Orientation orientation = Orientation.NORTH;

    public void move(String command) {
        if (command.equals("F")) {
            moveForwards();
            return;
        }
        if (command.equals("T")) {
            turnClockwise();
        }
    }

    public void moveForwards() {
        switch (orientation) {
            case NORTH:
                position.incY();
                break;
            case EAST:
                position.incX();
                break;
            case SOUTH:
                position.decY();
                break;
            case WEST:
                position.decX();
                break;
        }
        if (isOutArea()) {
            throw new TractorInDitchException();
        }
    }

    public boolean isOutArea() {
        return (position.getX() > area.getX()) || (position.getY() > area.getY());
    }
    public void turnClockwise() {
       orientation = orientation.turnClockwise();
    }

    public int getPositionX() {
        return position.getX();
    }

    public int getPositionY() {
        return position.getY();
    }

    public Orientation getOrientation() {
        return orientation;
    }

    private class Position {
        private int x;
        private int y;

        Position() {

        }

        Position(int x, int y) {
          this.x = x;
          this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void incX() {
            x++;
        }

        public void decX() {
            x--;
        }

        public  void incY() {
            y++;
        }

        public void decY() {
            y--;
        }
    }
}
