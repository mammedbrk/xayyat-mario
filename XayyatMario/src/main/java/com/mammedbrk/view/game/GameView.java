package com.mammedbrk.view.game;

import com.mammedbrk.access.SceneAccess;
import com.mammedbrk.controller.GameController;
import com.mammedbrk.current.Current;
import com.mammedbrk.event.CharacterCollisionEvent;
import com.mammedbrk.event.CharacterMovementEvent;
import com.mammedbrk.listener.CharacterCollisionListener;
import com.mammedbrk.listener.SectionLoadListener;
import com.mammedbrk.model.Game;
import com.mammedbrk.model.Scene;
import com.mammedbrk.model.Section;
import com.mammedbrk.model.gamecomponent.Tile;
import com.mammedbrk.model.gamecomponent.enemy.Enemy;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GameView extends Pane {
    private static final double WIDTH = 1300;
    private static final double HEIGHT = 1000;
    private final ImageView character;
    private ImageView[][] gTiles;
    private double dx, dy;
    private double xFront, xBack;
    private double yFront, yBack;
    private static final double gravity = 0.15;
    private boolean left, right, up, canJump;
    private final SectionLoadListener sectionLoadListener = new SectionLoadListener();
    private final CharacterCollisionListener characterCollisionListener = new CharacterCollisionListener();

    public GameView() {
        // -------------------------------------
        Current.game = new Game();
        Current.controller = new GameController();
        sectionLoadListener.setController(Current.controller);
        characterCollisionListener.setController(Current.controller);
        Current.game.setCharacter(Current.user.chosenCharacter());

        Section section = new Section(100);
        section.setScenes(new ArrayList<>());
        section.setNo(1);

        Scene scene = new SceneAccess().get(1, 1, 1);
        scene.setSection(section);
        section.addScene(scene);
        Scene scene2 = new SceneAccess().get(1, 1, 2);
        scene2.setSection(section);
        section.addScene(scene2);
        Current.game.setScene(scene);

        // -------------------------------------

        this.setStyle("-fx-background-color: #282828;");
        try {
            character = new ImageView(new Image(new FileInputStream(Current.game.getCharacter().getImageAddress())));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Add KeyListener
        this.sceneProperty().addListener(new ChangeListener<javafx.scene.Scene>() {
            @Override
            public void changed(ObservableValue<? extends javafx.scene.Scene> observable, javafx.scene.Scene oldValue, javafx.scene.Scene newValue) {
                if (newValue != null) {
                    newValue.setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            if (event.getCode() == KeyCode.RIGHT)
                                right = true;
                            if (event.getCode() == KeyCode.LEFT)
                                left = true;
                            if (event.getCode() == KeyCode.UP && canJump) {
                                up = true;
                            }
                        }
                    });

                    newValue.setOnKeyReleased(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            if (event.getCode() == KeyCode.RIGHT)
                                right = false;
                            if (event.getCode() == KeyCode.LEFT)
                                left = false;
                        }
                    });
                }
            }
        });

        // Load section graphics
        loadSectionGraphics();

        // Start AnimationTimer
        timer.start();
    }

    private void loadSectionGraphics() {
        gTiles = new ImageView[5 * (int) (WIDTH / Tile.TILE_SIZE)][(int) (HEIGHT / Tile.TILE_SIZE)];
        List<ImageView> list = sectionLoadListener.listen();
        this.getChildren().clear();
        if (list == null) {
            // todo game finished
            return;
        }
        for (ImageView gTile : list) {
            this.getChildren().add(gTile);
            gTiles[(int) gTile.getX() / Tile.TILE_SIZE][(int) gTile.getY() / Tile.TILE_SIZE] = gTile;
        }
        character.setFitWidth(50);
        character.setFitHeight(50);
        character.setX(2 * Tile.TILE_SIZE);
        character.setY(5 * Tile.TILE_SIZE);
        this.getChildren().add(character);
    }

    private final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            dx = 0;
            if (right) dx += Current.game.getCharacter().getSpeed();
            if (left && character.getBoundsInParent().getMinX() > 0) dx -= Current.game.getCharacter().getSpeed();
            if (up) {
                up = false;
                dy = Current.game.getCharacter().getJumpAbility() * -1;
            } else dy += gravity;

            if (dy > 0) {
                yFront = character.getY() + character.getFitHeight();
                yBack = character.getY();
            } else if (dy < 0) {
                yFront = character.getY();
                yBack = character.getY() + character.getFitHeight();
            }
            if (dx > 0) {
                xFront = character.getX() + character.getFitWidth();
                xBack = character.getX();
            } else if (dx < 0) {
                xFront = character.getX();
                xBack = character.getX() + character.getFitWidth();
            }

            CharacterMovementEvent characterMovementEvent
                    = characterCollisionListener.listen(new CharacterCollisionEvent(
                    this,
                    dx, dy,
                    xFront, xBack,
                    yFront, yBack));

            canJump = characterMovementEvent.isCanJump();

            if (characterMovementEvent.getRemovedTile() != null) {
                removeGTile(characterMovementEvent.getRemovedTile());
            }

            if (characterMovementEvent.isPowerUp()) {
                // todo apply power up
            }

            if (characterMovementEvent.isKilled()) {
                // todo collision with enemy
                // todo falling down
                System.out.println("you lose");
            }

            dx = characterMovementEvent.getDx();
            dy = characterMovementEvent.getDy();

            character.setX(character.getX() + dx);
            character.setY(character.getY() + dy);

            if (dx > 0 && character.getBoundsInParent().getCenterX() > WIDTH / 2) {
                for (Node node : GameView.this.getChildren()) {
                    node.relocate(node.getBoundsInParent().getMinX() - dx, node.getBoundsInParent().getMinY());
                }
            }


            // fixme split enemies from tiles for improvement
            for (Tile tile: Current.game.getScene().getComponents()) {
                if (tile instanceof Enemy) {
                    ImageView enemyImg = gTiles[tile.getX()][tile.getY()];
                    enemyImg.setX(enemyImg.getX() + ((Enemy) tile).getxVelocity());
                    enemyImg.setY(enemyImg.getY() + ((Enemy) tile).getyVelocity());
                    ((Enemy) tile).setxCurrent((int) (enemyImg.getX() / Tile.TILE_SIZE));
                    ((Enemy) tile).setyCurrent((int) (enemyImg.getY() / Tile.TILE_SIZE));
                    ((Enemy) tile).modifySpeed();
                }
            }
        }
    };

    private void removeGTile(Tile tile) {
        this.getChildren().remove(gTiles[tile.getX()][tile.getY()]);
        gTiles[tile.getX()][tile.getY()] = null;
    }
}
