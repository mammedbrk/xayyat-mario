package com.mammedbrk.controller.collision;

import com.mammedbrk.controller.CollisionSystem;
import com.mammedbrk.model.component.Mario;
import com.mammedbrk.model.component.block.Block;
import com.mammedbrk.model.component.enemy.Enemy;
import com.mammedbrk.model.component.item.Item;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Hittable;
import com.mammedbrk.model.interfaces.Movable;

import java.util.List;

public class EnemyCollisionVisitor implements CollisionVisitor {
    private final CollisionSystem collisionSystem = new CollisionSystem();
    private List<Enemy> enemies;

    @Override
    public void visit(Mario mario) {
        for (Enemy enemy: enemies) {
            switch (collisionSystem.checkCollision(mario, enemy)) {
                case bottom_to_top -> {
                    if (enemy instanceof Hittable)
                        ((Hittable) enemy).hit();
                    else {
                        // todo kill
                    }
                }
                case top_to_bottom, right_to_left, left_to_right -> {
                    // todo kill
                }
            }
        }
    }

    @Override
    public void visit(Enemy enemy) {
        for (Enemy other: enemies) {
            switch (collisionSystem.checkCollision(enemy, other)) {
                case bottom_to_top -> {
                    if (other instanceof Hittable)
                        ((Hittable) other).hit();
                }
                case right_to_left -> {
                    if (enemy instanceof Movable) {
                        ((Movable) other).changeDirection();
                        ((Movable) enemy).changeDirection();
                        other.setX(enemy.getX() + 1.);
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

    public void setBlocks(List<Enemy> enemies) {
        this.enemies = enemies;
    }
}
