package com.mammedbrk.event;

import com.mammedbrk.model.Character;

import java.util.EventObject;

public class CharacterEvent extends EventObject {
    private Character character;

    public CharacterEvent(Object source, Character character) {
        super(source);
        this.character = character;
    }

    // Getters and setters

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
