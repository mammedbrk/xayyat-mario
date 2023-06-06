package com.mammedbrk.controller;

import com.mammedbrk.model.component.Component;

public class CollisionSystem {
    public CollisionDirection checkCollision(Component a, Component b) {
        double dy = a.getY() - b.getY();
        double dx = a.getX() - b.getX();
        
        if (Math.abs(dy) < 1. && Math.abs(dx) < 1.) {
            if (Math.abs(dx) <= Math.abs(dy)) {
                if (dy > 0) return CollisionDirection.bottom_to_top;
                else return CollisionDirection.top_to_bottom;
            } else {
                if (dx > 0) return CollisionDirection.left_to_right;
                else return CollisionDirection.right_to_left;
            }
        }
        return CollisionDirection.none;
    }
}
