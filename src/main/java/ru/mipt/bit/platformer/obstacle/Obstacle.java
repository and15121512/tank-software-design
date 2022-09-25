package ru.mipt.bit.platformer.obstacle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.gridpoint.GridPoint;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public interface Obstacle {
    GridPoint getCoordinates();
}
