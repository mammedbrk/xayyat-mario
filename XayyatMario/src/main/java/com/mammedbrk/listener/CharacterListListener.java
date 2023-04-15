package com.mammedbrk.listener;

import com.mammedbrk.controller.CharacterController;
import com.mammedbrk.model.Character;

import java.util.List;

public class CharacterListListener {
    private final CharacterController controller = new CharacterController();

    public List<Character> listen() {
        return controller.getAll();
    }
}
