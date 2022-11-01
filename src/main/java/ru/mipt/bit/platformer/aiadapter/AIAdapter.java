package ru.mipt.bit.platformer.aiadapter;

import ru.mipt.bit.platformer.level.Level;

import java.util.ArrayList;

public interface AIAdapter {
    ArrayList<Hint> recommendations();
}
