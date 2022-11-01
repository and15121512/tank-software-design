package ru.mipt.bit.platformer.command.generator.impl;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.command.Command;
import ru.mipt.bit.platformer.command.generator.CommandGenerator;
import ru.mipt.bit.platformer.command.impl.MoveCommand;
import ru.mipt.bit.platformer.deltatime.DeltaTime;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.tank.Tank;

import java.util.ArrayList;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;

public class KeyboardPlayerCommandGenerator implements CommandGenerator {
    Tank player;
    Level level;
    DeltaTime deltaTime;
    float movementSpeed;

    public KeyboardPlayerCommandGenerator(Tank player, Level level, DeltaTime deltaTime, float movementSpeed) {
        this.player = player;
        this.level = level;
        this.deltaTime = deltaTime;
        this.movementSpeed = movementSpeed;
    }

    public ArrayList<Command> getCommands() {
        ArrayList<Command> commands = new ArrayList<>();
        var direction = getDesiredDirection();
        commands.add(new MoveCommand(direction, player, level, deltaTime.getValue(), movementSpeed));
        return commands;
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
