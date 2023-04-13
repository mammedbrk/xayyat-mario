package com.mammedbrk.listener;

import com.mammedbrk.controller.CharacterController;
import com.mammedbrk.event.CharacterListEvent;

public class CharacterListListener {
    private final CharacterController controller = new CharacterController();

    public boolean listen(CharacterListEvent characterListEvent) {
        characterListEvent.getCharacters().addAll(controller.getAll());
        return false;
    }
}
