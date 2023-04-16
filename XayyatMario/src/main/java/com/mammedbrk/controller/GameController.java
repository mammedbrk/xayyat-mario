package com.mammedbrk.controller;

import com.mammedbrk.access.SceneAccess;
import com.mammedbrk.access.UserAccess;
import com.mammedbrk.current.Current;
import com.mammedbrk.event.CharacterCollisionEvent;
import com.mammedbrk.event.CharacterMovementEvent;
import com.mammedbrk.model.*;
import com.mammedbrk.model.gamecomponent.Coin;
import com.mammedbrk.model.gamecomponent.Tile;
import com.mammedbrk.model.gamecomponent.block.Block;
import com.mammedbrk.model.gamecomponent.block.CoinBlock;
import com.mammedbrk.model.gamecomponent.block.PowerUpBlock;
import com.mammedbrk.model.gamecomponent.enemy.Enemy;
import com.mammedbrk.view.game.TileImages;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private static final double WIDTH = 1300;
    private static final double HEIGHT = 1000;
    private final SceneAccess access = new SceneAccess();
    private Game game;
    private TileImages tileImages;
    private Tile[][] tiles;
    private List<Enemy> enemies;
    private CharacterMovementEvent characterMovementEvent;

    public GameController() {
        this.game = Current.user.getCurrentGame();
        tileImages = new TileImages();
    }

    // Methods

    public List<ImageView> loadSection() {
        tiles = new Tile[(game.getCurrentLevel().getCurrentSection().getScenes().size() + 1) *(int) (WIDTH/Tile.TILE_SIZE)][(int) (HEIGHT/Tile.TILE_SIZE)];
        enemies = new ArrayList<>();

        Section section = Current.user.getCurrentGame().getCurrentLevel().getCurrentSection();

        List<ImageView> list = new ArrayList<>();
        for (int i = 0; i < section.getScenes().size(); i++) {
            Scene scene = section.getScenes().get(i);
            for (Tile tile: scene.getComponents()) {
                ImageView imgView = new ImageView(tileImages.getImage(tile.getImageAddress()));

                int x = tile.getX() * Tile.TILE_SIZE;
                if (tile.getX() < (int) (WIDTH / Tile.TILE_SIZE))
                    x = tile.getX() * Tile.TILE_SIZE + i * ((int) (WIDTH / Tile.TILE_SIZE)) * Tile.TILE_SIZE;
                int y = tile.getY() * Tile.TILE_SIZE;

                imgView.setFitWidth(Tile.TILE_SIZE * tile.scaleX());
                imgView.setFitHeight(Tile.TILE_SIZE * tile.scaleY());
                imgView.setX(x);
                imgView.setY(y);
                imgView.setVisible(true);

                if (tile instanceof Enemy)
                    enemies.add((Enemy) tile);
                else
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

        characterMovementEvent = new CharacterMovementEvent();
        characterMovementEvent.setDx(dx);
        characterMovementEvent.setDy(dy);

        if (xBack > game.getCurrentLevel().getCurrentSection().getScenes().size() * ((int) (WIDTH / Tile.TILE_SIZE)) * Tile.TILE_SIZE) {
            readNextSection();

            characterMovementEvent.setLoadNeeded(true);
            characterMovementEvent.setDx(0);
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

        // While jumping,
        if (dy < 0) {
            // Check if PowerUpBlock:
            if (occupied(xFront, yFront + dy) instanceof PowerUpBlock) {
                characterMovementEvent.setPowerUp(true);
                characterMovementEvent.setRemovedTile(occupied(xFront, yFront + dy));
                removeTile(occupied(xFront, yFront + dy));
                System.out.println("pwrUp");
            }
            else if (occupied(xBack, yFront + dy) instanceof PowerUpBlock) {
                characterMovementEvent.setPowerUp(true);
                characterMovementEvent.setRemovedTile(occupied(xBack, yFront + dy));
                removeTile(occupied(xBack, yFront + dy));
                System.out.println("pwrUp");
            }

            // Check if CoinBlock:
            if (occupied(xFront, yFront + dy) instanceof CoinBlock) {
                game.getCurrentLevel().getCurrentSection().addCoin(((CoinBlock) occupied(xFront, yFront + dy)).getValue());
            }
            else if (occupied(xBack, yFront + dy) instanceof CoinBlock) {
                game.getCurrentLevel().getCurrentSection().addCoin(((CoinBlock) occupied(xBack, yFront + dy)).getValue());
            }
        }

        // Check if Coin:
        if (occupied((xBack + xFront) / 2 + characterMovementEvent.getDx(), (yBack + yFront) / 2 + characterMovementEvent.getDy()) instanceof Coin) {
            game.getCurrentLevel().getCurrentSection().addCoin(((Coin) occupied((xBack + xFront) / 2 + characterMovementEvent.getDx(), (yBack + yFront) / 2 + characterMovementEvent.getDy())).getValue());
            System.out.println(game.getCurrentLevel().getCurrentSection().getCoins());
            characterMovementEvent.setRemovedTile(occupied((xBack + xFront) / 2 + characterMovementEvent.getDx(), (yBack + yFront) / 2 + characterMovementEvent.getDy()));
            removeTile(occupied((xBack + xFront) / 2 + characterMovementEvent.getDx(), (yBack + yFront) / 2 + characterMovementEvent.getDy()));
        }

        // Lose with enemies
        for (Enemy enemy: enemies) {
            if ((int) (xFront / Tile.TILE_SIZE) == enemy.getxCurrent() && (int) ((yBack + yFront) / 2 / Tile.TILE_SIZE) == enemy.getyCurrent()) {
                readFirstSection();
                game.reduceHeart();
                characterMovementEvent.setLoadNeeded(true);
            }
        }

        // Lose when fall down
        if (yFront > HEIGHT - 100) {
            readFirstSection();
            game.reduceHeart();
            characterMovementEvent.setDy(0);
            characterMovementEvent.setLoadNeeded(true);
        }

        return characterMovementEvent;
    }

    private Tile occupied(double x, double y) {
        return tiles[(int) (x/ Tile.TILE_SIZE)][(int) (y/ Tile.TILE_SIZE)];
    }

    private void removeTile(Tile tile) {
        tiles[tile.getX()][tile.getY()] = null;
    }

    private void readNextSection() {
        game.getCurrentLevel().addCoin(game.getCurrentLevel().getCurrentSection().getCoins());
        game.getCurrentLevel().addScore(game.getCurrentLevel().getCurrentSection().getCoins());

        int sectionNo = game.getCurrentLevel().getCurrentSection().getNo();
        int levelNo = game.getCurrentLevel().getNo();

        Section section = new Section(120);
        if (access.exists(levelNo, ++sectionNo)) {
            section.setNo(sectionNo);
            section.setScenes(new ArrayList<>());
            for (int sceneNo = 1; ; sceneNo++) {
                Scene scene = new SceneAccess().get(levelNo, sectionNo, sceneNo);
                if (scene == null) break;
                section.addScene(scene);
            }
            section.setX(2);
            section.setY(5);
            game.getCurrentLevel().setCurrentSection(section);
            return;
        }

        game.addCoin(game.getCurrentLevel().getCoins());
        game.addScore(game.getCurrentLevel().getScore());

        if (access.exists(++levelNo, sectionNo = 1)) {
            section.setNo(sectionNo);
            section.setScenes(new ArrayList<>());
            for (int sceneNo = 1; ; sceneNo++) {
                Scene scene = new SceneAccess().get(levelNo, sectionNo, sceneNo);
                if (scene == null) break;
                section.addScene(scene);
            }
            section.setX(2);
            section.setY(5);
            Level level = new Level();
            level.setNo(levelNo);
            level.setCurrentSection(section);
            game.setCurrentLevel(level);
            return;
        }

        Current.user.addCoin(game.getCurrentLevel().getCoins());
        Current.user.maximizeScore(game.getScore());
        Current.user.removeGame(game);
        Current.user.setCurrentGame(null);
        new UserAccess().add(Current.user);
        characterMovementEvent.setWin(true);
    }

    private void readFirstSection() {
        Section section = new Section(120);
        section.setNo(1);
        section.setScenes(new ArrayList<>());
        for (int sceneNo = 1; ; sceneNo++) {
            Scene scene = new SceneAccess().get(game.getCurrentLevel().getNo(), 1, sceneNo);
            if (scene == null) break;
            section.addScene(scene);
        }
        section.setX(2);
        section.setY(5);
        game.getCurrentLevel().setCurrentSection(section);
    }

    // Getters and setters

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public TileImages getTileImages() {
        return tileImages;
    }

    public void setTileImages(TileImages tileImages) {
        this.tileImages = tileImages;
    }
}
