package dad.javafx.contacto;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ContactoController<T> implements Initializable {

	// model
	private ContactoModel model = new ContactoModel();
	


	// vista
	@FXML
    private SplitPane view;

    @FXML
    private TitledPane telefonosTitledPane;

    @FXML
    private TableView<Telefono> telefonoTableView;

    @FXML
    private TableColumn<Telefono, String> numeroTableColumn;

    @FXML
    private TableColumn<Telefono, TipoTelefono> tipoTableColumn;

    @FXML
    private Button anadirTelefonoButton;

    @FXML
    private Button eliminarTelefonoButton;

    @FXML
    private TitledPane correoTitledPane;

    @FXML
    private TableView<Email> correoTableView;

    @FXML
    private TableColumn<Email,String> emailTableColumn;

    @FXML
    private Button anadirCorreoButton;

    @FXML
    private Button eliminarCorreoButton;

    @FXML
    private TitledPane websTitledPane;

    @FXML
    private TableView<Web> websTableView;

    @FXML
    private TableColumn<Web, String> webTableColumn;

    @FXML
    private Button anadirWebButton;

    @FXML
    private Button eliminarWebButton;
    
   



    
    

	public ContactoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ContactoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//hacemos email table editable
		correoTableView.setEditable(true);
		
		emailTableColumn.setCellFactory(
				TextFieldTableCell.forTableColumn());
		
		//rellenando columna email
		emailTableColumn.setCellValueFactory(value->value.getValue().emailProperty());
		//bindeo tabla correos
		correoTableView.itemsProperty().bind(model.direccionesCorreoProperty());
		//hacemos web tabl editable
		websTableView.setEditable(true);
		
		webTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		//rellenando columna web
		webTableColumn.setCellValueFactory(value->value.getValue().webProperty());
		//bindeo tabla web
		websTableView.itemsProperty().bind(model.websProperty());
		
		//hacemos tableTelefono editable
		
		telefonoTableView.setEditable(true);
		
		numeroTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		tipoTableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(TipoTelefono.DOMICILIO,TipoTelefono.MOVIL));
		//rellenado columna telefono
		numeroTableColumn.setCellValueFactory(value->value.getValue().numeroProperty());
		
		//rellenando columna tipo
		
		tipoTableColumn.setCellValueFactory(value->value.getValue().tipoProperty());
		
		//bindeo tabla telefono
		telefonoTableView.itemsProperty().bind(model.telefonosProperty());
		
		
		
		
		

	}

	@FXML
	public void onAnadirEmail() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Añadir e-mail");
		dialog.setHeaderText("Atención va a añadir un e-mail");
		dialog.setContentText("Por favor introduzca su e-mail:");
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("dad/javafx/resources/cv64x64.png"));

		// Traditional way to get the response value.
		Optional<String> result =  (Optional<String>) dialog.showAndWait();
		if (result.isPresent()){
		    Email email=new Email();
		    email.setEmail(result.get());
		    model.getDireccionesCorreo().add(email);
		}

		
	}
	@FXML
	public void onAnadirTelefono() throws IOException {
		//creamos el dialog con su titulo y header text
		Dialog<ButtonType> dialog=new Dialog<>();
		dialog.setTitle("Nuevo teléfono");
		dialog.setHeaderText("Introduzca el nuevo número de telefono");
		
		//declaramos los tipo de botones
		
		ButtonType anadirButton=new ButtonType("Añadir",ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(anadirButton,ButtonType.CANCEL);
		Stage stage=(Stage)dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("dad/javafx/resources/cv64x64.png"));
		
		//creamos los campos
		GridPane grid =new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		
		TextField telefonoTextField=new TextField();
		telefonoTextField.setPromptText("Número de teléfono");
		Label telefonoDialogLabel=new Label("Número: ");
		Label tipoDialogLabel=new Label("Tipo: ");
		
		ComboBox<TipoTelefono> tipoComboBox=new ComboBox<TipoTelefono>();
		tipoComboBox.getItems().addAll(TipoTelefono.values());
		
		grid.add(telefonoDialogLabel, 0, 0);
		grid.add(telefonoTextField, 1, 0);
		grid.add(tipoDialogLabel, 0, 1);
		grid.add(tipoComboBox, 1, 1);
		
		//activar o desactivar el boton añadir dependiendo de si se ha a�adido un numero de telefono o no
		
	
		
		//metemos el grid pane en nuestro dialog
		dialog.getDialogPane().setContent(grid);
		
		//accion al apretar añadir button
	
		    	Optional<ButtonType> result =  (Optional<ButtonType>) dialog.showAndWait();
				if (result.get()==anadirButton){
				   String verificarTelefono=telefonoTextField.getText();
				   if((verificarTelefono.charAt(0)=='9' || verificarTelefono.charAt(0)=='8') && tipoComboBox.getSelectionModel().getSelectedItem()==TipoTelefono.DOMICILIO) {
					   Telefono telefono=new Telefono();
					   telefono.setNumero(verificarTelefono);
					   telefono.setTipo(tipoComboBox.getSelectionModel().getSelectedItem());
					   model.getTelefonos().add(telefono);
				   }else if(verificarTelefono.charAt(0)=='6' && tipoComboBox.getSelectionModel().getSelectedItem()==TipoTelefono.MOVIL) {
					   Telefono telefono=new Telefono();
					   telefono.setNumero(verificarTelefono);
					   telefono.setTipo(tipoComboBox.getSelectionModel().getSelectedItem());
					   model.getTelefonos().add(telefono);
				   }else {
					   Alert alert = new Alert(AlertType.ERROR);
					   alert.setTitle("Error En telefono");
					   alert.setHeaderText("El telefono no es valido");
					   alert.setContentText("asegurese de que el numero de telefono coincide con el tipo");

					   alert.showAndWait();
				   }
				}
		    }
		

	@FXML
	public void onAnadirWeb() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Añadir una web");
		dialog.setHeaderText("Atención va a añadir una web");
		dialog.setContentText("Por favor introduzca su web:");
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("dad/javafx/resources/cv64x64.png"));

		// Traditional way to get the response value.
		Optional<String> result = (Optional<String>) dialog.showAndWait();
		if (result.isPresent()){
		    Web web=new Web();
		    web.setWeb(result.get());
		    model.getWebs().add(web);
		}
	}
	@FXML
	public void onEliminarTelefono() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmacion de eliminacion");
		alert.setHeaderText("Si elimina la fila no podra recuperarla");
		alert.setContentText("¿Aun asi quiere eliminarla?");

		Optional<ButtonType> result = (Optional<ButtonType>) alert.showAndWait();
		if (result.get() == ButtonType.OK){
		Telefono telefonoEliminar=new Telefono();
		telefonoEliminar=telefonoTableView.getSelectionModel().getSelectedItem();
		model.telefonosProperty().remove(telefonoEliminar);
		}else {
			Alert alertInformation = new Alert(AlertType.INFORMATION);
			alertInformation.setTitle("Cancelacion de eliminacion");
			alertInformation.setHeaderText("Ha decidido no eliminar la fila");
			alertInformation.setContentText("Los datos se mantendran en su estado original");

			alertInformation.showAndWait();
		}
	}
	@FXML
	public void onEliminarEmail() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmacion de eliminacion");
		alert.setHeaderText("Si elimina la fila no podra recuperarla");
		alert.setContentText("¿Aun asi quiere eliminarla?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		Email emailEliminar=new Email();
		emailEliminar=correoTableView.getSelectionModel().getSelectedItem();
		
		model.direccionesCorreoProperty().remove(emailEliminar);
		}
		else {
			Alert alertInformation = new Alert(AlertType.INFORMATION);
			alertInformation.setTitle("Cancelacion de eliminacion");
			alertInformation.setHeaderText("Ha decidido no eliminar la fila");
			alertInformation.setContentText("Los datos se mantendran en su estado original");

			alertInformation.showAndWait();
		}

	}
	@FXML
	public void onEliminarWeb() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmacion de eliminacion");
		alert.setHeaderText("Si elimina la fila no podra recuperarla");
		alert.setContentText("¿Aun asi quiere eliminarla?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		Web webEliminar=new Web();
		webEliminar=websTableView.getSelectionModel().getSelectedItem();
		
		model.websProperty().remove(webEliminar);
		}
		else {
			Alert alertInformation = new Alert(AlertType.INFORMATION);
			alertInformation.setTitle("Cancelacion de eliminacion");
			alertInformation.setHeaderText("Ha decidido no eliminar la fila");
			alertInformation.setContentText("Los datos se mantendran en su estado original");

			alertInformation.showAndWait();
		}
	}
	
	public ContactoModel getModel() {
		return model;
	}

	public SplitPane getView() {
		return view;
	}
	
}
