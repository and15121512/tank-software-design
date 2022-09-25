package ru.mipt.bit.platformer.player.impl;

import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.gridpoint.GridPoint;
import ru.mipt.bit.platformer.obstacle.Obstacle;
import ru.mipt.bit.platformer.player.FloatComparator;
import ru.mipt.bit.platformer.player.Player;
import ru.mipt.bit.platformer.player.ProgressUpdater;

public class PlayerImpl implements Player {
    private final GridPoint departureCoordinates;
    private final GridPoint destinationCoordinates;
    private float movementProgress;
    private Direction currentDirection;
    private ProgressUpdater progressUpdater;
    FloatComparator floatComparator;

    public GridPoint getDepartureCoordinates() {
        return departureCoordinates;
    }
    public GridPoint getDestinationCoordinates() {
        return destinationCoordinates;
    }
    public float getMovementProgress() {
        return movementProgress;
    }
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public PlayerImpl(
            GridPoint startCoordinates,
            Direction startDirection,
            ProgressUpdater progressUpdater,
            FloatComparator floatComparator
    ) {
        this.destinationCoordinates = startCoordinates;
        this.departureCoordinates = new GridPoint(destinationCoordinates);
        this.currentDirection = startDirection;
        this.movementProgress = 1f;
        this.progressUpdater = progressUpdater;
        this.floatComparator = floatComparator;
    }

    public void move(
            Direction desiredDirection,
            float deltaTime,
            float movementSpeed,
            Obstacle obstacle
    ) {
        tryToChangeMovementDirection(desiredDirection, obstacle.getCoordinates());
        updateProgress(deltaTime, movementSpeed);
        if (reachedDestinationCheck()) {
            finishMovement();
        }
    }

    private void tryToChangeMovementDirection(Direction desiredDirection, GridPoint obstacleCoordinates) {
        if (desiredDirection != Direction.NODIRECTION && reachedDestinationCheck()) {
            this.currentDirection = desiredDirection;
            if (noObstacle(desiredDirection, obstacleCoordinates)) {
                desiredDirection.movePoint(destinationCoordinates);
                startMovement();
            }
        }
    }

    private void updateProgress(float deltaTime, float movementSpeed) {
        this.movementProgress = progressUpdater.updateProgress(this.movementProgress, deltaTime, movementSpeed);
    }

    private void finishMovement() {
        departureCoordinates.set(destinationCoordinates);
    }

    private boolean noObstacle(Direction direction, GridPoint obstacleCoordinates) {
        GridPoint coordinatesCopy = new GridPoint(this.departureCoordinates.x, this.departureCoordinates.y);
        direction.movePoint(coordinatesCopy);
        return !obstacleCoordinates.equals(coordinatesCopy);
    }

    private void startMovement() {
        this.movementProgress = 0f;
    }

    private boolean reachedDestinationCheck() {
        return floatComparator.isEqual(this.movementProgress, 1f);
    }
}
