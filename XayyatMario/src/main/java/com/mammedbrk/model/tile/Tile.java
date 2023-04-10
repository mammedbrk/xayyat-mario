package com.mammedbrk.model.tile;

public abstract class Tile {
    private static final int TILE_SIZE = 40;
    private final int x, y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters and setters

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
