package dad.javafx.personal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PersonalController implements Initializable {
	
	//modelo
	private PersonalModel model=new PersonalModel();
	
	//vista
	
	@FXML
	private GridPane view;
	
	@FXML
	private Label dniLabel,nombreLabel,apellidosLabel,fechaNacimientoLabel,direccionLabel,codigoPostalLabel,localidadLabel,paisLabel,nacionalidadLabel;
	
	@FXML
	private TextField dniTextField,nombreTextField,apellidosTextField,localidadTextField,codigoPostalTextField;
	
	@FXML
	private DatePicker fechaNacimientoDatePicker;
	
	@FXML
	private TextArea direccionTextArea;
	
	@FXML
	private ListView<Nacionalidad> nacionalidadListView;
	
	@FXML 
	private Button anadirButton,quitarButton;
	
	@FXML
	private ComboBox<String> paisComboBox;
	
	
	public  PersonalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PersonalView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		obtenerPaises();
		
		
		 //paisComboBox.valueProperty().bind(model.paisProperty());
		paisComboBox.getItems().addAll(model.getPaises().toString());
	    
	    
	    //codInmueblesComboBox.getSelectionModel().select((listaResultado.getCodInmuebleAlquileres().getCodInmuebleAlquileres()));
	    
	    
	    //bindeamos la lista al modelo
	   nacionalidadListView.itemsProperty().bind(model.nacionalidadesProperty());
	   model.nombreProperty().bindBidirectional(nombreTextField.textProperty());
	   model.apellidosProperty().bindBidirectional(apellidosTextField.textProperty());
	   model.identificacionProperty().bindBidirectional(dniTextField.textProperty());
	   model.fechaNacimientoProperty().bindBidirectional(fechaNacimientoDatePicker.valueProperty());
	   model.direccionProperty().bindBidirectional(direccionTextArea.textProperty());
	   model.codigoPostalProperty().bindBidirectional(codigoPostalTextField.textProperty());
	   model.localidadProperty().bindBidirectional(localidadTextField.textProperty());
	   model.paisProperty().bind(Bindings.select(paisComboBox.getSelectionModel().selectedItemProperty()));
	 
	

	  nacionalidadListView.itemsProperty().bind(model.nacionalidadesProperty());
	  
	}
	
	@FXML
	public void onAñadir(ActionEvent e) {
		obtenerNacionalidad();
		List<String> nacionalidades = new ArrayList<>();
		nacionalidades.addAll(model.getNacionalidadesDisponibles());

		ChoiceDialog<String> dialog = new ChoiceDialog<>(nacionalidades.get(0), nacionalidades);
		dialog.setTitle("Eleccion de nacionalidades");
		dialog.setHeaderText("Lista de Nacionalidades disponibles");
		dialog.setContentText("Seleccione la nacionalidad a añadir");
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("dad/javafx/resources/cv64x64.png"));

		// Traditional way to get the response value.
		Optional<String> result =  dialog.showAndWait();
		if (result.isPresent()){
		  Nacionalidad nacionalidad=new Nacionalidad();
		  nacionalidad.setDenominacion(result.get());
		  model.nacionalidadesProperty().add(nacionalidad);
		}

	}
	
	public void paisActual(String pais) {
		paisComboBox.getSelectionModel().select(pais);
		
	}
	@FXML
	public void onEliminar() {
		model.getNacionalidades().remove(nacionalidadListView.getSelectionModel().getSelectedIndex());
	}

	public void  obtenerPaises() {
		//File paises=new File("src\\main\\resources\\dad\\javafx\\resources\\paises.csv");
		System.out.println(getClass().getPackageName());
		File paises=new File("C:\\Users\\Jorge\\eclipse-workspace\\MiCV\\src\\main\\resources\\dad\\javafx\\resources\\paises.csv");
		try {
			String leer;
			FileReader leerPaises=new FileReader(paises);
			BufferedReader b = new BufferedReader(leerPaises);
			while((leer=b.readLine())!=null) {
				model.paisesProperty().add(leer);
			}
			b.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void  obtenerNacionalidad() {
		File paises=new File("D:\\Users\\Alumno\\eclipse-workspace\\MiCV\\src\\dad\\javafx\\resources\\nacionalidades.csv");
		try {
			String leer;
			FileReader leerPaises=new FileReader(paises);
			BufferedReader b = new BufferedReader(leerPaises);
			while((leer=b.readLine())!=null) {
				model.nacionalidadesDisponiblesProperty().add(leer);
			}
			b.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PersonalModel getModel() {
		return model;
	}

	public GridPane getView() {
		return view;
	}
	

}
