package com.mammedbrk.controller;

import com.mammedbrk.model.component.Checkpoint;
import com.mammedbrk.model.component.Component;
import com.mammedbrk.model.component.EmptySpace;
import com.mammedbrk.model.component.block.Block;
import com.mammedbrk.model.component.enemy.Enemy;
import com.mammedbrk.model.component.item.Item;
import com.mammedbrk.model.component.pipe.Pipe;
import com.mammedbrk.model.game.Game;
import com.mammedbrk.model.game.Section;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Movable;
import com.mammedbrk.model.interfaces.Timer;
import com.mammedbrk.view.game.GameView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameController {
    private static double WIDTH;
    private static double HEIGHT;
    private static double FPS;
    private Game game;
    private GameView view;
    private Section section;
    private int frameCount;

    public GameController(Game game, GameView view) {
        this.game = game;
        this.view = view;
        this.section = game.currentSection();
        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.play();
    }

    private Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(1000 / FPS), event -> {
        frameCount++;
        view.handleInput();
        // doCharacterOperations();
        update();
        // doCollisionDetection();
        view.render(game);
    }));

    private void update() {
        // todo move mario

        for (Block block: section.getBlocks()) {
            method(block);
        }
        for (Enemy enemy: section.getEnemies()) {
            method(enemy);
        }
        for (Pipe pipe: section.getPipes()) {
            method(pipe);
        }
        for (Item item: section.getItems()) {
            method(item);
        }
        for (Checkpoint checkpoint: section.getCheckpoints()) {
            method(checkpoint);
        }
        for (EmptySpace space: section.getSpaces()) {
            method(space);
        }

        // todo collision detection and other updates
        //  reset gravity if landed
        //  change blocks if hit
        //  remove enemies if killed
    }

    private void method(Component component) {
        if (component instanceof Movable)
            ((Movable) component).move();

        if (component instanceof Gravitational)
            ((Gravitational) component).applyGravity();

        if (frameCount >= 1000 / FPS && component instanceof Timer) {
            frameCount = 0;
            ((Timer) component).changeTime();
        }
    }

    public static void setWIDTH(double WIDTH) {
        GameController.WIDTH = WIDTH;
    }

    public static void setHEIGHT(double HEIGHT) {
        GameController.HEIGHT = HEIGHT;
    }

    public static void setFPS(double FPS) {
        GameController.FPS = FPS;
    }
}
