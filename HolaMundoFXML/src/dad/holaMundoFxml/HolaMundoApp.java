package dad.holaMundoFxml;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HolaMundoApp extends Application {

	//controlador
	Controlador controlador;
	
	public static void main(String[] args) {
		launch(); 

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		controlador=new Controlador();
		
		primaryStage.setTitle("Hola Mundo con FXML");
		primaryStage.setScene(new Scene(controlador.getView()));
		primaryStage.show();
		
	}

}
