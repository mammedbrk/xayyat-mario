package com.mammedbrk.event;

import com.mammedbrk.view.game.GameView;

import java.util.EventObject;

public class GameLoadEvent extends EventObject {
    private final GameView gameView;

    public GameLoadEvent(Object source, GameView gameView) {
        super(source);
        this.gameView = gameView;
    }

    public GameView getGameView() {
        return gameView;
    }
}
