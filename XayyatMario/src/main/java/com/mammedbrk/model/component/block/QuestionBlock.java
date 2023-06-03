package com.mammedbrk.model.component.block;

import com.mammedbrk.model.component.item.Item;

public class QuestionBlock extends Block {
    private Item item;

    public QuestionBlock() {
    }

    public QuestionBlock(int x, int y, Item item) {
        super(x, y);
        this.item = item;
    }

    // Getters and setters

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
