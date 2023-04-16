package com.mammedbrk.view.game;

import com.mammedbrk.current.Current;
import com.mammedbrk.event.CharacterCollisionEvent;
import com.mammedbrk.event.CharacterMovementEvent;
import com.mammedbrk.listener.CharacterCollisionListener;
import com.mammedbrk.listener.SectionLoadListener;
import com.mammedbrk.listener.StringListener;
import com.mammedbrk.model.Scene;
import com.mammedbrk.model.gamecomponent.Tile;
import com.mammedbrk.model.gamecomponent.enemy.Enemy;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

public class GameView extends Pane {
    private static final double WIDTH = 1300;
    private static final double HEIGHT = 1000;
    private ImageView character;
    private ImageView[][] gTiles;
    private double dx, dy;
    private double xFront, xBack;
    private double yFront, yBack;
    private static final double gravity = 0.15;
    private boolean left, right, up, canJump;
    private final SectionLoadListener sectionLoadListener;
    private final CharacterCollisionListener characterCollisionListener;
    private final List<StringListener> listeners = new LinkedList<>();


    public GameView(SectionLoadListener sectionLoadListener, CharacterCollisionListener characterCollisionListener) {
        this.sectionLoadListener = sectionLoadListener;
        this.characterCollisionListener = characterCollisionListener;

        this.setStyle("-fx-background-color: #282828;");

        // Add KeyListener
        this.sceneProperty().addListener(new ChangeListener<javafx.scene.Scene>() {
            @Override
            public void changed(ObservableValue<? extends javafx.scene.Scene> observable, javafx.scene.Scene oldValue, javafx.scene.Scene newValue) {
                if (newValue != null) {
                    newValue.setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            if (event.getCode() == KeyCode.SEMICOLON)
                                right = true;
                            if (event.getCode() == KeyCode.K)
                                left = true;
                            if (event.getCode() == KeyCode.O && canJump) {
                                up = true;
                            }
                        }
                    });

                    newValue.setOnKeyReleased(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            if (event.getCode() == KeyCode.SEMICOLON)
                                right = false;
                            if (event.getCode() == KeyCode.K)
                                left = false;
                        }
                    });
                }
            }
        });

        // Load section graphics
        loadSectionGraphics();
    }

    public void loadSectionGraphics() {

        xFront = xBack = Current.user.getCurrentGame().getCurrentLevel().getCurrentSection().getX() * Tile.TILE_SIZE;
        yFront = yBack = Current.user.getCurrentGame().getCurrentLevel().getCurrentSection().getY() * Tile.TILE_SIZE;

        timer.stop();
        this.getChildren().clear();
        gTiles = new ImageView[5 * (int) (WIDTH / Tile.TILE_SIZE)][(int) (HEIGHT / Tile.TILE_SIZE)];
        List<ImageView> list = sectionLoadListener.listen();
        if (list == null) {
            // todo game finished
            return;
        }
        for (ImageView gTile : list) {
            this.getChildren().add(gTile);
            gTiles[(int) gTile.getX() / Tile.TILE_SIZE][(int) gTile.getY() / Tile.TILE_SIZE] = gTile;
        }
        try {
            character = new ImageView(new Image(new FileInputStream(Current.user.getCurrentGame().getCharacter().getImageAddress())));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        character.setFitWidth(50);
        character.setFitHeight(50);
        character.setX(Current.user.getCurrentGame().getCurrentLevel().getCurrentSection().getX() * Tile.TILE_SIZE);
        character.setY(Current.user.getCurrentGame().getCurrentLevel().getCurrentSection().getY() * Tile.TILE_SIZE);
        this.getChildren().add(character);
        timer.start();
    }

    private final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            dx = 0;
            if (right) dx += Current.user.getCurrentGame().getCharacter().getSpeed();
            if (left && character.getBoundsInParent().getMinX() > 0) dx -= Current.user.getCurrentGame().getCharacter().getSpeed();
            if (up) {
                up = false;
                dy = Current.user.getCurrentGame().getCharacter().getJumpAbility() * -1;
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

            if (characterMovementEvent.isFinished()) {
                finishGame();
                return;
            }

            canJump = characterMovementEvent.isCanJump();

            if (characterMovementEvent.getRemovedTile() != null) {
                removeGTile(characterMovementEvent.getRemovedTile());
            }

            /*if (characterMovementEvent.isPowerUp()) {
                // todo apply power up
            }*/

            dx = characterMovementEvent.getDx();
            dy = characterMovementEvent.getDy();

            character.setX(character.getX() + dx);
            character.setY(character.getY() + dy);

            if (dx > 0 && character.getBoundsInParent().getCenterX() > WIDTH / 2 && character.getX() < Current.user.getCurrentGame().getCurrentLevel().getCurrentSection().getScenes().size() * WIDTH - WIDTH/2) {
                for (Node node : GameView.this.getChildren()) {
                    node.relocate(node.getBoundsInParent().getMinX() - dx, node.getBoundsInParent().getMinY());
                }
            }

            for (Scene scene: Current.user.getCurrentGame().getCurrentLevel().getCurrentSection().getScenes()) {
                for (Tile tile : scene.getComponents()){
                    if (tile instanceof Enemy) {
                        ImageView enemyImg = gTiles[tile.getX()][tile.getY()];
                        if (enemyImg == null) continue;
                        enemyImg.setX(enemyImg.getX() + ((Enemy) tile).getxVelocity());
                        enemyImg.setY(enemyImg.getY() + ((Enemy) tile).getyVelocity());
                        ((Enemy) tile).setxCurrent((int) (enemyImg.getX() / Tile.TILE_SIZE));
                        ((Enemy) tile).setyCurrent((int) (enemyImg.getY() / Tile.TILE_SIZE));
                        ((Enemy) tile).modifySpeed();
                    }
                }
            }

            if (characterMovementEvent.isLoadNeeded()) {
                loadSectionGraphics();
            }
        }
    };

    public void finishGame() {
        for (StringListener listener: listeners) {
            listener.listen("pause");
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Finished");
        alert.setContentText("Score: " + Current.user.getCurrentGame().getScore() + '\n' + "Coins: " + Current.user.getCurrentGame().getCoins());
        alert.show();
        for (StringListener listener: listeners) {
            listener.listen("MainMenu");
        }
    }

    private void removeGTile(Tile tile) {
        this.getChildren().remove(gTiles[tile.getX()][tile.getY()]);
        gTiles[tile.getX()][tile.getY()] = null;
    }

    public void addListener(StringListener listener) {
        listeners.add(listener);
    }

    public void stopTimer() {
        up = left = right = false;
        timer.stop();
    }

    public void startTimer() {
        timer.start();
    }
}
