package com.mammedbrk.model;

import com.mammedbrk.model.component.block.Block;
import com.mammedbrk.model.component.item.Item;

import java.util.List;

public class Scene {
    private int no;
    private List<Block> blocks;
    private List<Item> items;

    public Scene() {
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }
    public void addItem(Item item) {
        items.add(item);
    }

    // Getters and setters


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
