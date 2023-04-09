package com.mammedbrk.event;

import com.mammedbrk.model.Character;

import java.util.EventObject;
import java.util.List;

public class CharacterListEvent extends EventObject {
    private List<Character> characters;

    public CharacterListEvent(Object source, List<Character> characters) {
        super(source);
        this.characters = characters;
    }

    // Getters and setters

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}
