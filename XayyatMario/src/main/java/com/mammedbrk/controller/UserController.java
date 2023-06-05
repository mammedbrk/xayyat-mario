package com.mammedbrk.controller;

import com.mammedbrk.access.CharacterAccess;
import com.mammedbrk.access.GameAccess;
import com.mammedbrk.access.LevelAccess;
import com.mammedbrk.access.UserAccess;
import com.mammedbrk.current.Current;
import com.mammedbrk.event.FormEvent;
import com.mammedbrk.model.*;
import com.mammedbrk.model.Character;
import com.mammedbrk.model.game.Game;
import com.mammedbrk.model.game.Level;

import java.util.ArrayList;
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

        for (int i = 0; i < 3; i++)
            user.addGame(new Game());

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
        users.sort(Comparator.comparing(User::getScore).reversed());
        return users;
    }

    public boolean buyCharacter(User user, Character character) {
        if (user.getCoins() < character.getPrice()) {
            return false;
        }
        user.addCoin(character.getPrice() * -1);
        user.addCharacter(character);
        access.add(user);
        Current.user = user;
        return true;
    }

    public void updateUser(User user) {
        access.add(user);
    }

    public void saveNewGame(User user, int index) {
        LevelAccess levelAccess = new LevelAccess();

        Game game = new Game();
        Level level;
        for (int levelNo = 0; (level = levelAccess.get(levelNo)) != null; levelNo++)
            game.addLevel(level);
        user.putGame(index, game);

        access.add(user);
    }
}
