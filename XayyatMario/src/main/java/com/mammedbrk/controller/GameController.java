package com.mammedbrk.controller;

import com.mammedbrk.access.SectionAccess;
import com.mammedbrk.model.Game;
import com.mammedbrk.model.Level;
import com.mammedbrk.model.Section;
import com.mammedbrk.model.User;

public class GameController {
    SectionAccess access = new SectionAccess();

    public Game createNewGame() {
        Game game = new Game();
        for (int levelNo = 1; levelNo <= 2; levelNo++) {
            Level level = new Level();
            for (int sectionNo = 1; sectionNo <= 4; sectionNo++) {
                Section section = access.get(levelNo, sectionNo);
                if (section == null) continue;
                section.setLevel(level);
                level.addSection(section);
            }
            level.setGame(game);
            game.addLevel(level);
        }
        return game;
    }

    public Game getExistingGame(User user, int index) {
        return user.getGames().get(index);
    }
}
