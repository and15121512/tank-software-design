package ru.mipt.bit.platformer.direction;

import ru.mipt.bit.platformer.gridpoint.GridPoint;

import java.util.*;

public enum Direction {
    RIGHT(0),
    UP(90),
    LEFT(-180),
    DOWN(-90),
    NODIRECTION(45);

    private final int angle;

    Direction(int angle) {
        this.angle = angle;
    }

    public float getAngle() {
        return this.angle;
    }

    public void movePoint(GridPoint pt) {
        switch (this) {
            case RIGHT:
                ++pt.x;
                break;
            case UP:
                ++pt.y;
                break;
            case LEFT:
                --pt.x;
                break;
            case DOWN:
                --pt.y;
                break;
            case NODIRECTION:
                break;
        }
    }

    public GridPoint getMovedPoint(GridPoint pt) {
        var ptCopy = new GridPoint(pt.x, pt.y);
        movePoint(ptCopy);
        return ptCopy;
    }

    private static final List<Direction> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Direction randomDirection(boolean includeNoDirection) {
        while (true) {
            var direction = VALUES.get(RANDOM.nextInt(SIZE));
            if (direction != NODIRECTION || includeNoDirection) {
                return direction;
            }
        }
    }
}
