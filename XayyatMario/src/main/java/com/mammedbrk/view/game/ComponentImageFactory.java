package com.mammedbrk.view.game;

import com.mammedbrk.config.Config;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class ComponentImageFactory {
    private final static HashMap<String, Image> images = new HashMap<>();
    private static String dir = Config.getInstance().getProperty("component_image_path");

    public static Image getImage(String name) {
        Image result = images.get(name);
        if (result == null) {
            try {
                result = new Image(new FileInputStream(dir + name + ".png"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            images.put(name, result);
        }
        return result;
    }

    public static void setDir(String dir) {
        ComponentImageFactory.dir = dir;
    }
}
