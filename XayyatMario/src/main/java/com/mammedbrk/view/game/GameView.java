package com.mammedbrk.view.game;

import com.mammedbrk.current.Current;
import com.mammedbrk.model.Character;
import com.mammedbrk.model.Game;
import com.mammedbrk.model.Scene;
import com.mammedbrk.model.Section;
import com.mammedbrk.model.gamecomponent.Coin;
import com.mammedbrk.model.gamecomponent.Tile;
import com.mammedbrk.model.gamecomponent.block.Block;
import com.mammedbrk.model.gamecomponent.block.CoinBlock;
import com.mammedbrk.model.gamecomponent.block.OrdinaryBlock;
import com.mammedbrk.model.gamecomponent.block.PowerUpBlock;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class GameView implements Initializable {
    private Game game; // todo how to get game? :think

    @FXML
    private Pane gamePane;
    private ImageView characterImg;

    private final int width = 1300;
    private final int height = 1000;
    private Tile[][] tiles;
    private ImageView[][] graphicTiles;
    private final HashMap<String, Image> images = new HashMap<>();

    private double dy;
    private static final double gravity = 0.2;
    private boolean up, left, right, canJump;
    private double xForward, xBackward;
    private double xMiddle, yMiddle;
    private double yForward, yBackward;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new Game();
        game.setCharacter(Current.user.getChosenCharacter());

        Section section = new Section(100);
        section.setNo(1);

        Scene scene1 = new Scene();
        scene1.setNo(1);

        for (int i = 0; i < 21; i++) {
            for (int j = 10; j < 15; j++) {
                if (i == 13) continue;
                scene1.addComponent(new OrdinaryBlock(i, j));
            }
        }
        for (int i = 4; i < 9; i++)
            scene1.addComponent(new Coin(i, 9));
        scene1.addComponent(new OrdinaryBlock(12, 9));
        scene1.addComponent(new OrdinaryBlock(12, 8));
        scene1.addComponent(new OrdinaryBlock(12, 7));
        scene1.addComponent(new OrdinaryBlock(11, 9));
        scene1.addComponent(new OrdinaryBlock(11, 8));
        scene1.addComponent(new OrdinaryBlock(10, 9));
        scene1.addComponent(new OrdinaryBlock(14, 9));
        scene1.addComponent(new OrdinaryBlock(14, 8));
        scene1.addComponent(new OrdinaryBlock(14, 7));
        scene1.addComponent(new OrdinaryBlock(15, 9));
        scene1.addComponent(new OrdinaryBlock(15, 8));
        scene1.addComponent(new OrdinaryBlock(16, 9));
        scene1.addComponent(new PowerUpBlock(10, 4));
        section.addScene(scene1);

        Scene scene2 = new Scene();
        scene2.setNo(2);
        for (int i = 0; i < 21; i++) {
            for (int j = 10; j < 15; j++) {
                if (i == 13) continue;
                scene2.addComponent(new OrdinaryBlock(i, j));
            }
        }
        for (int i = 4; i < 9; i++)
            scene2.addComponent(new Coin(i, 9));
        section.addScene(scene2);

        Scene scene3 = new Scene();
        scene3.setNo(3);
        section.addScene(scene3);

        Scene scene4 = new Scene();
        scene4.setNo(4);
        section.addScene(scene4);







        gamePane.sceneProperty().addListener(new ChangeListener<javafx.scene.Scene>() {
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

//        for (Level level: game.getLevels()) {
//            if (level.getNo() < game.getScene().getSection().getLevel().getNo())
//                continue;
//            for (Section section: level.getSections()) {
//                if (level.getNo() == game.getScene().getSection().getLevel().getNo()
//                        && section.getNo() < game.getScene().getSection().getNo()) continue;
//
//                loadSection(section);
//            }
//        }

        loadSection(section);
        loadCharacter(game.getCharacter());

        timer.start();
    }

    private final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            double dx = 0;
            canJump = false;
            if (right) dx += game.getCharacter().getSpeed();
            if (left) dx -= game.getCharacter().getSpeed();
            if (up) {
                up = false;
                dy = game.getCharacter().getJumpAbility() *-1;
            }
            else dy += gravity;

            if (dy > 0) {
                yForward = characterImg.getY() + characterImg.getFitHeight();
                yBackward = characterImg.getY();
            }
            else if (dy < 0){
                yForward = characterImg.getY();
                yBackward = characterImg.getY() + characterImg.getFitHeight();
            }
            if (dx > 0) {
                xForward = characterImg.getX() + characterImg.getFitWidth();
                xBackward = characterImg.getX();
            }
            else if (dx < 0){
                xForward = characterImg.getX();
                xBackward = characterImg.getX() + characterImg.getFitWidth();
            }

            if (occupiedByBlock(xBackward, yForward + dy) || occupiedByBlock(xForward, yForward + dy)) {
                dy = 0;
                canJump = true;
            }
            characterImg.setY(characterImg.getY() + dy);

            if (occupiedByBlock(xForward + dx, yForward + dy) || occupiedByBlock(xForward + dx, yBackward + dy)) {
                dx = 0;
            }
            if (characterImg.getBoundsInParent().getMinX() + dx < 0) {
                dx = 0;
            }
            characterImg.setX(characterImg.getX() + dx);

            if (occupiedByCoin(xForward, yForward)
                    && graphicTiles[(int) (xForward / Tile.TILE_SIZE)][(int) (yForward / Tile.TILE_SIZE)].isVisible()) {
                // todo add coin
                graphicTiles[(int) (xForward / Tile.TILE_SIZE)][(int) (yForward / Tile.TILE_SIZE)].setVisible(false);
            }

            if (dx > 0 && characterImg.getBoundsInParent().getCenterX() > 600) {
                for (Node node : gamePane.getChildren()) {
                    node.relocate(node.getBoundsInParent().getMinX() - dx, node.getBoundsInParent().getMinY());
                }
            }
        }
    };

    private boolean occupiedByBlock(double x, double y) {
        return tiles[(int) (x/ Tile.TILE_SIZE)][(int) (y/ Tile.TILE_SIZE)] instanceof Block;
    }

    private boolean occupiedByCoin(double x, double y) {
        return tiles[(int) (x/ Tile.TILE_SIZE)][(int) (y/ Tile.TILE_SIZE)] instanceof Coin
                || tiles[(int) (x/ Tile.TILE_SIZE)][(int) (y/ Tile.TILE_SIZE)] instanceof CoinBlock;
    }

    private void loadSection(Section section) {
        System.out.println(section.getNo());
        tiles = new Tile[5 * width / Tile.TILE_SIZE][height / Tile.TILE_SIZE];
        graphicTiles = new ImageView[5 * width / Tile.TILE_SIZE][height / Tile.TILE_SIZE];
        for (int idx = 0; idx < section.getScenes().size(); idx++) {
            Scene scene = section.getScenes().get(idx);
            for (Tile component: scene.getComponents()) {
                ImageView imgView;
                imgView = new ImageView(getImage(component.getImageAddress()));
                imgView.setX(component.getX() * Tile.TILE_SIZE + idx * 21 * Tile.TILE_SIZE);
                imgView.setY(component.getY() * Tile.TILE_SIZE);
                imgView.setFitWidth(Tile.TILE_SIZE);
                imgView.setFitHeight(Tile.TILE_SIZE);
                imgView.setScaleX(component.getScaleX());
                imgView.setScaleY(component.getScaleY());
                imgView.setVisible(true);

                System.out.println((int) (imgView.getX() / Tile.TILE_SIZE));

                tiles[(int) (imgView.getX() / Tile.TILE_SIZE)][(int) (imgView.getY() / Tile.TILE_SIZE)] = component;
                graphicTiles[(int) (imgView.getX() / Tile.TILE_SIZE)][(int) (imgView.getY() / Tile.TILE_SIZE)] = imgView;
                gamePane.getChildren().add(imgView);

//                switch (component.getCode()) {
//                    case 'C': coins.put(new Pair(imgView.getX()))
//                }
            }
        }
    }

    private void loadCharacter(Character character) {
        try {
            characterImg = new ImageView(new Image(new FileInputStream(game.getCharacter().getImageAddress())));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        characterImg.setFitWidth(50);
        characterImg.setFitHeight(50);
        characterImg.setX(2 * Tile.TILE_SIZE);
        characterImg.setY(5 * Tile.TILE_SIZE);
        gamePane.getChildren().add(characterImg);
    }

    private Image getImage(String address) {
        Image image = images.get(address);
        if (image == null) {
            try {
                image = new Image(new FileInputStream(address));
                images.put(address, image);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return image;
    }

}
