package com.mammedbrk.model.gamecomponent.block;

public class CoinBlock extends Block {
    private int value;

    public CoinBlock(int x, int y) {
        super(x, y, "src/main/resources/com.mammedbrk/image/game/coin.png");
        this.value = 1;
    }

    public CoinBlock(int x, int y, int value) {
        this(x, y);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
