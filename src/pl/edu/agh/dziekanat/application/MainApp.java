package pl.edu.agh.dziekanat.application;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.edu.agh.dziekanat.email.EmailSender;
import pl.edu.agh.dziekanat.person.PersonUtil;

public class MainApp extends Application {

    // Creating a static root to pass to the controller
    private static final BorderPane root = new BorderPane();
    private static MenuBar bar;

    /**
     * Just a root getter for the controller to use
     *
     * @return BorderPane
     */
    public static BorderPane getRoot() {
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        // loading FXML resources
        // note that we don't need PaneTwo in this class
        URL menuBarUrl = getClass().getResource("/pl/edu/agh/dziekanat/application/form/MenuPane.fxml");
        bar = FXMLLoader.load(menuBarUrl);

        URL paneOneUrl = getClass().getResource("/pl/edu/agh/dziekanat/application/form/LoginPane.fxml");
        AnchorPane paneOne = FXMLLoader.load(paneOneUrl);
        // constructing our scene using the static root
        //root.setTop(bar);
        root.setCenter(paneOne);

        Scene scene = new Scene(root, 1024, 768);
        scene
                .getStylesheets()
                .add(getClass()
                        .getResource("/pl/edu/agh/dziekanat/application/form/application.css")
                        .toExternalForm());

        primaryStage.setTitle("Menedżer dziekanatu");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());

        System.out.println("konieczny raval <koniecznyraval@gmail.com>".replaceAll(".*<(.*?)>", "'$1'"));
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void showMenu() {
        root.setTop(bar);
    }

}
