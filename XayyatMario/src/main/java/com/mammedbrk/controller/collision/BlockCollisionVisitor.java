package com.mammedbrk.controller.collision;

import com.mammedbrk.controller.CollisionSystem;
import com.mammedbrk.model.component.Mario;
import com.mammedbrk.model.component.block.Block;
import com.mammedbrk.model.component.enemy.Enemy;
import com.mammedbrk.model.component.item.Item;
import com.mammedbrk.model.interfaces.Changeable;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Hittable;

public class BlockCollisionVisitor implements CollisionVisitor<Block> {
    private CollisionSystem collisionSystem = new CollisionSystem();
    @Override
    public void visit(Block block, Mario mario) {
        switch (collisionSystem.checkCollision(block, mario)) {
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

    @Override
    public void visit(Block block, Enemy enemy) {

    }

    @Override
    public void visit(Block block, Item item) {

    }
}
