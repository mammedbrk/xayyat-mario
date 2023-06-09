package com.mammedbrk.controller.collision;

import com.mammedbrk.model.component.Component;

public interface CollisionHandler {
    void handle(Component component1, Component component2);
}
