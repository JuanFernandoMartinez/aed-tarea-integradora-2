package ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

	@FXML
	private TextField initialRSearchTextField;
	
	@FXML
	private TextField finalRSearchTextField;

	@FXML
	private TableView<Record> dataTableView;

	@FXML
	private TableColumn<Record, String> nameColumn;

	@FXML
	private TableColumn<Record, String> ageColumn;

	@FXML
	private TableColumn<Record, String> teamColumn;

	@FXML
	private TableColumn<Record, String> totalReboundsColumn;

	@FXML
	private TableColumn<Record, String> offensiveReboundsColumn;

	@FXML
	private TableColumn<Record, String> blocksColumn;

	@FXML
	private TableColumn<Record, String> trueShootingColumn;

	@FXML
	private TableColumn<Record, String> freeThrowColumn;
	
	@FXML
	private Label timeSearchLabel;
	
	private final ObservableList<Record> dataList = FXCollections.observableArrayList();

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

	@SuppressWarnings("unchecked")
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
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
        totalReboundsColumn.setCellValueFactory(new PropertyValueFactory<>("totalRebounds"));
        offensiveReboundsColumn.setCellValueFactory(new PropertyValueFactory<>("offensiveRebounds"));
        blocksColumn.setCellValueFactory(new PropertyValueFactory<>("blocks"));
        trueShootingColumn.setCellValueFactory(new PropertyValueFactory<>("trueShooting"));
        freeThrowColumn.setCellValueFactory(new PropertyValueFactory<>("freeThrow"));
 
        dataTableView.setItems(dataList);
        dataTableView.getColumns().setAll(
        		nameColumn, ageColumn, teamColumn, totalReboundsColumn,
        		offensiveReboundsColumn, blocksColumn, trueShootingColumn, freeThrowColumn);
 
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

	public void search(ActionEvent event) {
		
		Double initialValue = Double.parseDouble(initialRSearchTextField.getText());
		Double finalValue = Double.parseDouble(finalRSearchTextField.getText());
		
		if(sortingComboBox.getSelectionModel().isEmpty())
			JOptionPane.showMessageDialog(null, "Select a valid search standard", "Invalid standard", JOptionPane.PLAIN_MESSAGE, null);
		else if(!(initialValue<=finalValue))
			JOptionPane.showMessageDialog(null, "The initial range's value must to be less than the final range's value", "Invalid range", JOptionPane.PLAIN_MESSAGE, null);
		else {
			long time = System.currentTimeMillis();
			dataList.clear();
			ArrayList<String> data = new ArrayList<>();
			try {
				switch (sortingComboBox.getSelectionModel().getSelectedItem()) {

				case "Total Rebounds":
					data = masterClass.searchByRange(MasterClass.TRB, initialValue, finalValue);
					break;
				case "Offensive Rebounds":
					data = masterClass.searchByRange(MasterClass.ORB, initialValue, finalValue);
					break;
				case "Blocks":
					data = masterClass.searchByRange(MasterClass.BLK, initialValue, finalValue);
					break;
				case "True Shooting %":
					data = masterClass.searchByRange(MasterClass.TS, initialValue, finalValue);
					break;
				case "Free Throw %":
					data = masterClass.searchByRange(MasterClass.FTR, initialValue, finalValue);
					break;

				}

				time = System.currentTimeMillis() - time;
				
				if(data.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No results found", "No results", JOptionPane.PLAIN_MESSAGE, null);
				}
				
				for(int i = 0; i<data.size(); i++) {
			
					Record record = new Record(data.get(i).split(",")[2], data.get(i).split(",")[3], data.get(i).split(",")[1],
							data.get(i).split(",")[12], data.get(i).split(",")[10], data.get(i).split(",")[15],data.get(i).split(",")[7],data.get(i).split(",")[9]);
	                dataList.add(record);
					
				}	
				
				timeSearchLabel.setText(time+" ms");
				
				sortingComboBox.getSelectionModel().clearSelection();
				initialRSearchTextField.clear();
				finalRSearchTextField.clear();
				
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Input a valid value", "Invalid Value", JOptionPane.PLAIN_MESSAGE, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void endProgram(ActionEvent event) {
		System.exit(0);
	}
	
	public void help(ActionEvent event) {
		JOptionPane.showMessageDialog(null, "made with blood sweat and tears!!", "Help", JOptionPane.PLAIN_MESSAGE, null);
	}

	public class Record {

		private SimpleStringProperty name, age, team, totalRebounds, offensiveRebounds, blocks, trueShooting, freeThrow;

		public String getName() {
			return name.get();
		}

		public String getAge() {
			return age.get();
		}

		public String getTeam() {
			return team.get();
		}

		public String getTotalRebounds() {
			return totalRebounds.get();
		}

		public String getOffensiveRebounds() {
			return offensiveRebounds.get();
		}

		public String getBlocks() {
			return blocks.get();
		}

		public String getTrueShooting() {
			return trueShooting.get();
		}

		public String getFreeThrow() {
			return freeThrow.get();
		}

		public Record(String name, String age, String team,
				String totalRebounds, String offensiveRebounds, String blocks,
				String trueShooting, String freeThrow) {
			
			this.name = new SimpleStringProperty(name);
			this.age = new SimpleStringProperty(age);
			this.team = new SimpleStringProperty(team);
			this.totalRebounds = new SimpleStringProperty(totalRebounds);
			this.offensiveRebounds = new SimpleStringProperty(offensiveRebounds);
			this.blocks = new SimpleStringProperty(blocks);
			this.trueShooting = new SimpleStringProperty(trueShooting);
			this.freeThrow = new SimpleStringProperty(freeThrow);
		}

	}

}
