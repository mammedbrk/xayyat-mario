package com.mammedbrk.view.game;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class TileImages {
    private final HashMap<String, Image> images;

    public TileImages() {
        images = new HashMap<>();
    }

    public Image getImage(String address) {
        Image image = images.get(address);
        if (image == null) {
            try {
                image = new Image(new FileInputStream(address));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return image;
    }

    public HashMap<String, Image> getImages() {
        return images;
    }
}
