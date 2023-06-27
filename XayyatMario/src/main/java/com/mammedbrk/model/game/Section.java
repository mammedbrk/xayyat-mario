package com.mammedbrk.model.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.config.Config;
import com.mammedbrk.model.component.Checkpoint;
import com.mammedbrk.model.component.Component;
import com.mammedbrk.model.component.block.Block;
import com.mammedbrk.model.component.enemy.Enemy;
import com.mammedbrk.model.component.item.Item;
import com.mammedbrk.model.component.pipe.Pipe;

import java.util.ArrayList;
import java.util.List;

public class Section {
    @JsonIgnore
    private static int HEIGHT = Integer.parseInt(Config.getInstance().getProperty("height"));;
    private int length;
    private int time;
    private List<Block> blocks;
    private List<Enemy> enemies;
    private List<Pipe> pipes;
    private List<Item> items;
    private List<Checkpoint> checkpoints;
    private Pipe spawnPipe;

    public Section() {
        this.blocks = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.pipes = new ArrayList<>();
        this.items = new ArrayList<>();
        this.checkpoints = new ArrayList<>();
    }

    public Section(int length, int time) {
        this();
        this.length = length;
        this.time = time;
    }

    public Section(int length, int time, List<Block> blocks, List<Enemy> enemies, List<Pipe> pipes) {
        this.length = length;
        this.time = time;
        this.blocks = blocks;
        this.enemies = enemies;
        this.pipes = pipes;
        this.items = new ArrayList<>();
        this.checkpoints = new ArrayList<>();
    }

    public Section(int length, int time, List<Block> blocks, List<Enemy> enemies, List<Pipe> pipes, Pipe spawnPipe) {
        this.length = length;
        this.time = time;
        this.blocks = blocks;
        this.enemies = enemies;
        this.pipes = pipes;
        this.spawnPipe = spawnPipe;
        this.items = new ArrayList<>();
        this.checkpoints = new ArrayList<>();
    }

    public Section(int length, int time, List<Block> blocks, List<Enemy> enemies, List<Pipe> pipes, List<Item> items, List<Checkpoint> checkpoints, Pipe spawnPipe) {
        this.length = length;
        this.time = time;
        this.blocks = blocks;
        this.enemies = enemies;
        this.pipes = pipes;
        this.items = items;
        this.checkpoints = checkpoints;
        this.spawnPipe = spawnPipe;
    }

    public void addComponent(Component component) {
        if (component instanceof Block)
            blocks.add((Block) component);
        else if (component instanceof Enemy)
            enemies.add((Enemy) component);
        else if (component instanceof Pipe)
            pipes.add((Pipe) component);
        else if (component instanceof Item)
            items.add((Item) component);
    }

    public void removeComponent(Component component) {
        if (component instanceof Block)
            blocks.remove((Block) component);
        else if (component instanceof Enemy)
            enemies.remove((Enemy) component);
        else if (component instanceof Pipe)
            pipes.remove((Pipe) component);
        else if (component instanceof Item)
            items.remove((Item) component);
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public List<Pipe> getPipes() {
        return pipes;
    }

    public void setPipes(List<Pipe> pipes) {
        this.pipes = pipes;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Pipe getSpawnPipe() {
        return spawnPipe;
    }

    public void setSpawnPipe(Pipe spawnPipe) {
        this.spawnPipe = spawnPipe;
    }

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(List<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }

    public static void setHEIGHT(int HEIGHT) {
        Section.HEIGHT = HEIGHT;
    }
}
