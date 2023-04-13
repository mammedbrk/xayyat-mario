package com.mammedbrk.view.menu;

import com.mammedbrk.current.Current;
import com.mammedbrk.listener.Listener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class MainMenuHeaderView implements Initializable {
    @FXML
    private Label username;
    @FXML
    private Label score;
    @FXML
    private Label coin;
    @FXML
    private Pane profileBtn;
    @FXML
    private ImageView characterImg;
    private List<Listener<String>> listeners = new LinkedList<>();

    // Methods

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(Current.user.getUsername());
        score.setText(String.valueOf(Current.user.getMaxScore()));
        coin.setText(String.valueOf(Current.user.getNumOfCoins()));
        try {
            characterImg.setImage(new Image(new FileInputStream(Current.user.chosenCharacter().getImageAddress())));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addListener(Listener<String> listener) {
        listeners.add(listener);
    }

    public void profileBtnClicked(MouseEvent mouseEvent) {
        for (Listener<String> listener : listeners) {
            listener.listen("ProfileView");
        }
    }

    public void shopBtnClicked(ActionEvent event) {
        for (Listener<String> listener : listeners) {
            listener.listen("ShopView");
        }
    }

    // Getters and setters

    public List<Listener<String>> getListeners() {
        return listeners;
    }

    public void setListeners(List<Listener<String>> listeners) {
        this.listeners = listeners;
    }
}
