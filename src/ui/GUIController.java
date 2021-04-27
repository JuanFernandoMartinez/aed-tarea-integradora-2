package ui;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

public class GUIController {
	
	@FXML
	private BorderPane mainPanel;

	private FileChooser fileChooser;
	
	private File file;

	public GUIController() {
		// TODO Auto-generated constructor stub
	}

	@FXML
	public void initialize() throws IOException{
		fileChooser= new FileChooser();
		fileChooser.setTitle("Seleccionar datos");                
		fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("csv", "*.csv"));


	}

	public void chooseFiles(ActionEvent event) {

		file = fileChooser.showOpenDialog(mainPanel.getScene().getWindow());
		if (file != null) {
			labelURL.setText(file.getAbsolutePath());
			sortingComboBox.setDisable(false);
		}
	}

}
