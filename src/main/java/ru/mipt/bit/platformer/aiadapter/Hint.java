package ru.mipt.bit.platformer.aiadapter;

import ru.mipt.bit.platformer.tank.Tank;

public class Hint {
    private final Tank tank;
    private final Action action;

    public Hint(Tank tank, Action action) {
        this.tank = tank;
        this.action = action;
    }

    public Tank getTank() {
        return tank;
    }
    public Action getAction() {
        return action;
    }
}
