package com.mammedbrk.listener;

import com.mammedbrk.controller.GameController;
import javafx.scene.image.ImageView;

import java.util.List;

public class SectionLoadListener {
    private GameController controller;

    public List<ImageView> listen() {
        return controller.loadNextSection();
    }

    public GameController getController() {
        return controller;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }
}
