package com.mammedbrk.listener;

import com.mammedbrk.controller.GameController;
import com.mammedbrk.current.Current;
import javafx.scene.image.ImageView;

import java.util.List;

public class SectionLoadListener {
    private final GameController controller = Current.controller;

    public List<ImageView> listen() {
        return controller.loadNextSection();
    }
}
