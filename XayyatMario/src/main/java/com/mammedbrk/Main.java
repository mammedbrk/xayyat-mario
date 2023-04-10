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
        MainView mainView = new MainView();
        stage.setScene(new Scene(mainView));
        stage.show();
    }
}
