package ru.mipt.bit.platformer.command.generator.impl;

import ru.mipt.bit.platformer.aiadapter.AIAdapter;
import ru.mipt.bit.platformer.aiadapter.Action;
import ru.mipt.bit.platformer.command.Command;
import ru.mipt.bit.platformer.command.generator.CommandGenerator;
import ru.mipt.bit.platformer.command.impl.MoveCommand;
import ru.mipt.bit.platformer.deltatime.DeltaTime;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.tank.Tank;

import java.util.ArrayList;
import java.util.List;

public class SmartAICommandGenerator implements CommandGenerator {
    List<Tank> tanks;
    Level level;
    DeltaTime deltaTime;
    float movementSpeed;
    AIAdapter aiAdapter;

    public SmartAICommandGenerator(List<Tank> tanks, Level level, DeltaTime deltaTime, float movementSpeed, AIAdapter aiAdapter) {
        this.tanks = tanks;
        this.level = level;
        this.deltaTime = deltaTime;
        this.movementSpeed = movementSpeed;
        this.aiAdapter = aiAdapter;
    }

    public ArrayList<Command> getCommands() {
        ArrayList<Command> commands = new ArrayList<>();
        var hints = aiAdapter.recommendations();
        for (var hint : hints) {
            if (hint.getAction() == Action.SHOOT) {
                continue;
            }
            switch (hint.getAction()) {
                case MOVE_EAST -> commands.add(
                        new MoveCommand(Direction.RIGHT, hint.getTank(), level, deltaTime.getValue(), movementSpeed
                        ));
                case MOVE_NORTH -> commands.add(
                        new MoveCommand(Direction.UP, hint.getTank(), level, deltaTime.getValue(), movementSpeed
                        ));
                case MOVE_SOUTH -> commands.add(
                        new MoveCommand(Direction.DOWN, hint.getTank(), level, deltaTime.getValue(), movementSpeed
                        ));
                case MOVE_WEST -> commands.add(
                        new MoveCommand(Direction.LEFT, hint.getTank(), level, deltaTime.getValue(), movementSpeed
                        ));
            }
        }
        return commands;
    }
}
