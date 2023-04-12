package com.mammedbrk.model.gamecomponent.block;

import com.mammedbrk.model.gamecomponent.GameComponent;

public abstract class Block extends GameComponent {
    public Block(int x, int y, String imageAddress) {
        super(x, y, imageAddress);
    }
}
