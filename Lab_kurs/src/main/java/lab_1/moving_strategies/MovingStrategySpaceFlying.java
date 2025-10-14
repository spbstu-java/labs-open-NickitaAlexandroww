package lab_1.moving_strategies;

import java.awt.*;

public class MovingStrategySpaceFlying implements IMovingStrategy {
    @Override
    public void move(Point location) {
        location.setLocation(location.getX() + 1000, location.getY());
    }
}
