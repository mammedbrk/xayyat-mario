package com.mammedbrk.view.game;

import com.mammedbrk.current.Current;
import com.mammedbrk.event.CharacterMovementEvent;
import com.mammedbrk.event.SectionLoadEvent;
import com.mammedbrk.listener.CharacterMovementListener;
import com.mammedbrk.listener.SectionLoadListener;
import com.mammedbrk.model.gamecomponent.Tile;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameView extends Pane {
    private final ImageView character;
    private double dx, dy;
    private double xFront, xBack;
    private double yFront, yBack;
    private final static double gravity = 0.15;
    private boolean left, right, up, canJump;
    private final SectionLoadListener gameLoadListener = new SectionLoadListener();
    private final CharacterMovementListener characterMovementListener = new CharacterMovementListener();

    public GameView() {
        this.setStyle("-fx-background-color: #282828;");
        try {
            character = new ImageView(new Image(new FileInputStream(Current.game.getCharacter().getImageAddress())));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        this.sceneProperty().addListener(new ChangeListener<Scene>() {
            @Override
            public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
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

        loadSectionGraphics();
    }

    private void loadSectionGraphics() {
        gameLoadListener.listen(new SectionLoadEvent(this, this));
        character.setFitWidth(50);
        character.setFitHeight(50);
        character.setX(2 * Tile.TILE_SIZE);
        character.setY(5 * Tile.TILE_SIZE);
        this.getChildren().add(character);
    }

    private final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (right) dx += Current.game.getCharacter().getSpeed();
            if (left) dx -= Current.game.getCharacter().getSpeed();
            if (up) {
                up = false;
                dy = Current.game.getCharacter().getJumpAbility() *-1;
            }
            else dy += gravity;

            if (dy > 0) {
                yFront = character.getY() + character.getFitHeight();
                yBack = character.getY();
            }
            else if (dy < 0){
                yFront = character.getY();
                yBack = character.getY() + character.getFitHeight();
            }
            if (dx > 0) {
                xFront = character.getX() + character.getFitWidth();
                xBack = character.getX();
            }
            else if (dx < 0){
                xFront = character.getX();
                xBack = character.getX() + character.getFitWidth();
            }

            canJump = characterMovementListener.listen(new CharacterMovementEvent(
                    this,
                    dx, dy,
                    xFront, xBack,
                    yFront, yBack,
                    character.getFitWidth(),
                    character.getFitHeight()));






        }
    };



}
