package com.mammedbrk.controller;

import com.mammedbrk.config.Config;
import com.mammedbrk.controller.collision.*;
import com.mammedbrk.event.MovementEvent;
import com.mammedbrk.model.component.Checkpoint;
import com.mammedbrk.model.component.Component;
import com.mammedbrk.model.component.block.Block;
import com.mammedbrk.model.component.enemy.Enemy;
import com.mammedbrk.model.component.item.Item;
import com.mammedbrk.model.component.pipe.Pipe;
import com.mammedbrk.model.game.Game;
import com.mammedbrk.model.game.Section;
import com.mammedbrk.model.interfaces.*;
import com.mammedbrk.view.game.GameView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameController {
    private static double WIDTH;
    private static double HEIGHT;
    private static double FPS = Double.parseDouble(Config.getInstance().getProperty("fps"));
    private Game game;
    private GameView view;
    private CollisionManager collisionManager;
    private int frameCount;

    public GameController(Game game, GameView view) {
        this.game = game;
        this.view = view;
        this.collisionManager = new CollisionManager();
        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.play();
    }

    private Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(1000 / FPS), event -> {
        frameCount++;
        MovementEvent movementEvent = view.handleInput();
        update(movementEvent);
        view.render(game);
        if (frameCount > FPS)
            frameCount = 0;
    }));


    private void update(MovementEvent movementEvent) {
        updateStates(movementEvent);
        handleCollisions();
        handleComponentChanges();
    }

    private void updateStates(MovementEvent movementEvent) {
        Section section = game.currentSection();

        moveMario(movementEvent);
        for (Block block: section.getBlocks())
            updateComponentState(block);
        for (Enemy enemy: section.getEnemies())
            updateComponentState(enemy);
        for (Pipe pipe: section.getPipes())
            updateComponentState(pipe);
        for (Item item: section.getItems())
            updateComponentState(item);
        for (Checkpoint checkpoint: section.getCheckpoints())
            updateComponentState(checkpoint);
    }

    private void handleCollisions() {
        Section section = game.currentSection();

        for (Block block: section.getBlocks()) {
            collisionManager.handleCollision(game.getMario(), block);
            for (Enemy enemy: section.getEnemies())
                collisionManager.handleCollision(enemy, block);
            for (Item item: section.getItems())
                collisionManager.handleCollision(item, block);
        }
        for (Pipe pipe: section.getPipes()) {
            collisionManager.handleCollision(game.getMario(), pipe);
            for (Enemy enemy: section.getEnemies())
                collisionManager.handleCollision(enemy, pipe);
            for (Item item: section.getItems())
                collisionManager.handleCollision(item, pipe);
        }
        for (Checkpoint checkpoint: section.getCheckpoints()) {
            collisionManager.handleCollision(game.getMario(), checkpoint);
        }
        for (Enemy enemy: section.getEnemies()) {
            collisionManager.handleCollision(game.getMario(), enemy);
            for (Enemy enemy2: section.getEnemies())
                if (!enemy.equals(enemy2))
                    collisionManager.handleCollision(enemy, enemy2);
        }
        for (Item item: section.getItems()) {
            collisionManager.handleCollision(game.getMario(), item);
        }
    }

    private void handleComponentChanges() {
        Section section = game.currentSection();

        Iterator<Block> blockIterator = section.getBlocks().listIterator();
        List<Block> newBlocks = new ArrayList<>();
        while (blockIterator.hasNext()) {
            Block block = blockIterator.next();
            if (block instanceof Hittable && ((Hittable) block).isHit()) {
                if (block instanceof Changeable)
                    newBlocks.add((Block) ((Changeable<?>) block).changedObject());
                blockIterator.remove();
            }
        }
        section.getBlocks().addAll(newBlocks);

        section.getEnemies().removeIf(enemy -> !enemy.isAlive());
        section.getItems().removeIf(Item::isHit);
    }

    private void moveMario(MovementEvent movementEvent) {
        if (movementEvent.isMove()) {
            game.getMario().setMove(true);
            game.getMario().setLeft(movementEvent.isLeft());
        } else game.getMario().setMove(false);
        if (movementEvent.isJump())
            game.getMario().setJump(true);
        game.getMario().move();
        game.getMario().applyGravity();
    }

    private void updateComponentState(Component component) {
        if (component instanceof Gravitational)
            ((Gravitational) component).applyGravity();
        if (component instanceof Movable)
            ((Movable) component).move();
        if (component instanceof Timer && frameCount > FPS)
            ((Timer) component).changeTime();
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

    public void setCollisionManager(CollisionManager collisionManager) {
        this.collisionManager = collisionManager;
    }
}
