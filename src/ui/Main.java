package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	private GUIController gui;

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			gui = new GUIController();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLFiles/ContainerBorderPane.fxml"));
			fxmlLoader.setController(gui);
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root,1280,800);
			scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.centerOnScreen();
			primaryStage.setResizable(false);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
