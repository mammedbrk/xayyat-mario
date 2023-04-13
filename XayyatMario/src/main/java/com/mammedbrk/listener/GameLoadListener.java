package com.mammedbrk.listener;

import com.mammedbrk.controller.GameController;
import com.mammedbrk.event.GameLoadEvent;
import javafx.scene.image.ImageView;

import java.util.List;

public class GameLoadListener implements Listener<GameLoadEvent> {
    private final GameController controller = new GameController();


    @Override
    public boolean listen(GameLoadEvent gameEvent) {
        List<ImageView> gTiles = controller.loadNextSection();
        gameEvent.getGameView().getChildren().clear();
        if (gTiles == null) return false;
        for (ImageView gTile: gTiles)
            gameEvent.getGameView().getChildren().add(gTile);
        return true;
    }
}
