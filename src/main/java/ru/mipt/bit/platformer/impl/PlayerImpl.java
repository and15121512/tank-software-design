package ru.mipt.bit.platformer.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.Direction;
import ru.mipt.bit.platformer.Obstacle;
import ru.mipt.bit.platformer.Player;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class PlayerImpl implements Player {
    private final TextureRegion graphics;
    private final Rectangle rectangle;
    private final GridPoint2 coordinates;
    private final GridPoint2 destinationCoordinates;
    private float movementProgress;
    private Direction direction;
    private final TileMovement tileMovement;

    public PlayerImpl(
            Texture texture,
            GridPoint2 startCoordinates,
            TileMovement tileMovement,
            Direction startDirection
    ) {
        this.graphics = new TextureRegion(texture);
        this.rectangle = createBoundingRectangle(graphics);

        this.destinationCoordinates = startCoordinates;
        this.coordinates = new GridPoint2(destinationCoordinates);
        this.direction = startDirection;

        this.movementProgress = 1f;
        this.tileMovement = tileMovement;
    }

    public void move(
            Direction desiredDirection,
            float deltaTime,
            float movementSpeed,
            Obstacle obstacle
    ) {
        tryToChangeMovementDirection(desiredDirection, obstacle.getCoordinates());
        updateCoordinates(deltaTime, movementSpeed);
        if (reachedDestination()) {
            finishMovement();
        }
    }

    public void draw(Batch batch) {
        drawTextureRegionUnscaled(batch, this.graphics, this.rectangle, this.direction.getAngle());
    }

    private void tryToChangeMovementDirection(Direction desiredDirection, GridPoint2 obstacleCoordinates) {
        if (desiredDirection != Direction.NODIRECTION && reachedDestination()) {
            this.direction = desiredDirection;
            if (noObstacle(desiredDirection, obstacleCoordinates)) {
                desiredDirection.movePoint(destinationCoordinates);
                startMovement();
            }
        }
    }

    private void updateCoordinates(float deltaTime, float movementSpeed) {
        this.tileMovement.moveRectangleBetweenTileCenters(
                this.rectangle,
                this.coordinates,
                this.destinationCoordinates,
                this.movementProgress
        );
        this.movementProgress = continueProgress(this.movementProgress, deltaTime, movementSpeed);
    }

    private void finishMovement() {
        coordinates.set(destinationCoordinates);
    }

    private boolean noObstacle(Direction direction, GridPoint2 obstacleCoordinates) {
        GridPoint2 coordinatesCopy = new GridPoint2(this.coordinates.x, this.coordinates.y);
        direction.movePoint(coordinatesCopy);
        return !obstacleCoordinates.equals(coordinatesCopy);
    }

    private void startMovement() {
        this.movementProgress = 0f;
    }

    private boolean reachedDestination() {
        return isEqual(this.movementProgress, 1f);
    }
}
