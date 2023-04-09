package com.mammedbrk.controller;

import com.mammedbrk.access.CharacterAccess;
import com.mammedbrk.model.Character;

import java.util.List;

public class CharacterController {
    private final CharacterAccess access = new CharacterAccess();

    public List<Character> getAll() {
        return access.getAll();
    }
}
