package ru.mipt.bit.platformer.tank;

import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.gridpoint.GridPoint;

public interface Tank {
    void move(
            Direction direction,
            float deltaTime,
            float movementSpeed
    );

    GridPoint getDepartureCoordinates();
    GridPoint getDestinationCoordinates();
    float getMovementProgress();
    Direction getCurrentDirection();
}
