package ru.mipt.bit.platformer.level.impl;

import ru.mipt.bit.platformer.gridpoint.GridPoint;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.obstacle.Obstacle;
import ru.mipt.bit.platformer.tank.Tank;

import java.util.ArrayList;

public class LevelImpl implements Level {
    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    private final ArrayList<Tank> tanks;
    private final ArrayList<Obstacle> obstacles;

    public LevelImpl(ArrayList<Tank> tanks, ArrayList<Obstacle> obstacles) {
        this.tanks = tanks;
        this.obstacles = obstacles;
    }

    public boolean isOccupied(GridPoint pt) {
        for (var obstacle : obstacles) {
            if (obstacle.getCoordinates().equals(pt)) {
                return true;
            }
        }
        for (var tank : tanks) {
            // TODO: get positions occupied by tank and compare with pt in cycle
        }
        return false;
    }
}
