package dad.holaMundoFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Controlador implements Initializable {
	// modelo

	Modelo model = new Modelo();

	// vista

	@FXML // si no usamos @FXML no hay inyeccion de valores asi que no puede ser olvidado
	private VBox view;

	@FXML
	private TextField nombreText;

	@FXML
	private Button saludarButton;

	@FXML
	private Label saludoLabel;

	// constructor

	public Controlador() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Vista.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//bindeos
		
		model.nombreProperty().bind(nombreText.textProperty());
		
		saludoLabel.textProperty().bind(model.saludoProperty());
		
	

		saludarButton.disableProperty().bind(model.nombreProperty().isEmpty());
		
	
		
		
	}
	@FXML
	private void onSaludarButtonAction(ActionEvent e) {
		System.out.println("Pulsado");
		if(model.getNombre().isEmpty()) {
			model.setSaludo("");
		}
		else {
			
			model.setSaludo("¡Hola "+model.getNombre()+"!");
		}
		
		
	}

	public VBox getView() {
		return view;
	}
	
	public Modelo getModel() {
		return model;
	}

}
