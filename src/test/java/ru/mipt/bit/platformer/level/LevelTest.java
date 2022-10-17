package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.math.MathUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.gridpoint.GridPoint;
import ru.mipt.bit.platformer.level.impl.LevelImpl;
import ru.mipt.bit.platformer.obstacle.Obstacle;
import ru.mipt.bit.platformer.obstacle.impl.ObstacleImpl;
import ru.mipt.bit.platformer.tank.Tank;
import ru.mipt.bit.platformer.tank.impl.TankImpl;
import ru.mipt.bit.platformer.util.GdxGameUtils;

import java.util.ArrayList;

public class LevelTest {
    @Test
    void test_LevelIsOccupied() {
        ArrayList<Tank> tanks = new ArrayList<>();
        tanks.add(new TankImpl(
                new GridPoint(1, 1),
                Direction.RIGHT,
                GdxGameUtils::continueProgress,
                MathUtils::isEqual
        ));
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new ObstacleImpl(new GridPoint(0, 1)));
        obstacles.add(new ObstacleImpl(new GridPoint(2, 2)));
        obstacles.add(new ObstacleImpl(new GridPoint(2, 0)));

        Level level = new LevelImpl(tanks, obstacles, 3, 3);
        level.getTanks().get(0).move(Direction.UP, 1f, 0.5f);

        Assertions.assertTrue(level.isOccupied(new GridPoint(0, 1)));
        Assertions.assertTrue(level.isOccupied(new GridPoint(2, 2)));
        Assertions.assertTrue(level.isOccupied(new GridPoint(2, 0)));

        Assertions.assertFalse(level.isOccupied(new GridPoint(0, 0)));
        Assertions.assertFalse(level.isOccupied(new GridPoint(1, 1)));
        Assertions.assertFalse(level.isOccupied(new GridPoint(2, 1)));
    }
}
