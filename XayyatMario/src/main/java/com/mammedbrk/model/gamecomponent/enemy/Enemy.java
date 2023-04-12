package com.mammedbrk.model.gamecomponent.enemy;

import com.mammedbrk.model.gamecomponent.Tile;

public abstract class Enemy extends Tile {
    public Enemy(int x, int y, String imageAddress) {
        super(x, y, imageAddress);
    }

    public void move() {

    }
}
