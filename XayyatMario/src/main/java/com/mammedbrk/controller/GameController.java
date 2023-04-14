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
import com.mammedbrk.model.gamecomponent.block.CoinBlock;
import com.mammedbrk.model.gamecomponent.block.PowerUpBlock;
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

                imgView.setFitWidth(Tile.TILE_SIZE * tile.scaleX());
                imgView.setFitHeight(Tile.TILE_SIZE * tile.scaleY());
                imgView.setX(x);
                imgView.setY(y);
                imgView.setVisible(true);

                tiles[x / Tile.TILE_SIZE][y / Tile.TILE_SIZE] = tile;
                tile.setX(x / Tile.TILE_SIZE);
                tile.setY(y / Tile.TILE_SIZE);

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

        if (occupied(xFront, yFront + characterCollisionEvent.getDy()) instanceof Block
                || occupied(xBack, yFront + characterMovementEvent.getDy()) instanceof Block) {
            characterMovementEvent.setDy(0);
            characterMovementEvent.setCanJump(true);
        }

        if (occupied(xFront + characterMovementEvent.getDx(), yFront + characterMovementEvent.getDy()) instanceof Block
                || occupied(xFront + characterMovementEvent.getDx(), yBack + characterMovementEvent.getDy()) instanceof Block) {
            characterMovementEvent.setDx(0);
        }

        // While jumping,
        if (dy < 0) {
            // Check if PowerUpBlock:
            if (occupied(xFront, yFront + dy) instanceof PowerUpBlock) {
                characterMovementEvent.setPowerUp(true);
                characterMovementEvent.setRemovedTile(occupied(xFront, yFront + dy));
                removeTile(occupied(xFront, yFront + dy));
                System.out.println("pwrUp"); // todo
            }
            else if (occupied(xBack, yFront + dy) instanceof PowerUpBlock) {
                characterMovementEvent.setPowerUp(true);
                characterMovementEvent.setRemovedTile(occupied(xBack, yFront + dy));
                removeTile(occupied(xBack, yFront + dy));
                System.out.println("pwrUp"); // todo
            }

            // Check if CoinBlock:
            if (occupied(xFront, yFront + dy) instanceof CoinBlock) {
                System.out.println("coinBlock"); // todo
            }
            else if (occupied(xBack, yFront + dy) instanceof CoinBlock) {
                System.out.println("coinBlock"); // todo
            }
        }

        // Check if Coin:
        if (occupied((xBack + xFront) / 2 + characterMovementEvent.getDx(), (yBack + yFront) / 2 + characterMovementEvent.getDy()) instanceof Coin) {
            game.getScene().getSection().changeCoinsBy(((Coin) occupied((xBack + xFront) / 2 + characterMovementEvent.getDx(), (yBack + yFront) / 2 + characterMovementEvent.getDy())).getValue());
            System.out.println(game.getScene().getSection().getCoins()); // todo
            characterMovementEvent.setRemovedTile(occupied((xBack + xFront) / 2 + characterMovementEvent.getDx(), (yBack + yFront) / 2 + characterMovementEvent.getDy()));
            removeTile(occupied((xBack + xFront) / 2 + characterMovementEvent.getDx(), (yBack + yFront) / 2 + characterMovementEvent.getDy()));
        }

        /*
        if (occupied(xBack + characterMovementEvent.getDx(), yBack + characterMovementEvent.getDy()) instanceof Enemy
                || occupied(xFront + characterMovementEvent.getDx(), yBack + characterMovementEvent.getDy()) instanceof Enemy
                || occupied(xBack + characterMovementEvent.getDx(), yFront + characterMovementEvent.getDy()) instanceof Enemy
                || occupied(xFront + characterMovementEvent.getDx(), yFront + characterMovementEvent.getDy()) instanceof Enemy) {
            // todo you lose
            characterMovementEvent.setKilled(true);
        }*/

        // Lose when fall down
        if (yFront > HEIGHT - 100) {
            characterMovementEvent.setDy(0);
            characterMovementEvent.setKilled(true);
        }

        // Move enemies todo
//        for (GEnemy gEnemy: enemies) {
//            gEnemy.getEnemy().changeYVelocity();
//            if ((int) (gEnemy.getImage().getY() / Tile.TILE_SIZE) > )
//            if (gEnemy.getImage().getY() / Tile.TILE_SIZE != gEnemy.getEnemy().getY()) {
//                tiles[gEnemy.getEnemy().getX()][(int) (gEnemy.getImage().getY() / Tile.TILE_SIZE)] = gEnemy.getEnemy();
//            }
//        }
//        characterMovementEvent.setEnemies(enemies);

        return characterMovementEvent;
    }

    private Tile occupied(double x, double y) {
        return tiles[(int) (x/ Tile.TILE_SIZE)][(int) (y/ Tile.TILE_SIZE)];
    }

    private void removeTile(Tile tile) {
        tiles[tile.getX()][tile.getY()] = null;
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
