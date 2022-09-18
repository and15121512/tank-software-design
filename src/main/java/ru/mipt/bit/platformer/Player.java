package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;

public interface Player {
    void move(
            Direction direction,
            float deltaTime,
            float movementSpeed,
            Obstacle obstacle
    );
    void draw(Batch batch);
}
