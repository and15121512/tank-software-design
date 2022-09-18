package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

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

    public void movePoint(GridPoint2 pt) {
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
}
