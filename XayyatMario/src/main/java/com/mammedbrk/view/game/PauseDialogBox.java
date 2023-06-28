package com.mammedbrk.view.game;

import com.mammedbrk.listener.StringListener;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PauseDialogBox extends Stage {
    private final List<StringListener> listeners = new ArrayList<>();
    private boolean mute;

    public PauseDialogBox(Scene scene) {
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(scene.getWindow());
        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.BASELINE_CENTER);
        dialogVbox.getChildren().add(new Text("Game Paused"));
        Button continueBtn = new Button("Continue");
        continueBtn.setOnMousePressed(e -> {
            for (StringListener listener: listeners)
                listener.listen("Continue");
            this.close();
        });
        dialogVbox.getChildren().add(continueBtn);
        Button muteBtn = new Button(mute? "Unmute": "Mute");
        muteBtn.setOnMousePressed(e -> {
            mute = !mute;
            muteBtn.setText(mute? "Unmute": "Mute");
            for (StringListener listener: listeners)
                listener.listen("Mute");
        });
        dialogVbox.getChildren().add(muteBtn);
        Button exitBtn = new Button("Exit Game");
        exitBtn.setOnMousePressed(e -> {
            for (StringListener listener: listeners)
                listener.listen("MainMenu");
        });
        dialogVbox.getChildren().add(exitBtn);
        Scene dialogScene = new Scene(dialogVbox, 200, 200);
        this.setScene(dialogScene);
    }

    public void addListener(StringListener listener) {
        listeners.add(listener);
    }
}
