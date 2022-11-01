package ru.mipt.bit.platformer.level;

import ru.mipt.bit.platformer.gridpoint.GridPoint;
import ru.mipt.bit.platformer.obstacle.Obstacle;
import ru.mipt.bit.platformer.tank.Tank;

import java.util.ArrayList;
import java.util.List;

public interface Level {
    boolean isOccupied(GridPoint pt);
    ArrayList<Tank> getTanks();
    Tank getPlayer();
    List<Tank> getBots();
    ArrayList<Obstacle> getObstacles();
    int getHeight();
    int getWidth();
    void addTank(Tank tank);
    GridPoint randomCell();
}
