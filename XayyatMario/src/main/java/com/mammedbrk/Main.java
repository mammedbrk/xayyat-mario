package com.mammedbrk;

import com.mammedbrk.access.CharacterAccess;
import com.mammedbrk.access.UserAccess;
import com.mammedbrk.controller.UserController;
import com.mammedbrk.event.FormEvent;
import com.mammedbrk.model.Character;
import com.mammedbrk.model.User;
import com.mammedbrk.model.gamecomponent.block.Block;
import com.mammedbrk.model.gamecomponent.block.OrdinaryBlock;
import com.mammedbrk.model.tile.Tile;
import com.mammedbrk.model.tile.TileType;
import com.mammedbrk.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

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
