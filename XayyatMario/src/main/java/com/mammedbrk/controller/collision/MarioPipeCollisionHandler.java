package com.mammedbrk.controller.collision;

import com.mammedbrk.model.component.Component;
import com.mammedbrk.model.component.enemy.Piranha;
import com.mammedbrk.model.component.pipe.TelePipe;
import com.mammedbrk.model.interfaces.Gravitational;

public class MarioPipeCollisionHandler implements CollisionHandler {
    private CollisionSystem collisionSystem = new CollisionSystem();

    @Override
    public void handle(Component mario, Component pipe) {
        switch (collisionSystem.checkCollision(mario, pipe)) {
            case bottom_to_top -> {
                mario.setY(pipe.getY() + 1.);
                ((Gravitational) mario).resetGravity();
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
