package ru.mipt.bit.platformer.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.player.Player;

public interface PlayerRenderer {
    void draw(Batch batch, Player player);
}
