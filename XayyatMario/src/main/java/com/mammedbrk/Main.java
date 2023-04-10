package com.mammedbrk;

import com.mammedbrk.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(new MainView()));
        stage.setFullScreen(true);
        stage.show();
    }
}
