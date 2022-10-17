package ru.mipt.bit.platformer.obstacle.impl;

import ru.mipt.bit.platformer.gridpoint.GridPoint;
import ru.mipt.bit.platformer.obstacle.Obstacle;

import java.util.ArrayList;

public class ObstacleImpl implements Obstacle {
    private final GridPoint coordinates;

    public ObstacleImpl(GridPoint coordinates) {
        this.coordinates = coordinates;
    }

    public GridPoint getCoordinates() {
        return this.coordinates;
    }

    public ArrayList<GridPoint> occupiedCells() {
        ArrayList<GridPoint> occupied = new ArrayList<>();
        occupied.add(this.coordinates);
        return occupied;
    }
}
