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

    @Override
    public EmptyBlock changedObject() {
        return new EmptyBlock(x, y);
    }

    @Override
    public void hit() {
        hit = true;
    }

    @Override
    public boolean isHit() {
        return hit;
    }
}
