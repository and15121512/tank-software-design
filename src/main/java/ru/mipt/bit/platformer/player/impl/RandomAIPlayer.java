package ru.mipt.bit.platformer.player.impl;

import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.gridpoint.GridPoint;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.player.Player;
import ru.mipt.bit.platformer.tank.Tank;

public class RandomAIPlayer implements Player {
    private final Tank managedTank;
    private final Level level;
    private final float movementSpeed;

    public RandomAIPlayer(Tank managedTank, Level level, float movementSpeed) {
        this.managedTank = managedTank;
        this.level = level;
        this.movementSpeed = movementSpeed;
    }

    public void live(float deltaTime) {
        Direction newDirection = Direction.randomDirection(false);
        GridPoint newDestination = newDirection.getMovedPoint(managedTank.getDepartureCoordinates());
        if (!(managedTank.isDestinationReached() && level.isOccupied(newDestination))) {
            managedTank.move(newDirection, deltaTime, movementSpeed);
        }
    }
}
