package com.mammedbrk.view.game;

import com.mammedbrk.event.MovementEvent;
import com.mammedbrk.listener.StringListener;
import com.mammedbrk.model.component.Checkpoint;
import com.mammedbrk.model.component.Component;
import com.mammedbrk.model.component.block.Block;
import com.mammedbrk.model.component.enemy.Enemy;
import com.mammedbrk.model.component.item.Item;
import com.mammedbrk.model.component.pipe.Pipe;
import com.mammedbrk.model.game.Game;
import com.mammedbrk.model.game.Section;
import com.mammedbrk.model.interfaces.Movable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.List;

public class GameView extends Pane {
    private static double WIDTH;
    private static double HEIGHT;
    private boolean left, right, jump;
    private final List<StringListener> listeners = new LinkedList<>();


    public GameView() {
        this.sceneProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                newValue.setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case UP -> jump = true;
                        case LEFT -> left = true;
                        case RIGHT -> right = true;
                    }
                });

                newValue.setOnKeyReleased(event -> {
                    switch (event.getCode()) {
                        case LEFT -> left = false;
                        case RIGHT -> right = false;
                    }
                });
            }
        });
    }

    public MovementEvent handleInput() {
        return new MovementEvent(right ^ left, left, jump);
    }

    public void render(Game game) {
        Section section = game.currentSection();
        this.getChildren().clear();

        for (Block block: section.getBlocks())
            this.getChildren().add(getImageView(block));
        for (Enemy enemy: section.getEnemies())
            this.getChildren().add(getImageView(enemy));
        for (Pipe pipe: section.getPipes())
            this.getChildren().add(getImageView(pipe));
        for (Item item: section.getItems())
            this.getChildren().add(getImageView(item));
        for (Checkpoint checkpoint: section.getCheckpoints())
            this.getChildren().add(getImageView(checkpoint));

        ImageView marioImageView = getImageView(game.getMario());
        this.getChildren().add(marioImageView);
        if (marioImageView.getBoundsInParent().getMaxX() > WIDTH/2 && (game.currentSection().getLength() * Component.SIZE) + this.getTranslateX() > WIDTH)
            this.setTranslateX(Math.min(this.getTranslateX(), WIDTH/2 - marioImageView.getBoundsInParent().getCenterX()));
        // todo restrict going out of bounds
    }

    private ImageView getImageView(Component component) {
        ImageView imageView = new ImageView();
        imageView.setImage(ComponentImageFactory.getImage(component.getClass().getSimpleName()));
        imageView.setX(component.getX() * Component.SIZE);
        imageView.setX(HEIGHT - component.getY() * Component.SIZE);
        imageView.setFitWidth(Component.SIZE);
        imageView.setFitHeight(Component.SIZE);
        // todo reverse if speed is negative
        return imageView;
    }

    public static void setWIDTH(double WIDTH) {
        GameView.WIDTH = WIDTH;
    }

    public static void setHEIGHT(double HEIGHT) {
        GameView.HEIGHT = HEIGHT;
    }
}
