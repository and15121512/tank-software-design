package ru.mipt.bit.platformer.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.Obstacle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class ObstacleImpl implements Obstacle {
    private final TextureRegion graphics;
    private final GridPoint2 coordinates;
    private final Rectangle rectangle;

    public ObstacleImpl(Texture texture, GridPoint2 coordinates, TiledMapTileLayer groundLayer) {
        this.graphics = new TextureRegion(texture);
        this.coordinates = new GridPoint2(1, 3);
        this.rectangle = createBoundingRectangle(this.graphics);
        moveRectangleAtTileCenter(groundLayer,  this.rectangle,  this.coordinates);
    }

    public void draw(Batch batch) {
        drawTextureRegionUnscaled(batch, this.graphics, this.rectangle, 0f);
    }

    public GridPoint2 getCoordinates() {
        return this.coordinates;
    }
}
