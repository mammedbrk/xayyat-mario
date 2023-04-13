package com.mammedbrk.model.gamecomponent.enemy;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mammedbrk.model.gamecomponent.Tile;
import com.mammedbrk.model.gamecomponent.block.CoinBlock;
import com.mammedbrk.model.gamecomponent.block.OrdinaryBlock;
import com.mammedbrk.model.gamecomponent.block.PowerUpBlock;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Grave.class, name = "grave")
})
public abstract class Enemy extends Tile {
    public Enemy() {
    }

    public Enemy(int x, int y, String imageAddress) {
        super(x, y, imageAddress);
    }

    public void move() {

    }
}
