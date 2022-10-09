package ru.mipt.bit.platformer.level;

import ru.mipt.bit.platformer.gridpoint.GridPoint;
import ru.mipt.bit.platformer.obstacle.Obstacle;
import ru.mipt.bit.platformer.tank.Tank;

import java.util.ArrayList;

public interface Level {
    boolean isOccupied(GridPoint pt);
    ArrayList<Tank> getTanks();
    ArrayList<Obstacle> getObstacles();
}
