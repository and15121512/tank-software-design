package ru.mipt.bit.platformer.command.generator.impl;

import ru.mipt.bit.platformer.command.Command;
import ru.mipt.bit.platformer.command.generator.CommandGenerator;
import ru.mipt.bit.platformer.command.impl.MoveCommand;
import ru.mipt.bit.platformer.deltatime.DeltaTime;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.tank.Tank;

import java.util.ArrayList;
import java.util.List;

public class RandomMoveCommandGenerator implements CommandGenerator {
    List<Tank> tanks;
    Level level;
    DeltaTime deltaTime;
    float movementSpeed;

    public RandomMoveCommandGenerator(List<Tank> tanks, Level level, DeltaTime deltaTime, float movementSpeed) {
        this.tanks = tanks;
        this.level = level;
        this.deltaTime = deltaTime;
        this.movementSpeed = movementSpeed;
    }

    public ArrayList<Command> getCommands() {
        ArrayList<Command> commands = new ArrayList<>();
        var direction = Direction.randomDirection(false);
        for (var tank : tanks) {
            commands.add(new MoveCommand(direction, tank, level, deltaTime.getValue(), movementSpeed));
        }
        return commands;
    }
}
