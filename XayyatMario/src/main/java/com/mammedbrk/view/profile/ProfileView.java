package com.mammedbrk.view.profile;

import com.mammedbrk.current.Current;
import com.mammedbrk.event.CharacterEvent;
import com.mammedbrk.listener.CharacterChooseListener;
import com.mammedbrk.listener.StringListener;
import com.mammedbrk.model.Character;
import com.mammedbrk.view.character.CharacterCardView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileView implements Initializable {
    @FXML
    private Button chooseBtn;
    @FXML
    private BorderPane cardPane;
    @FXML
    private ImageView characterImg;
    @FXML
    private Label score;
    @FXML
    private Label coin;
    @FXML
    private TilePane tilePane;
    @FXML
    private Label username;

    private CharacterChooseListener characterChooseListener;
    private List<StringListener> listeners = new LinkedList<>();

    // Methods
    public void addListener(StringListener listener) {
        listeners.add(listener);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUserInfo();

        for (Character character: Current.user.getCharacters()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.mammedbrk/fxml/character/character-card-view.fxml"));
            try {
                tilePane.getChildren().add(loader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            setItemsInCard(loader.getController(), character);

            Pane card = loader.getRoot();

            /*if (Current.user.getChosenCharacter().equals(character)) {
                FXMLLoader mainCardLoader = new FXMLLoader(getClass().getResource("/com.mammedbrk/fxml/character/character-card-view.fxml"));
                Pane selectedPane;
                try {
                    selectedPane = mainCardLoader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                setItemsInCard(mainCardLoader.getController(), character);

                selectedPane.setScaleX(1.5);
                selectedPane.setScaleY(1.5);
                cardPane.setCenter(selectedPane);
            }*/

            card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    FXMLLoader mainCardLoader = new FXMLLoader(getClass().getResource("/com.mammedbrk/fxml/character/character-card-view.fxml"));
                    Pane selectedPane;
                    try {
                        selectedPane = mainCardLoader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    setItemsInCard(mainCardLoader.getController(), character);

                    selectedPane.setScaleX(1.5);
                    selectedPane.setScaleY(1.5);
                    cardPane.setCenter(selectedPane);

                    /*if (Current.user.getChosenCharacter().equals(character)) {
                        chooseBtn.setText("Chose!");
                        chooseBtn.setDisable(true);
                    }
                    else {
                        chooseBtn.setText("Choose");
                        chooseBtn.setDisable(false);
                    }*/


                    chooseBtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
//                            characterChooseListener.listen(new CharacterEvent(this, character));

                            chooseBtn.setText("Chose!");
                            chooseBtn.setDisable(true);

                            setUserInfo();
                        }
                    });
                }
            });
        }
    }

    private void setUserInfo() {
        username.setText(Current.user.getUsername());
        score.setText(String.valueOf(Current.user.getScore()));
        coin.setText(String.valueOf(Current.user.getCoins()));
        /*try {
            characterImg.setImage(new Image(new FileInputStream(Current.user.getChosenCharacter().getImageAddress())));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }*/
    }

    private void setItemsInCard(CharacterCardView characterCardView, Character character) {
        try {
            characterCardView.getCharacterImg().setImage(new Image(new FileInputStream(character.getImageAddress())));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        characterCardView.setCharacterNameLabel(character.getName());
        characterCardView.setSpeedLabel(String.valueOf(character.getSpeed()));
        characterCardView.setJumpLabel(String.valueOf(character.getJumpAbility()));
        characterCardView.setCollectLabel(String.valueOf(character.getCollectAbility()));
        characterCardView.setShootLabel(String.valueOf(character.getShootAbility()));
        characterCardView.makeCharacterPriceLabelInvisible();
    }

    public void backBtnClicked(ActionEvent event) {
        for (StringListener listener: listeners) {
            listener.listen("MainMenu");
        }
    }


    // Getters and setters

    public CharacterChooseListener getCharacterChooseListener() {
        return characterChooseListener;
    }

    public void setCharacterChooseListener(CharacterChooseListener characterChooseListener) {
        this.characterChooseListener = characterChooseListener;
    }

    public List<StringListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<StringListener> listeners) {
        this.listeners = listeners;
    }
}
