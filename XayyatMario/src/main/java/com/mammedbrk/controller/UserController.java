package com.mammedbrk.controller;

import com.mammedbrk.access.CharacterAccess;
import com.mammedbrk.access.UserAccess;
import com.mammedbrk.event.FormEvent;
import com.mammedbrk.model.Character;
import com.mammedbrk.model.User;

import java.util.Comparator;
import java.util.List;

public class UserController {
    private final UserAccess access = new UserAccess();

    public User register(FormEvent e) {
        if (access.get(e.getUsername()) != null) {
            return null;
        }
        User user = new User(access.lastId() + 1, e.getUsername(), e.getPassword());
        Character character = new CharacterAccess().get("OrdinaryXayyat");
        Character newCharacter = new Character(character.getName(), character.getImageAddress(), character.getSpeed(), character.getJumpAbility(), character.getCollectAbility(), character.getShootAbility(), character.getPrice());
        newCharacter.setChosen(true);
        user.addCharacter(newCharacter);
        access.add(user);
        return user;
    }

    public User login(FormEvent e) {
        User user = access.get(e.getUsername());
        if (user != null && user.getPassword().equals(e.getPassword())) {
            return user;
        }
        return null;
    }

    public List<User> getSortedUsersList() {
        List<User> users = access.getAll();
        users.sort(Comparator.comparing(User::getMaxScore).reversed());
        return users;
    }

    public void buyCharacter(User user, Character character) {
        Character newCharacter = new Character(character.getName(), character.getImageAddress(), character.getSpeed(), character.getJumpAbility(), character.getCollectAbility(), character.getShootAbility(), character.getPrice());
        newCharacter.setChosen(true);
        user.addCharacter(newCharacter);
        access.add(user);
    }
}
