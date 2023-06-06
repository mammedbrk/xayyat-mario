package com.mammedbrk.view.game;

import com.mammedbrk.event.MovementEvent;
import com.mammedbrk.listener.StringListener;
import com.mammedbrk.model.component.Checkpoint;
import com.mammedbrk.model.component.Component;
import com.mammedbrk.model.component.EmptySpace;
import com.mammedbrk.model.component.block.Block;
import com.mammedbrk.model.component.enemy.Enemy;
import com.mammedbrk.model.component.item.Item;
import com.mammedbrk.model.component.pipe.Pipe;
import com.mammedbrk.model.game.Game;
import com.mammedbrk.model.game.Section;
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
                    if (event.getCode() == KeyCode.D)
                        right = true;
                    if (event.getCode() == KeyCode.A)
                        left = true;
                    if (event.getCode() == KeyCode.W)
                        jump = true;
                });

                newValue.setOnKeyReleased(event -> {
                    if (event.getCode() == KeyCode.D)
                        right = false;
                    if (event.getCode() == KeyCode.A)
                        left = false;
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

        this.getChildren().add(getImageView(game.getMario()));
        for (Block block: section.getBlocks())
            this.getChildren().add(getImageView(block));
        for (Enemy enemy: section.getEnemies())
            this.getChildren().add(getImageView(enemy));
        for (Pipe pipe: section.getPipes())
            this.getChildren().add(getImageView(pipe));
        for (Item item: section.getItems())
            this.getChildren().add(getImageView(item));
        for (EmptySpace space: section.getSpaces())
            this.getChildren().add(getImageView(space));
        for (Checkpoint checkpoint: section.getCheckpoints())
            this.getChildren().add(getImageView(checkpoint));

        // todo move scene with mario
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
