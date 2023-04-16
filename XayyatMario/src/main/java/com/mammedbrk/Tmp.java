package com.mammedbrk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mammedbrk.access.CharacterAccess;
import com.mammedbrk.access.SceneAccess;
import com.mammedbrk.access.UserAccess;
import com.mammedbrk.controller.UserController;
import com.mammedbrk.current.Current;
import com.mammedbrk.model.*;
import com.mammedbrk.model.Character;
import com.mammedbrk.model.gamecomponent.Coin;
import com.mammedbrk.model.gamecomponent.Tile;
import com.mammedbrk.model.gamecomponent.block.*;
import com.mammedbrk.model.gamecomponent.enemy.Grave;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Tmp {
    private static final int WIDTH = 1300;
    private static final int HEIGHT = 900;
    public static void main(String[] args) throws IOException {
        Scene scene = new Scene();
        scene.setComponents(new ArrayList<>());
        scene.setNo(4);


        scene.addComponent(new Grave(13, 8));
        scene.addComponent(new Grave(14, 8));

        scene.addComponent(new Pipe(13, 9));
        scene.addComponent(new Pipe(14, 9));


        for (int i = 0; i < WIDTH / Tile.TILE_SIZE; i++) {
            scene.addComponent(new EarthBlock(i, 10));
            scene.addComponent(new EarthBlock(i, 11));
//            scene.addComponent(new EarthBlock(i, 12));
//            scene.addComponent(new EarthBlock(i, 13));
        }

        scene.addComponent(new OrdinaryBlock(5, 9));
        scene.addComponent(new OrdinaryBlock(6, 9));
        scene.addComponent(new OrdinaryBlock(6, 8));
        scene.addComponent(new OrdinaryBlock(7, 8));
        scene.addComponent(new OrdinaryBlock(8, 8));
        scene.addComponent(new OrdinaryBlock(9, 8));
        scene.addComponent(new OrdinaryBlock(9, 7));
        scene.addComponent(new OrdinaryBlock(10, 7));
        scene.addComponent(new OrdinaryBlock(11,  7));
        scene.addComponent(new OrdinaryBlock(12, 7));
        scene.addComponent(new OrdinaryBlock(12, 6));
        scene.addComponent(new OrdinaryBlock(13, 6));
        scene.addComponent(new OrdinaryBlock(14, 6));
        scene.addComponent(new OrdinaryBlock(15, 6));
        scene.addComponent(new OrdinaryBlock(15, 7));
        scene.addComponent(new OrdinaryBlock(16, 7));
        scene.addComponent(new OrdinaryBlock(17, 7));
        scene.addComponent(new OrdinaryBlock(18, 7));

        scene.addComponent(new Coin(7, 9));
        scene.addComponent(new Coin(8, 9));
        scene.addComponent(new Coin(9, 9));
        scene.addComponent(new Coin(10, 9));
        scene.addComponent(new Coin(11, 9));
        scene.addComponent(new Coin(12, 9));
        scene.addComponent(new Coin(15, 9));
        scene.addComponent(new Coin(16, 9));
        scene.addComponent(new Coin(17, 9));
        scene.addComponent(new Coin(13, 7));
        scene.addComponent(new Coin(14, 7));
        scene.addComponent(new Coin(9, 5));
        scene.addComponent(new Coin(10, 5));
        scene.addComponent(new Coin(17, 5));
        scene.addComponent(new Coin(18, 5));

        scene.addComponent(new CoinBlock(13, 4, 5));
        scene.addComponent(new CoinBlock(20, 7, 5));

        new SceneAccess().add(scene, 1, 1);
    }
}
