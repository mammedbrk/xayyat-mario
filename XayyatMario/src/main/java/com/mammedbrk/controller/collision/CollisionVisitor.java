package com.mammedbrk.controller.collision;

import com.mammedbrk.model.component.*;
import com.mammedbrk.model.component.block.Block;
import com.mammedbrk.model.component.enemy.Enemy;
import com.mammedbrk.model.component.item.Item;
import com.mammedbrk.model.component.pipe.Pipe;

public interface CollisionVisitor {
    void visit(Mario mario, Block block);
    void visit(Mario mario, Pipe pipe);
    void visit(Mario mario, Checkpoint checkpoint);
    void visit(Mario mario, Enemy enemy);
    void visit(Mario mario, Item item);
    void visit(Enemy enemy, Block block);
    void visit(Enemy enemy, Pipe pipe);
    void visit(Enemy enemy1, Enemy enemy2);
    void visit(Item item, Block block);
    void visit(Item item, Pipe pipe);
}
