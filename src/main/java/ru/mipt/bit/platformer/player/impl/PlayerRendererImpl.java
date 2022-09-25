package ru.mipt.bit.platformer.player.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.gridpoint.GridPoint;
import ru.mipt.bit.platformer.player.Player;
import ru.mipt.bit.platformer.player.PlayerRenderer;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class PlayerRendererImpl implements PlayerRenderer {
    private final TextureRegion graphics;
    private final Rectangle rectangle;
    private final TileMovement tileMovement;

    public PlayerRendererImpl(
            Texture texture,
            TileMovement tileMovement
    ) {
        this.graphics = new TextureRegion(texture);
        this.rectangle = createBoundingRectangle(graphics);
        this.tileMovement = tileMovement;
    }

    public void draw(Batch batch, Player player) {
        calculateRectanglePosition(player);
        drawTextureRegionUnscaled(batch, this.graphics, this.rectangle, player.getCurrentDirection().getAngle());
    }

    private void calculateRectanglePosition(Player player) {
        GridPoint departureCoordinates = player.getDepartureCoordinates();
        GridPoint destinationCoordinates = player.getDestinationCoordinates();
        this.tileMovement.moveRectangleBetweenTileCenters(
                this.rectangle,
                new GridPoint2(departureCoordinates.x, departureCoordinates.y),
                new GridPoint2(destinationCoordinates.x, destinationCoordinates.y),
                player.getMovementProgress()
        );
    }
}
