package ru.mipt.bit.platformer.player;

import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.gridpoint.GridPoint;
import ru.mipt.bit.platformer.obstacle.Obstacle;

public interface Player {
    void move(
            Direction direction,
            float deltaTime,
            float movementSpeed,
            Obstacle obstacle
    );

    GridPoint getDepartureCoordinates();
    GridPoint getDestinationCoordinates();
    float getMovementProgress();
    Direction getCurrentDirection();
}
