package com.mammedbrk.controller.collision;

import com.mammedbrk.controller.CollisionSystem;
import com.mammedbrk.model.component.Mario;
import com.mammedbrk.model.component.enemy.Enemy;
import com.mammedbrk.model.component.enemy.Piranha;
import com.mammedbrk.model.component.item.Item;
import com.mammedbrk.model.component.pipe.Pipe;
import com.mammedbrk.model.component.pipe.TelePipe;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Movable;

import java.util.List;

public class PipeCollisionVisitor implements CollisionVisitor {
    private final CollisionSystem collisionSystem = new CollisionSystem();
    private List<Pipe> pipes;

    @Override
    public void visit(Mario mario) {
        for (Pipe pipe: pipes) {
            switch (collisionSystem.checkCollision(mario, pipe)) {
                case bottom_to_top -> {
                    mario.setY(pipe.getY() + 1.);
                    mario.resetGravity();
                    if (pipe instanceof Piranha
                            && ((Piranha) pipe).isAlive() && ((Piranha) pipe).isShown()) {
                        // todo kill
                    }
                    if (pipe instanceof TelePipe) {
                        // todo tele scene
                    }
                }
                case right_to_left -> mario.setX(pipe.getX() - 1);
                case left_to_right -> mario.setX(pipe.getX() + 1);
            }
        }
    }

    @Override
    public void visit(Enemy enemy) {
        for (Pipe pipe: pipes) {
            switch (collisionSystem.checkCollision(enemy, pipe)) {
                case bottom_to_top -> {
                    if (enemy instanceof Gravitational) {
                        enemy.setY(pipe.getY() + 1.);
                        ((Gravitational) enemy).resetGravity();
                    }
                    if (pipe instanceof Piranha
                            && ((Piranha) pipe).isAlive() && ((Piranha) pipe).isShown()) {
                        // todo kill
                    }
                }
                case left_to_right -> {
                    if (enemy instanceof Movable) {
                        ((Movable) enemy).changeDirection();
                        enemy.setX(pipe.getX() + 1.);
                    }
                }
                case right_to_left -> {
                    if (enemy instanceof Movable) {
                        ((Movable) enemy).changeDirection();
                        enemy.setX(pipe.getX() - 1.);
                    }
                }
            }
        }
    }

    @Override
    public void visit(Item item) {
        for (Pipe pipe: pipes) {
            switch (collisionSystem.checkCollision(item, pipe)) {
                case bottom_to_top -> {
                    if (item instanceof Gravitational) {
                        item.setY(pipe.getY() + 1.);
                        ((Gravitational) item).resetGravity();
                    }
                }
                case left_to_right -> {
                    if (item instanceof Movable) {
                        ((Movable) item).changeDirection();
                        item.setX(pipe.getX() + 1.);
                    }
                }
                case right_to_left -> {
                    if (item instanceof Movable) {
                        ((Movable) item).changeDirection();
                        item.setX(pipe.getX() - 1.);
                    }
                }
                case top_to_bottom -> {
                    if (item instanceof Gravitational) {
                        item.setY(pipe.getY() - 1.);
                        ((Gravitational) item).resetGravity();
                    }
                }
            }
        }
    }

    public void setPipes(List<Pipe> pipes) {
        this.pipes = pipes;
    }
}
