package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;

public interface Obstacle {
    void draw(Batch batch);
    GridPoint2 getCoordinates();
}
