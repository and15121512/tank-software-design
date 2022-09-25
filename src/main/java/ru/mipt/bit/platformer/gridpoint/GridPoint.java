package ru.mipt.bit.platformer.gridpoint;

public class GridPoint {
    public int x;
    public int y;

    public GridPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GridPoint(GridPoint other) {
        this.x = other.x;
        this.y = other.y;
    }

    public void set(GridPoint other) {
        this.x = other.x;
        this.y = other.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        GridPoint g = (GridPoint)o;
        return this.x == g.x && this.y == g.y;
    }
}
