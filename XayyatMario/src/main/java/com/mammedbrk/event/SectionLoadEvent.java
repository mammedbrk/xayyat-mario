package com.mammedbrk.event;

import com.mammedbrk.view.game.GameView;

import java.util.EventObject;

public class SectionLoadEvent extends EventObject {
    private final GameView gameView;

    public SectionLoadEvent(Object source, GameView gameView) {
        super(source);
        this.gameView = gameView;
    }

    public GameView getGameView() {
        return gameView;
    }
}
