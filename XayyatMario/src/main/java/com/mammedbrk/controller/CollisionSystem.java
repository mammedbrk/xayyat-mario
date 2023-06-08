package com.mammedbrk.controller;

import com.mammedbrk.model.component.Component;

public class CollisionSystem {
    public CollisionDirection checkCollision(Component a, Component b) {
        double dy = a.getY() - b.getY();
        double dx = a.getX() - b.getX();
        double error = .00001;

        if (Math.abs(dy) < 1. - error && Math.abs(dx) < 1. - error) {
            if (Math.abs(dy) > .5 && Math.abs(dy) > Math.abs(dx)) {
                if (dy > 0) return CollisionDirection.bottom_to_top;
                else if (dy < 0) return CollisionDirection.top_to_bottom;
            } else if (Math.abs(dx) > error){
                if (dx > 0) return CollisionDirection.left_to_right;
                else if (dx < 0) return CollisionDirection.right_to_left;
            }
        }
        return CollisionDirection.none;
    }
}
