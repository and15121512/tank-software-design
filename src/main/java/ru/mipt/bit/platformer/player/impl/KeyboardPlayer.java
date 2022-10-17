package ru.mipt.bit.platformer.player.impl;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.player.Player;
import ru.mipt.bit.platformer.tank.Tank;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;

public class KeyboardPlayer implements Player {
    private final Tank managedTank;
    private final Level level;
    private final float movementSpeed;

    public KeyboardPlayer(Tank managedTank, Level level, float movementSpeed) {
        this.managedTank = managedTank;
        this.level = level;
        this.movementSpeed = movementSpeed;
    }

    public void live(float deltaTime) {
        var desiredDirection = getDesiredDirection();
        var desiredCoordinates = desiredDirection.getMovedPoint(managedTank.getDepartureCoordinates());
        if (!(managedTank.isDestinationReached() && level.isOccupied(desiredCoordinates))) {
            managedTank.move(desiredDirection, deltaTime, movementSpeed);
        }
    }

    private Direction getDesiredDirection() {
        Direction desiredDirection = Direction.NODIRECTION;
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            desiredDirection = Direction.UP;
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            desiredDirection = Direction.LEFT;
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            desiredDirection = Direction.DOWN;
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            desiredDirection = Direction.RIGHT;
        }
        return desiredDirection;
    }
}
