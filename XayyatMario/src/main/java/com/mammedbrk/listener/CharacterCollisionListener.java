package com.mammedbrk.listener;

import com.mammedbrk.controller.GameController;
import com.mammedbrk.event.CharacterCollisionEvent;
import com.mammedbrk.event.CharacterMovementEvent;

public class CharacterCollisionListener {
    private GameController controller;

    public CharacterMovementEvent listen(CharacterCollisionEvent characterMovementEvent) { // todo return movement things
        return controller.moveAndCollision(characterMovementEvent);
    }

    public GameController getController() {
        return controller;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }
}
