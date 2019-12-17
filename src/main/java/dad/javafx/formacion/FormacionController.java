package dad.javafx.formacion;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class FormacionController implements Initializable {
	//model
	private FormacionModel model=new FormacionModel();
	
	
	//vista
	 @FXML
	    private BorderPane view;

	    @FXML
	    private TableView<Titulo> formacionTableView;

	    @FXML
	    private TableColumn<Titulo, LocalDate> desdeTableColumn;

	    @FXML
	    private TableColumn<Titulo, LocalDate> hastaTableColumn;

	    @FXML
	    private TableColumn<Titulo, String> denominacionTableColumn;

	    @FXML
	    private TableColumn<Titulo, String> organizadorTableColumn;

	    @FXML
	    private Button anadirButton;

	    @FXML
	    private Button eliminarButton;
	
	public FormacionController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("FormacionView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//hacemos la tabla editablee
		formacionTableView.setEditable(true);
		
		desdeTableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));//esto es para obligar al usuario a escribir string en el formato local date
		
		hastaTableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		
		denominacionTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		organizadorTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		//rellenado tabla formacion
		desdeTableColumn.setCellValueFactory(value->value.getValue().desdeProperty());
		
		hastaTableColumn.setCellValueFactory(value->value.getValue().hastaProperty());
		
		denominacionTableColumn.setCellValueFactory(value->value.getValue().denominacionProperty());
		
		organizadorTableColumn.setCellValueFactory(value->value.getValue().organizadorProperty());
		
		formacionTableView.itemsProperty().bindBidirectional(model.titulosProperty());
		
		
		
		
		
		
	}
	
	@FXML
	public void onAnadir() {
		//creamos el dialog con su titulo y header text
				Dialog<ButtonType> dialog=new Dialog<>();
				dialog.setTitle("Nuevo titulo");
				
				
				//declaramos los tipo de botones
				
				ButtonType crearButton=new ButtonType("Crear",ButtonData.OK_DONE);
				dialog.getDialogPane().getButtonTypes().addAll(crearButton,ButtonType.CANCEL);
				Stage stage=(Stage)dialog.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("dad/javafx/resources/cv64x64.png"));
				
				//creamos los campos
				GridPane grid =new GridPane();
				grid.setHgap(10);
				grid.setVgap(10);
				grid.setPadding(new Insets(20, 150, 10, 10));
				
				TextField denominacionTextField=new TextField();
				TextField organizadorTextField=new TextField();
				DatePicker desdeDatePicker=new DatePicker();
				DatePicker hastaDatePicker=new DatePicker();
				
				
				
				Label denominacionDialogLabel=new Label("Denominacion");
				Label organizadorDialogLabel=new Label("Organizador");
				Label desdeDialogLabel=new Label("Desde");
				Label hastaDialogLabel=new Label("Hasta");
				
				
				
				
				grid.add(denominacionDialogLabel, 0, 0);
				grid.add(denominacionTextField, 1, 0);
				grid.add(organizadorDialogLabel, 0, 1);
				grid.add(organizadorTextField, 1, 1);
				grid.add(desdeDialogLabel, 0, 2);
				grid.add(desdeDatePicker, 1, 2);
				grid.add(hastaDialogLabel, 0, 3);
				grid.add(hastaDatePicker, 1, 3);
				
				
				//activar o desactivar el boton a�adir dependiendo de si se ha a�adido un numero de telefono o no
				
			
				
				//metemos el grid pane en nuestro dialog
				dialog.getDialogPane().setContent(grid);
				
				//accion al apretar a�adir button
				
				
					Optional<ButtonType> result = (Optional<ButtonType>) dialog.showAndWait();
					if (result.get()==crearButton){
						if(desdeDatePicker.getValue().isAfter(hastaDatePicker.getValue())) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error de datos");
							alert.setHeaderText("Fechas incorrectas");
							alert.setContentText("La fecha en \"Desde\" ha de ser menor que la fecha en \"Hasta\"");

							alert.showAndWait();
						}else {
						Titulo titulo=new Titulo();
						titulo.setDesde(desdeDatePicker.getValue());
						titulo.setHasta(hastaDatePicker.getValue());
						titulo.setDenominacion(denominacionTextField.getText());
						titulo.setOrganizador(organizadorTextField.getText());
						model.getTitulos().add(titulo);
						}
					}
	}
	
	@FXML
	public void onEliminar() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmacion de eliminacion");
		alert.setHeaderText("Si elimina la fila no podra recuperarla");
		alert.setContentText("¿Aun asi quiere eliminarla?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		Titulo tituloEliminar=new Titulo();
		tituloEliminar=formacionTableView.getSelectionModel().getSelectedItem();
		model.titulosProperty().remove(tituloEliminar);
		}else {
			Alert alertInformation = new Alert(AlertType.INFORMATION);
			alertInformation.setTitle("Cancelacion de eliminacion");
			alertInformation.setHeaderText("Ha decidido no eliminar la fila");
			alertInformation.setContentText("Los datos se mantendran en su estado original");

			alertInformation.showAndWait();
		}
	}

	public FormacionModel getModel() {
		return model;
	}

	public BorderPane getView() {
		return view;
	}
	

}
