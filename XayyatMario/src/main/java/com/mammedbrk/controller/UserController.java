package com.mammedbrk.controller;

import com.mammedbrk.access.CharacterAccess;
import com.mammedbrk.access.UserAccess;
import com.mammedbrk.current.Current;
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
        user.addCharacter(character);
        access.add(user);
        return Current.user = user;
    }

    public User login(FormEvent e) {
        User user = access.get(e.getUsername());
        if (user != null && user.getPassword().equals(e.getPassword())) {
            return Current.user = user;
        }
        return null;
    }

    public List<User> getSortedUsersList() {
        List<User> users = access.getAll();
        users.sort(Comparator.comparing(User::getMaxScore).reversed());
        return users;
    }

    public boolean buyCharacter(User user, Character character) {
        if (user.getNumOfCoins() < character.getPrice()) {
            return false;
        }
        user.changeNumOfCoinsBy(character.getPrice() * -1);
        user.addCharacter(character);
        access.add(user);
        Current.user = user;
        return true;
    }
}
