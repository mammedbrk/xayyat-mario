package com.mammedbrk.model.component.block;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.model.component.item.ItemType;

public class QuestionBlock extends Block {
    @JsonIgnore
    private boolean hit;
    private ItemType item;

    public QuestionBlock() {
    }

    public QuestionBlock(double x, double y) {
        super(x, y);
    }

    public QuestionBlock(double x, double y, ItemType item) {
        super(x, y);
        this.item = item;
    }

    public ItemType getItem() {
        return item;
    }

    public void setItem(ItemType item) {
        this.item = item;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}
