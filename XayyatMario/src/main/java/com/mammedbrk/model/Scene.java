package com.mammedbrk.model;

import com.mammedbrk.model.gamecomponent.tile.Tile;

import java.util.List;

public class Scene {
    private List<Tile> tiles;

    public Scene(List<Tile> tiles) {
        this.tiles = tiles;
    }

    // Methods

    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    // Getters and setters

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }
}
