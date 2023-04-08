package com.mammedbrk.controller;

import com.mammedbrk.access.CharacterAccess;
import com.mammedbrk.access.UserAccess;
import com.mammedbrk.event.FormEvent;
import com.mammedbrk.model.Character;
import com.mammedbrk.model.User;

import java.util.Comparator;
import java.util.List;

public class UserController {
    UserAccess userAccess = new UserAccess();
    CharacterAccess characterAccess = new CharacterAccess();

    public User register(FormEvent e) {
        if (userAccess.get(e.getUsername()) != null) {
            return null;
        }
        User user = new User(userAccess.lastId() + 1, e.getUsername(), e.getPassword());
        Character character = characterAccess.get("OrdinaryXayyat");
        Character newCharacter = new Character(character.getName(), character.getImageAddress(), character.getSpeed(), character.getJumpAbility(), character.getCollectAbility(), character.getShootAbility());
        newCharacter.setChosen(true);
        user.addCharacter(newCharacter);
        userAccess.add(user);
        return user;
    }

    public User login(FormEvent e) {
        User user = userAccess.get(e.getUsername());
        if (user != null && user.getPassword().equals(e.getPassword())) {
            return user;
        }
        return null;
    }

    public List<User> getSortedUsersList() {
        List<User> users = userAccess.getAll();
        users.sort(Comparator.comparing(User::getMaxScore).reversed());
        return users;
    }
}
