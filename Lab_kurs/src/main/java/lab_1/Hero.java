package lab_1;

import lab_1.moving_strategies.IMovingStrategy;

import java.awt.*;

public class Hero {
    private final String name;

    private final Point location;

    private IMovingStrategy movingStrategy;

    public Hero(String name, IMovingStrategy movingStrategy) {
        this.name = name;
        this.location = new Point(0, 0);
        this.movingStrategy = movingStrategy;
    }

    @Override
    public String toString() {
        return "Hero name: " + name + ". Location: " + location.getX() + ", " + location.getY() + ". Moving strategy: " + movingStrategy.getClass().getSimpleName();
    }

    public IMovingStrategy getMovingStrategy() {
        return movingStrategy;
    }

    public void setMovingStrategy(IMovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    void move() {
        this.movingStrategy.move(this.location);
    }
}