package com.mammedbrk.listener;

import com.mammedbrk.controller.GameController;
import com.mammedbrk.current.Current;
import com.mammedbrk.event.SectionLoadEvent;
import javafx.scene.image.ImageView;

import java.util.List;

public class SectionLoadListener implements Listener<SectionLoadEvent> {
    private final GameController controller = Current.controller;


    @Override
    public boolean listen(SectionLoadEvent gameEvent) {
        List<ImageView> gTiles = controller.loadNextSection();
        gameEvent.getGameView().getChildren().clear();
        if (gTiles == null) return false;
        for (ImageView gTile: gTiles)
            gameEvent.getGameView().getChildren().add(gTile);
        return true;
    }
}
