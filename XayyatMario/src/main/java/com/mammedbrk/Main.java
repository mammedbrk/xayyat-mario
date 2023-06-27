package com.mammedbrk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mammedbrk.current.Current;
import com.mammedbrk.model.User;
import com.mammedbrk.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Current.user = new ObjectMapper().readValue(new FileReader("json/user/1.json"), User.class);
        stage.setScene(new Scene(new MainView(), 1300, 800));
//        stage.setResizable(false);
        stage.show();
    }
}
