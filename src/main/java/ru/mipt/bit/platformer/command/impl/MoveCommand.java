package ru.mipt.bit.platformer.command.impl;

import ru.mipt.bit.platformer.command.Command;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.tank.Tank;

public class MoveCommand implements Command {
    Direction desiredDirection;
    Tank managedTank;
    Level level;
    float deltaTime;
    float movementSpeed;

    public MoveCommand(
            Direction desiredDirection,
            Tank managedTank,
            Level level,
            float deltaTime,
            float movementSpeed
    ) {
        this.desiredDirection = desiredDirection;
        this.managedTank = managedTank;
        this.level = level;
        this.deltaTime = deltaTime;
        this.movementSpeed = movementSpeed;
    }

    @Override
    public void execute() {
        var desiredCoordinates = desiredDirection.getMovedPoint(managedTank.getDepartureCoordinates());
        if (!(managedTank.isDestinationReached() && level.isOccupied(desiredCoordinates))) {
            managedTank.move(desiredDirection, deltaTime, movementSpeed);
//            System.out.printf("X: %d, Y: %d%n",
//                    managedTank.getDestinationCoordinates().x,
//                    managedTank.getDestinationCoordinates().y
//            );
        }
    }
}
