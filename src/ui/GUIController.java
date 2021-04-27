package ui;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import model.MasterClass;

public class GUIController {

	@FXML
	private BorderPane mainPanel;

	@FXML
	private Button addFileButton;

	@FXML
	private ComboBox<String> sortingComboBox;

	@FXML
	private ProgressBar progressBar;

	@FXML
	private TextField labelURL;

	private FileChooser fileChooser;

	private File file;

	private MasterClass masterClass;



	public GUIController() {
		masterClass = new MasterClass();
	}

	@FXML
	public void initialize() throws IOException{
		fileChooser= new FileChooser();
		fileChooser.setTitle("Seleccionar datos");                
		fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("csv", "*.csv"));

	}

	@FXML
	public void openAddFileCSV(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLFiles/AddcsvFileAnchorPane.fxml"));
		fxmlLoader.setController(this);
		BorderPane pane = fxmlLoader.load();

		mainPanel.setCenter(pane);
	}

	@FXML
	public void openAddAnElement(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLFiles/AddElementAnchorPane.fxml"));
		fxmlLoader.setController(this);
		BorderPane pane = fxmlLoader.load();

		mainPanel.setCenter(pane);
	}

	@FXML
	public void openSearchElement(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLFiles/SearchAnchorPane.fxml"));
		fxmlLoader.setController(this);
		BorderPane pane = fxmlLoader.load();

		mainPanel.setCenter(pane);
		

		sortingComboBox.getItems().add("Total Rebounds");
		sortingComboBox.getItems().add("Offensive Rebounds");
		sortingComboBox.getItems().add("Blocks");
		sortingComboBox.getItems().add("True Shooting %");
		sortingComboBox.getItems().add("Free Throw %");
	}

	public void addFile(ActionEvent event) throws IOException {

		file = fileChooser.showOpenDialog(mainPanel.getScene().getWindow());
		if (file != null) {
			labelURL.setText(file.getAbsolutePath());
		}

		addFileButton.setDisable(true);

		masterClass.readFiles(file);

		addFileButton.setDisable(true);
	}

	public void addElement(ActionEvent event) {

	}

}
