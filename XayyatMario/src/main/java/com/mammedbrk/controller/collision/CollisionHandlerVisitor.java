package com.mammedbrk.controller.collision;

import com.mammedbrk.model.component.Checkpoint;
import com.mammedbrk.model.component.Mario;
import com.mammedbrk.model.component.block.Block;
import com.mammedbrk.model.component.enemy.Enemy;
import com.mammedbrk.model.component.item.Item;
import com.mammedbrk.model.component.pipe.Pipe;

public class CollisionHandlerVisitor implements CollisionVisitor {
    private CollisionHandler collisionHandler;

    public CollisionHandlerVisitor() {
    }

    public CollisionHandlerVisitor(CollisionHandler collisionHandler) {
        this.collisionHandler = collisionHandler;
    }

    @Override
    public void visit(Mario mario, Block block) {
        collisionHandler.handle(mario, block);
    }

    @Override
    public void visit(Mario mario, Pipe pipe) {
        collisionHandler.handle(mario, pipe);
    }

    @Override
    public void visit(Mario mario, Checkpoint checkpoint) {
        collisionHandler.handle(mario, checkpoint);
    }

    @Override
    public void visit(Mario mario, Enemy enemy) {
        collisionHandler.handle(mario, enemy);
    }

    @Override
    public void visit(Mario mario, Item item) {
        collisionHandler.handle(mario, item);
    }

    @Override
    public void visit(Enemy enemy, Block block) {
        collisionHandler.handle(enemy, block);
    }

    @Override
    public void visit(Enemy enemy, Pipe pipe) {
        collisionHandler.handle(enemy, pipe);
    }

    @Override
    public void visit(Enemy enemy1, Enemy enemy2) {
        collisionHandler.handle(enemy1, enemy2);
    }

    @Override
    public void visit(Item item, Block block) {
        collisionHandler.handle(item, block);
    }

    @Override
    public void visit(Item item, Pipe pipe) {
        collisionHandler.handle(item, pipe);
    }

    public void setCollisionHandler(CollisionHandler collisionHandler) {
        this.collisionHandler = collisionHandler;
    }
}
