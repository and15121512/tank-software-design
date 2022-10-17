package ru.mipt.bit.platformer.level.impl;

import ru.mipt.bit.platformer.gridpoint.GridPoint;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.obstacle.Obstacle;
import ru.mipt.bit.platformer.tank.Tank;

import java.util.ArrayList;
import java.util.Random;

public class LevelImpl implements Level {
    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    private final ArrayList<Tank> tanks;
    private final ArrayList<Obstacle> obstacles;
    private final int height;
    private final int width;

    public LevelImpl(ArrayList<Tank> tanks, ArrayList<Obstacle> obstacles, int height, int width) {
        this.tanks = tanks;
        this.obstacles = obstacles;
        this.height = height;
        this.width = width;
    }

    public boolean isOccupied(GridPoint pt) {
        boolean xInside = (pt.x >= 0 && pt.x < this.width);
        boolean yInside = (pt.y >= 0 && pt.y < this.height);
        if (!xInside || !yInside) {
            return true;
        }
        for (var obstacle : obstacles) {
            if (obstacle.occupiedCells().contains(pt)) {
                return true;
            }
        }
        for (var tank : tanks) {
            if (tank.occupiedCells().contains(pt)) {
                return true;
            }
        }
        return false;
    }

    public void addTank(Tank tank) {
        tanks.add(tank);
    }

    public GridPoint randomCell() {
        int x = randIntFromRange(0, this.width);
        int y = randIntFromRange(0, this.height);
        return new GridPoint(x, y);
    }

    private static int randIntFromRange(int min, int max) {
        var rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
