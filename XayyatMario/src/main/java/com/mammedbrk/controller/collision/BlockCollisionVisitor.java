package com.mammedbrk.controller.collision;

import com.mammedbrk.controller.CollisionSystem;
import com.mammedbrk.model.component.Mario;
import com.mammedbrk.model.component.block.Block;
import com.mammedbrk.model.component.enemy.Enemy;
import com.mammedbrk.model.component.item.Item;
import com.mammedbrk.model.interfaces.Changeable;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Hittable;
import com.mammedbrk.model.interfaces.Movable;

import java.util.List;

public class BlockCollisionVisitor implements CollisionVisitor {
    private final CollisionSystem collisionSystem = new CollisionSystem();
    private List<Block> blocks;

    @Override
    public void visit(Mario mario) {
        for (Block block: blocks) {
            switch (collisionSystem.checkCollision(mario, block)) {
                case top_to_bottom -> {
                    mario.setY(block.getY() - 1);
                    mario.resetGravity();
                    if (block instanceof Hittable)
                        ((Hittable) block).hit();
                }
                case bottom_to_top -> {
                    mario.setY(block.getY() + 1.);
                    mario.resetGravity();
                }
                case right_to_left -> mario.setX(block.getX() - 1);
                case left_to_right -> mario.setX(block.getX() + 1);
            }
        }
    }

    @Override
    public void visit(Enemy enemy) {
        for (Block block: blocks) {
            switch (collisionSystem.checkCollision(enemy, block)) {
                case bottom_to_top -> {
                    if (enemy instanceof Gravitational) {
                        enemy.setY(block.getY() + 1.);
                        ((Gravitational) enemy).resetGravity();
                    }
                }
                case left_to_right -> {
                    if (enemy instanceof Movable) {
                        ((Movable) enemy).changeDirection();
                        enemy.setX(block.getX() + 1.);
                    }
                }
                case right_to_left -> {
                    if (enemy instanceof Movable) {
                        ((Movable) enemy).changeDirection();
                        enemy.setX(block.getX() - 1.);
                    }
                }
                case top_to_bottom -> {
                    if (enemy instanceof Gravitational) {
                        enemy.setY(block.getY() - 1.);
                        ((Gravitational) enemy).resetGravity();
                    }
                }
            }
        }
    }

    @Override
    public void visit(Item item) {
        for (Block block: blocks) {
            switch (collisionSystem.checkCollision(item, block)) {
                case bottom_to_top -> {
                    if (item instanceof Gravitational) {
                        item.setY(block.getY() + 1.);
                        ((Gravitational) item).resetGravity();
                    }
                }
                case left_to_right -> {
                    if (item instanceof Movable) {
                        ((Movable) item).changeDirection();
                        item.setX(block.getX() + 1.);
                    }
                }
                case right_to_left -> {
                    if (item instanceof Movable) {
                        ((Movable) item).changeDirection();
                        item.setX(block.getX() - 1.);
                    }
                }
                case top_to_bottom -> {
                    if (item instanceof Gravitational) {
                        item.setY(block.getY() - 1.);
                        ((Gravitational) item).resetGravity();
                    }
                }
            }
        }
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }
}
