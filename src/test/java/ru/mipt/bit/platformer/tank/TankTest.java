package ru.mipt.bit.platformer.tank;

import com.badlogic.gdx.math.MathUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.gridpoint.GridPoint;
import ru.mipt.bit.platformer.tank.impl.TankImpl;
import ru.mipt.bit.platformer.util.GdxGameUtils;

public class TankTest {
    @Test
    void test_TankMove() {
        float epsilon = 1e-6f;
        float testDeltaTime = 0.4f;
        float testMovementSpeed = 1f;
        Tank tank = new TankImpl(
                new GridPoint(0, 0),
                Direction.RIGHT,
                GdxGameUtils::continueProgress,
                MathUtils::isEqual
        );
        Assertions.assertEquals(new GridPoint(0, 0), tank.getDepartureCoordinates());
        Assertions.assertEquals(new GridPoint(0, 0), tank.getDestinationCoordinates());
        Assertions.assertEquals(Direction.RIGHT, tank.getCurrentDirection());
        Assertions.assertEquals(1f, tank.getMovementProgress(), 1e-10);

        tank.move(Direction.DOWN, testDeltaTime, testMovementSpeed);

        Assertions.assertEquals(new GridPoint(0, 0), tank.getDepartureCoordinates());
        Assertions.assertEquals(new GridPoint(0, -1), tank.getDestinationCoordinates());
        Assertions.assertEquals(Direction.DOWN, tank.getCurrentDirection());
        Assertions.assertEquals(1 * testDeltaTime / testMovementSpeed, tank.getMovementProgress(), epsilon);

        tank.move(Direction.UP, testDeltaTime, testMovementSpeed);

        Assertions.assertEquals(new GridPoint(0, 0), tank.getDepartureCoordinates());
        Assertions.assertEquals(new GridPoint(0, -1), tank.getDestinationCoordinates());
        Assertions.assertEquals(Direction.DOWN, tank.getCurrentDirection());
        Assertions.assertEquals(2 * testDeltaTime / testMovementSpeed, tank.getMovementProgress(), epsilon);

        tank.move(Direction.DOWN, testDeltaTime, testMovementSpeed);

        Assertions.assertEquals(new GridPoint(0, -1), tank.getDepartureCoordinates());
        Assertions.assertEquals(new GridPoint(0, -1), tank.getDestinationCoordinates());
        Assertions.assertEquals(Direction.DOWN, tank.getCurrentDirection());
        Assertions.assertEquals(1f, tank.getMovementProgress(), epsilon);
    }
}
