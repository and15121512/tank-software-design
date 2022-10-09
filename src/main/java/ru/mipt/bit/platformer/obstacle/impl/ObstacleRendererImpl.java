package ru.mipt.bit.platformer.obstacle.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.obstacle.Obstacle;
import ru.mipt.bit.platformer.obstacle.ObstacleRenderer;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class ObstacleRendererImpl implements ObstacleRenderer {
    private final TextureRegion graphics;
    private final Rectangle rectangle;

    public ObstacleRendererImpl(Texture texture, TiledMapTileLayer groundLayer, Obstacle obstacle) {
        this.graphics = new TextureRegion(texture);
        this.rectangle = createBoundingRectangle(this.graphics);
        moveRectangleAtTileCenter(groundLayer,  this.rectangle,
                new GridPoint2(obstacle.getCoordinates().x, obstacle.getCoordinates().y));
    }

    public void draw(Batch batch) {
        drawTextureRegionUnscaled(batch, this.graphics, this.rectangle, 0f);
    }
}