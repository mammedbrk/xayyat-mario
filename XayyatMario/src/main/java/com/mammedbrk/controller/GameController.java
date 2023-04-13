package com.mammedbrk.controller;

import com.mammedbrk.current.Current;
import com.mammedbrk.event.CharacterCollisionEvent;
import com.mammedbrk.event.CharacterMovementEvent;
import com.mammedbrk.model.Game;
import com.mammedbrk.model.Scene;
import com.mammedbrk.model.Section;
import com.mammedbrk.model.gamecomponent.Coin;
import com.mammedbrk.model.gamecomponent.Tile;
import com.mammedbrk.model.gamecomponent.block.Block;
import com.mammedbrk.model.gamecomponent.block.PowerUpBlock;
import com.mammedbrk.model.gamecomponent.enemy.Enemy;
import com.mammedbrk.view.game.TileImages;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    // todo get Game from somewhere (json/user)
    // todo create an iterator on sections/levels

    private static final double WIDTH = 1300;
    private static final double HEIGHT = 1000;
    private Game game;
    private int levelNo;
    private int sectionNo;
    private TileImages tileImages;
    private Tile[][] tiles;

    public GameController() {
        this.game = Current.game;
//        levelNo = game.getScene().getSection().getLevel().getNo();
//        sectionNo = game.getScene().getSection().getNo();
        tileImages = new TileImages();
        levelNo = 1;
        sectionNo = 1;
    }

    // Methods

    public List<ImageView> loadNextSection() {
        tiles = new Tile[5*(int) (WIDTH/Tile.TILE_SIZE)][(int) (HEIGHT/Tile.TILE_SIZE)];

//        if (sectionNo > game.getLevels().get(levelNo - 1).getSections().size()) {
//            levelNo++;
//            sectionNo = 1;
//            if (levelNo > game.getLevels().size())
//                return null;
//        }
//        Section section = game.getLevels().get(levelNo - 1).getSections().get(sectionNo - 1);
        Section section = Current.game.getScene().getSection();

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

                list.add(imgView);
            }
        }
        return list;
    }


    public CharacterMovementEvent moveAndCollision(CharacterCollisionEvent characterCollisionEvent) {
        double dx = characterCollisionEvent.getDx();
        double dy = characterCollisionEvent.getDy();
        double xFront = characterCollisionEvent.getxFront();
        double xBack = characterCollisionEvent.getxBack();
        double yFront = characterCollisionEvent.getyFront();
        double yBack = characterCollisionEvent.getyBack();

        CharacterMovementEvent characterMovementEvent = new CharacterMovementEvent();
        characterMovementEvent.setDx(dx);
        characterMovementEvent.setDy(dy);
        characterMovementEvent.setVisible(true);

        if (characterMovementEvent.getDy() < 0
                && (occupied(xFront, yFront + characterCollisionEvent.getDy()) instanceof PowerUpBlock
                || occupied(xBack, yFront + characterMovementEvent.getDy()) instanceof PowerUpBlock)) {
            // todo power up enabled
            characterMovementEvent.setPowerUp(true);
            characterMovementEvent.setVisible(false);
            System.out.println("PowerUp");
        }

        if (occupied(xFront, yFront + characterCollisionEvent.getDy()) instanceof Block
                || occupied(xBack, yFront + characterMovementEvent.getDy()) instanceof Block) {
            characterMovementEvent.setDy(0);
            characterMovementEvent.setCanJump(true);
        }

        if (occupied(xFront + characterMovementEvent.getDx(), yFront + characterMovementEvent.getDy()) instanceof Block
                || occupied(xFront + characterMovementEvent.getDx(), yBack + characterMovementEvent.getDy()) instanceof Block) {
            characterMovementEvent.setDx(0);
        }

        if (occupied((xBack + xFront) / 2 + characterMovementEvent.getDx(), (yBack + yFront) / 2 + characterMovementEvent.getDy()) instanceof Coin) {
            // todo add coin, change coin value to 0
            System.out.println(((Coin)tiles[(int) (((xBack + xFront) / 2 + characterMovementEvent.getDx()) / Tile.TILE_SIZE)][(int) (((yBack + yFront) / 2 + characterMovementEvent.getDy()) / Tile.TILE_SIZE)]).getValue());
            characterMovementEvent.setVisible(false);
        }

        if (occupied(xBack + characterMovementEvent.getDx(), yBack + characterMovementEvent.getDy()) instanceof Enemy
                || occupied(xFront + characterMovementEvent.getDx(), yBack + characterMovementEvent.getDy()) instanceof Enemy
                || occupied(xBack + characterMovementEvent.getDx(), yFront + characterMovementEvent.getDy()) instanceof Enemy
                || occupied(xFront + characterMovementEvent.getDx(), yFront + characterMovementEvent.getDy()) instanceof Enemy) {
            // todo you lose
            characterMovementEvent.setKilled(true);
        }

        if (yFront > 15*Tile.TILE_SIZE) {
            characterMovementEvent.setDy(0);
            characterMovementEvent.setKilled(true);
        }

        return characterMovementEvent;
    }

    private Tile occupied(double x, double y) {
        return tiles[(int) (x/ Tile.TILE_SIZE)][(int) (y/ Tile.TILE_SIZE)];
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
