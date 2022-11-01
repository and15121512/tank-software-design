package ru.mipt.bit.platformer.command.generator;

import ru.mipt.bit.platformer.command.Command;

import java.util.ArrayList;

public interface CommandGenerator {
    ArrayList<Command> getCommands();
}
