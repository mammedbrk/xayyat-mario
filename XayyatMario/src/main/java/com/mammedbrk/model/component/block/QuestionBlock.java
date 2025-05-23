package com.mammedbrk.model.component.block;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.model.component.item.ItemType;
import com.mammedbrk.model.interfaces.Changeable;

public class QuestionBlock extends Block implements Changeable<EmptyBlock> {
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

    @Override
    public EmptyBlock changedObject() {
        return new EmptyBlock(x, y);
    }
}
