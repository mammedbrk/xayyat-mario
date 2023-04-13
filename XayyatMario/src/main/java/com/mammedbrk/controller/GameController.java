package com.mammedbrk.controller;

import com.mammedbrk.current.Current;
import com.mammedbrk.event.CharacterMovementEvent;
import com.mammedbrk.model.Game;
import com.mammedbrk.model.Scene;
import com.mammedbrk.model.Section;
import com.mammedbrk.model.gamecomponent.Tile;
import com.mammedbrk.view.game.TileImages;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    // todo get Game from somewhere (json/user)
    // todo create an iterator on sections/levels

    private static final double WIDTH = 1300;
    private static final double HEIGHT = 800;
    private Game game;
    private int levelNo;
    private int sectionNo;
    private TileImages tileImages;
    private Tile[][] tiles;
    private ImageView[][] gTiles;

    public GameController() {
        this.game = Current.game;
        levelNo = game.getScene().getSection().getLevel().getNo();
        sectionNo = game.getScene().getSection().getNo();
        tileImages = new TileImages();
    }

    // Methods

    public List<ImageView> loadNextSection() {
        tiles = new Tile[(int) (WIDTH/Tile.TILE_SIZE)][(int) (HEIGHT/Tile.TILE_SIZE)];
        gTiles = new ImageView[(int) (WIDTH/Tile.TILE_SIZE)][(int) (HEIGHT/Tile.TILE_SIZE)];

        if (sectionNo > game.getLevels().get(levelNo - 1).getSections().size()) {
            levelNo++;
            sectionNo = 1;
            if (levelNo > game.getLevels().size())
                return null;
        }
        Section section = game.getLevels().get(levelNo - 1).getSections().get(sectionNo - 1);

        List<ImageView> list = new ArrayList<>();
        for (int i = 0; i < section.getScenes().size(); i++) {
            Scene scene = section.getScenes().get(i);
            for (Tile tile: scene.getComponents()) {
                ImageView imgView = new ImageView(tileImages.getImage(tile.getImageAddress()));

                int x = tile.getX() * Tile.TILE_SIZE + i * ((int) (WIDTH / Tile.TILE_SIZE)) * Tile.TILE_SIZE;
                int y = tile.getY() * Tile.TILE_SIZE;

                imgView.setX(x);
                imgView.setY(y);
                imgView.setFitWidth(Tile.TILE_SIZE);
                imgView.setFitHeight(Tile.TILE_SIZE);
                imgView.setScaleX(tile.scaleX());
                imgView.setScaleY(tile.scaleY());
                imgView.setVisible(true);

                tiles[x / Tile.TILE_SIZE][y / Tile.TILE_SIZE] = tile;
                gTiles[x / Tile.TILE_SIZE][y / Tile.TILE_SIZE] = imgView;

                list.add(imgView);
            }
        }
        return list;
    }


    public boolean moveAndCollision(CharacterMovementEvent characterMovementEvent) {
        return false;
    }


    // Getters and setters

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public int getSectionNo() {
        return sectionNo;
    }

    public void setSectionNo(int sectionNo) {
        this.sectionNo = sectionNo;
    }

    public TileImages getTileImages() {
        return tileImages;
    }

    public void setTileImages(TileImages tileImages) {
        this.tileImages = tileImages;
    }
}
