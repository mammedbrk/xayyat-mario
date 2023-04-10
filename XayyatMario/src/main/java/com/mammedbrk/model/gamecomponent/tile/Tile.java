package com.mammedbrk.model.gamecomponent.tile;

public abstract class Tile {
    public static final int TILE_SIZE = 50;
    protected final int x, y;
    protected final String imageAddress;

    public Tile(int x, int y, String imageAddress) {
        this.x = x;
        this.y = y;
        this.imageAddress = imageAddress;
    }

    public double getScaleX() {
        return 1;
    }

    public double getScaleY() {
        return 1;
    }

    // Getters and setters

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getImageAddress() {
        return imageAddress;
    }
}
