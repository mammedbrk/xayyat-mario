package com.mammedbrk.controller;

import com.mammedbrk.access.CharacterAccess;
import com.mammedbrk.access.SceneAccess;
import com.mammedbrk.access.UserAccess;
import com.mammedbrk.current.Current;
import com.mammedbrk.event.FormEvent;
import com.mammedbrk.model.*;
import com.mammedbrk.model.Character;

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
        user.setChosenCharacter(character);
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
        users.sort(Comparator.comparing(User::getMaxScore).reversed());
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

    public void chooseCharacter(User user, Character character) {
        user.setChosenCharacter(character);
        access.add(user);
    }

    public void updateUser(User user) {
        access.add(user);
    }

    public void saveNewGame(User user, int index) {
        Section section = new Section(120);
        section.setNo(1);
        section.setScenes(new ArrayList<>());
        for (int sceneNo = 1; ; sceneNo++) {
            Scene scene = new SceneAccess().get(1, 1, sceneNo);
            if (scene == null) break;
            section.addScene(scene);
        }
        section.setX(2);
        section.setY(5);
        Level level = new Level();
        level.setNo(1);
        level.setCurrentSection(section);
        Game game = new Game();
        game.setCharacter(Current.user.getChosenCharacter());
        game.setHearts(3);
        game.setCurrentLevel(level);
        user.putGame(index, game);
        user.setCurrentGame(game);
        access.add(user);
    }
}
