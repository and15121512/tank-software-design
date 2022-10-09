package ru.mipt.bit.platformer.obstacle.impl;

import ru.mipt.bit.platformer.gridpoint.GridPoint;
import ru.mipt.bit.platformer.obstacle.Obstacle;

public class ObstacleImpl implements Obstacle {
    private final GridPoint coordinates;

    public ObstacleImpl(GridPoint coordinates) {
        this.coordinates = coordinates;
    }

    public GridPoint getCoordinates() {
        return this.coordinates;
    }
}
