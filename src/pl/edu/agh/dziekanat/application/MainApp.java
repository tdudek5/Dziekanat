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


public class MainApp extends Application {
  
  // Creating a static root to pass to the controller
  private static BorderPane root = new BorderPane();
  private static MenuBar bar;

  /**
   * Just a root getter for the controller to use
   */
  public static BorderPane getRoot() {
    return root;
  }


  @Override
  public void start(Stage primaryStage) throws IOException {
    
    // loading FXML resources
    // note that we don't need PaneTwo in this class
    
    URL menuBarUrl = getClass().getResource("form/MenuPane.fxml");
    bar = FXMLLoader.load( menuBarUrl );

    URL paneOneUrl = getClass().getResource("form/LoginPane.fxml");
    AnchorPane paneOne = FXMLLoader.load( paneOneUrl );



    // constructing our scene using the static root

    //root.setTop(bar);
    root.setCenter(paneOne);
    
    Scene scene = new Scene(root, 1024, 768);
    scene
      .getStylesheets()
      .add(getClass()
      .getResource("form/application.css")
      .toExternalForm());
    
    primaryStage.setTitle("Menedżer dziekanatu ");
    primaryStage.setScene(scene);
    primaryStage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }
  
  public static void showMenu(){
	  root.setTop(bar);
  }
    
}