package pl.edu.agh.dziekanat.application.controller;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import pl.edu.agh.dziekanat.application.MainApp;

/**
 * Menu Panel
 */
public class MenuController {

    @FXML // fx:id="displayOne"
    private MenuItem displayOne; // Value injected by FXMLLoader

    @FXML // fx:id="displayTwo"
    private MenuItem displayTwo; // Value injected by FXMLLoader

    @FXML // fx:id="displayThree"
    private MenuItem displayThree;

    @FXML
    void switchToOne(ActionEvent event) {
        this.switchTo("/pl/edu/agh/dziekanat/application/form/EmptyPane.fxml");
    }

    @FXML
    void switchToLessonFrequency(ActionEvent event) {
        this.switchTo("/pl/edu/agh/dziekanat/application/form/LessonFrequencyPane.fxml");
    }

    @FXML
    void switchToTwo(ActionEvent event) {
        this.switchTo("/pl/edu/agh/dziekanat/application/form/EmptyPane.fxml");
    }

    @FXML
    void switchToMaintainPerson(ActionEvent event) {
        this.switchTo("/pl/edu/agh/dziekanat/application/form/PersonPane.fxml");
    }

    @FXML
    void switchToEmailAction(ActionEvent event) {
        this.switchTo("/pl/edu/agh/dziekanat/application/form/MailToPane.fxml");

    }

    @FXML
    void doHelloWorld(ActionEvent e) {
        System.out.println("Hello World");
    }

    private void switchTo(String resource) {
        try {
            URL panePersonUrl = getClass().getResource(resource);
            AnchorPane pane = FXMLLoader.load(panePersonUrl);
            BorderPane border = MainApp.getRoot();
            border.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
