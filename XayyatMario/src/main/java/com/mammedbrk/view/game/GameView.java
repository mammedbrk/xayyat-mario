package com.mammedbrk.view.game;

import com.mammedbrk.config.Config;
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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameView extends Pane {
    private static double WIDTH = Integer.parseInt(Config.getInstance().getProperty("width")) * Integer.parseInt(Config.getInstance().getProperty("tile_size"));;
    private static double HEIGHT = Integer.parseInt(Config.getInstance().getProperty("height")) * Integer.parseInt(Config.getInstance().getProperty("tile_size"));
    private boolean left, right, jump, sit;
    private final List<StringListener> listeners = new LinkedList<>();
    private MediaPlayer mediaPlayer;


    public GameView() {
        this.sceneProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                newValue.setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case W -> jump = true;
                        case A -> left = true;
                        case D -> right = true;
                        case SPACE -> sit = true;
                        case ESCAPE -> {
                            for (StringListener listener: listeners)
                                listener.listen("Pause");
                            PauseDialogBox dialogBox = new PauseDialogBox(this.getScene());
                            dialogBox.addListener(new StringListener() {
                                @Override
                                public void listen(String s) {
                                    if (s.equals("Continue")) {
                                        for (StringListener listener: listeners)
                                            listener.listen("Pause");
                                    }
                                    if (s.equals("Mute")) {
                                        if (mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING))
                                            mediaPlayer.pause();
                                        else mediaPlayer.play();
                                    }
                                    if (s.equals("Exit")) {

                                    }
                                }
                            });
                            dialogBox.show();
                        }
                    }
                });

                newValue.setOnKeyReleased(event -> {
                    switch (event.getCode()) {
                        case A -> left = false;
                        case D -> right = false;
                        case SPACE -> sit = false;
                    }
                });
            }
        });

        playMusic("src/main/resources/com.mammedbrk/music/main_theme.mp3");
    }

    private void playMusic(String path) {
        Media media = new Media(Paths.get(path).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public MovementEvent handleInput() {
        if (jump) {
            jump = false;
            return new MovementEvent(right ^ left, left, true, sit);
        }
        return new MovementEvent(right ^ left, left, false, sit);
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
        if (game.getMarioState() > 0 && !game.getMario().isSit()) {
            marioImageView.setFitHeight(2 * Component.SIZE);
        }
        this.getChildren().add(marioImageView);
        if (marioImageView.getBoundsInParent().getMaxX() > WIDTH/2 && (game.currentSection().getLength() * Component.SIZE) + this.getTranslateX() > WIDTH)
            this.setTranslateX(Math.min(this.getTranslateX(), WIDTH/2 - marioImageView.getBoundsInParent().getCenterX()));
        // todo restrict going out of bounds
    }

    private ImageView getImageView(Component component) {
        ImageView imageView = new ImageView();
        imageView.setImage(ComponentImageFactory.getImage(component.getClass().getSimpleName()));
        imageView.setX(component.getX() * Component.SIZE);
        imageView.setY((-5 + Integer.parseInt(Config.getInstance().getProperty("height")) - component.getY()) * Component.SIZE);
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

    public void addListener(StringListener listener) {
        listeners.add(listener);
    }
}
