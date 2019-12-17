package dad.javafx.habilidades;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HabilidadesController implements Initializable {

	// model

	private HabilidadesModel model = new HabilidadesModel();

	// view
	@FXML
	private BorderPane view;

	@FXML
	private TableView<Conocimiento> conocimientosTableView;

	@FXML
	private TableColumn<Conocimiento, String> denominacionTableColumn;

	@FXML
	private TableColumn<Conocimiento, Nivel> nivelTableColumn;

	@FXML
	private Button anadirConocimientoButton;

	@FXML
	private Button anadirIdiomaButton;

	@FXML
	private Button eliminarButton;

	public HabilidadesController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("HabilidadesView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// la hacemos editable
		conocimientosTableView.setEditable(true);

		denominacionTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		nivelTableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(Nivel.values()));

		// rellenamos laa tabla conocimiento
		denominacionTableColumn.setCellValueFactory(value -> value.getValue().denominacionProperty());

		nivelTableColumn.setCellValueFactory(value -> value.getValue().nivelProperty());

		conocimientosTableView.itemsProperty().bind(model.conocimientoProperty());

	}

	@FXML
	public void onAnadirConocimiento() {
		// creamos el dialog con su titulo y header text
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("Nuevo Conocimiento");

		// declaramos los tipo de botones

		ButtonType anadirButton = new ButtonType("Crear", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(anadirButton, ButtonType.CANCEL);
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("dad/javafx/resources/cv64x64.png"));

		// creamos los campos
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		Button xButton = new Button("X");

		TextField denominacionTextField = new TextField();
		Label denominacionDialogLabel = new Label("Denominacion");
		Label nivelDialogLabel = new Label("Nivel");

		ComboBox<Nivel> nivelComboBox = new ComboBox<Nivel>();

		nivelComboBox.getItems().addAll(Nivel.values());
		HBox box = new HBox(nivelComboBox, xButton);
		box.setSpacing(5);

		grid.add(denominacionDialogLabel, 0, 0);
		grid.add(denominacionTextField, 1, 0);
		grid.add(nivelDialogLabel, 0, 1);
		grid.add(box, 1, 1);

		xButton.setOnAction(e -> nivelComboBox.getSelectionModel().select(-1));

		// activar o desactivar el boton a�adir dependiendo de si se ha a�adido un
		// numero de telefono o no

		// metemos el grid pane en nuestro dialog
		dialog.getDialogPane().setContent(grid);

		xButton.setOnAction(e -> nivelComboBox.getSelectionModel().select(-1));
		
		Optional<ButtonType> result = dialog.showAndWait();
		if (result.get() == anadirButton) {
			Conocimiento conocimiento = new Conocimiento();
			conocimiento.setDenominacion(denominacionTextField.getText());
			conocimiento.setNivel(nivelComboBox.getSelectionModel().getSelectedItem());
			model.getConocimiento().add(conocimiento);
		}
	}

	@FXML
	public void onEliminar() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmacion de eliminacion");
		alert.setHeaderText("Si elimina la fila no podra recuperarla");
		alert.setContentText("¿Aun asi quiere eliminarla?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Conocimiento conocimiento = new Conocimiento();
			conocimiento = conocimientosTableView.getSelectionModel().getSelectedItem();
			model.conocimientoProperty().remove(conocimiento);
		} else {
			Alert alertInformation = new Alert(AlertType.INFORMATION);
			alertInformation.setTitle("Cancelacion de eliminacion");
			alertInformation.setHeaderText("Ha decidido no eliminar la fila");
			alertInformation.setContentText("Los datos se mantendran en su estado original");

			alertInformation.showAndWait();
		}

	}

	@FXML
	public void onAnadirIdioma() {
		// creamos el dialog con su titulo y header text
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("Nuevo Conocimiento");

		// declaramos los tipo de botones

		ButtonType anadirButton = new ButtonType("Crear", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(anadirButton, ButtonType.CANCEL);
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("dad/javafx/resources/cv64x64.png"));

		// creamos los campos
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		Button xButton = new Button("X");

		TextField denominacionTextField = new TextField();
		TextField certificacionTextField = new TextField();
		Label certificacionLabel = new Label("Certificacion");
		Label denominacionDialogLabel = new Label("Denominacion");
		Label nivelDialogLabel = new Label("Nivel");

		ComboBox<Nivel> nivelComboBox = new ComboBox<Nivel>();
		nivelComboBox.getItems().addAll(Nivel.values());
		HBox box = new HBox(nivelComboBox, xButton);
		box.setSpacing(5);

		grid.add(denominacionDialogLabel, 0, 0);
		grid.add(denominacionTextField, 1, 0);
		grid.add(nivelDialogLabel, 0, 1);
		grid.add(box, 1, 1);
		grid.add(certificacionLabel, 0, 2);
		grid.add(certificacionTextField, 1, 2);

		xButton.setOnAction(e -> nivelComboBox.getSelectionModel().select(-1));

		// activar o desactivar el boton a�adir dependiendo de si se ha a�adido un
		// numero de telefono o no

		// metemos el grid pane en nuestro dialog
		dialog.getDialogPane().setContent(grid);

		// accion al apretar a�adir button

		Optional<ButtonType> result = dialog.showAndWait();
		if (result.get() == anadirButton) {
			Idioma idioma = new Idioma();
			idioma.setCertificacion(certificacionTextField.getText());
			Conocimiento conocimiento = new Conocimiento();
			conocimiento.setDenominacion(denominacionTextField.getText());
			conocimiento.setNivel(nivelComboBox.getValue());
			conocimiento.setIdioma(idioma);
			model.conocimientoProperty().add(conocimiento);
		}
	}

	public HabilidadesModel getModel() {
		return model;
	}

	public BorderPane getView() {
		return view;
	}

}
