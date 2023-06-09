package com.mammedbrk.controller.collision;

import com.mammedbrk.controller.CollisionSystem;
import com.mammedbrk.model.component.Component;
import com.mammedbrk.model.component.enemy.Piranha;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Movable;

public class EnemyPipeCollisionHandler implements CollisionHandler {
    private CollisionSystem collisionSystem = new CollisionSystem();

    @Override
    public void handle(Component enemy, Component pipe) {
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
