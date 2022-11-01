package ru.mipt.bit.platformer.obstacle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.gridpoint.GridPoint;
import ru.mipt.bit.platformer.obstacle.impl.ObstacleImpl;

public class ObstacleTest {
    @Test
    void test_ObstacleGetCoordinates() {
        Obstacle obstacle = new ObstacleImpl(
                new GridPoint(1, 1)
        );
        Assertions.assertEquals(new GridPoint(1, 1), obstacle.getCoordinates());
    }
}
