package com.mammedbrk.model.gamecomponent.enemy;

import com.mammedbrk.model.gamecomponent.GameComponent;

public abstract class Enemy extends GameComponent {
    public Enemy(int x, int y, String imageAddress) {
        super(x, y, imageAddress);
    }

    public void move() {

    }
}
