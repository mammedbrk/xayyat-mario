package com.mammedbrk.model.component.block;

import com.mammedbrk.model.component.item.ItemType;

public class QuestionBlock extends Block {
    private ItemType item;

    public QuestionBlock() {
    }

    public QuestionBlock(int x, int y, ItemType item) {
        super(x, y);
        this.item = item;
    }

    // Getters and setters

    public ItemType getItem() {
        return item;
    }

    public void setItem(ItemType item) {
        this.item = item;
    }
}
