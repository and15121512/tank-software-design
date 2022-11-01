package ru.mipt.bit.platformer.tank.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.gridpoint.GridPoint;
import ru.mipt.bit.platformer.tank.Tank;
import ru.mipt.bit.platformer.tank.TankRenderer;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class TankRendererImpl implements TankRenderer {
    private final TextureRegion graphics;
    private final Rectangle rectangle;
    private final TileMovement tileMovement;
    private final Tank tank;

    public TankRendererImpl(
            Texture texture,
            TileMovement tileMovement,
            Tank tank
    ) {
        this.graphics = new TextureRegion(texture);
        this.rectangle = createBoundingRectangle(graphics);
        this.tileMovement = tileMovement;
        this.tank = tank;
    }

    public void draw(Batch batch) {
        calculateRectanglePosition(this.tank);
        drawTextureRegionUnscaled(batch, this.graphics, this.rectangle, tank.getCurrentDirection().getAngle());
    }

    private void calculateRectanglePosition(Tank tank) {
        GridPoint departureCoordinates = tank.getDepartureCoordinates();
        GridPoint destinationCoordinates = tank.getDestinationCoordinates();
        this.tileMovement.moveRectangleBetweenTileCenters(
                this.rectangle,
                new GridPoint2(departureCoordinates.x, departureCoordinates.y),
                new GridPoint2(destinationCoordinates.x, destinationCoordinates.y),
                tank.getMovementProgress()
        );
    }
}
