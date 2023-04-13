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
        Current.game.setScene(scene);

        // -------------------------------------

        this.setStyle("-fx-background-color: #282828;");
        try {
            character = new ImageView(new Image(new FileInputStream(Current.game.getCharacter().getImageAddress())));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

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

        loadSectionGraphics();
        timer.start();
    }

    private void loadSectionGraphics() {
        gTiles = new ImageView[5*(int) (WIDTH/Tile.TILE_SIZE)][(int) (HEIGHT/Tile.TILE_SIZE)];
        List<ImageView> list = sectionLoadListener.listen();
        this.getChildren().clear();
        if (list == null) {
            // todo game finished
            return;
        }
        for (ImageView gTile: list) {
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

            CharacterMovementEvent characterMovementEvent
                    = characterCollisionListener.listen(new CharacterCollisionEvent(
                    this,
                    dx, dy,
                    xFront, xBack,
                    yFront, yBack));

            canJump = characterMovementEvent.isCanJump();

            if (!characterMovementEvent.isVisible()
                    && gTiles[(int) ((xFront + xBack) / 2 / Tile.TILE_SIZE)][(int) ((yFront + yBack) / 2 / Tile.TILE_SIZE)] != null) {
                gTiles[(int) ((xFront + xBack) / 2 / Tile.TILE_SIZE)][(int) ((yFront + yBack) / 2 / Tile.TILE_SIZE)]
                        .setVisible(characterMovementEvent.isVisible());
            }

            if (characterMovementEvent.isPowerUp()) {
                gTiles[(int) ((xFront + xBack) / 2 / Tile.TILE_SIZE)][(int) ((yFront + dy) / Tile.TILE_SIZE)]
                        .setVisible(characterMovementEvent.isVisible());
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

            if (dx > 0 && character.getBoundsInParent().getCenterX() > WIDTH/2) {
                for (Node node : GameView.this.getChildren()) {
                    node.relocate(node.getBoundsInParent().getMinX() - dx, node.getBoundsInParent().getMinY());
                }
            }
        }
    };


    /*private Game game; // todo how to get game? :think
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

    public GameView() {
        game = new Game();
        game.setCharacter(Current.user.chosenCharacter());

        Section section = new Section(100);
        section.setScenes(new ArrayList<>());
        section.setNo(1);

        Scene scene1 = new Scene();
        scene1.setComponents(new ArrayList<>());
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
        scene2.setComponents(new ArrayList<>());
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
        scene3.setComponents(new ArrayList<>());
        scene3.setNo(3);
        section.addScene(scene3);

        Scene scene4 = new Scene();
        scene4.setComponents(new ArrayList<>());
        scene4.setNo(4);
        section.addScene(scene4);




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
            if (left  && characterImg.getBoundsInParent().getMinX() > 0) dx -= game.getCharacter().getSpeed();
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
//            if (characterImg.getBoundsInParent().getMinX() + dx < 0) {
//                dx = 0;
//            }
            characterImg.setX(characterImg.getX() + dx);

            if (occupiedByCoin(xForward, yForward)
                    && graphicTiles[(int) (xForward / Tile.TILE_SIZE)][(int) (yForward / Tile.TILE_SIZE)].isVisible()) {
                // todo add coin
                graphicTiles[(int) (xForward / Tile.TILE_SIZE)][(int) (yForward / Tile.TILE_SIZE)].setVisible(false);
            }


            System.out.println(xBackward + " " + characterImg.getBoundsInParent().getCenterX());

            if (dx > 0 && characterImg.getBoundsInParent().getCenterX() >= 700) {
                for (Node node : GameView.this.getChildren()) {
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
                imgView.setScaleX(component.scaleX());
                imgView.setScaleY(component.scaleY());
                imgView.setVisible(true);

                tiles[(int) (imgView.getX() / Tile.TILE_SIZE)][(int) (imgView.getY() / Tile.TILE_SIZE)] = component;
                graphicTiles[(int) (imgView.getX() / Tile.TILE_SIZE)][(int) (imgView.getY() / Tile.TILE_SIZE)] = imgView;
                this.getChildren().add(imgView);

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
        this.getChildren().add(characterImg);
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
    }*/
}
