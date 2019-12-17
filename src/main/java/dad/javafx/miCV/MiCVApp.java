package dad.javafx.miCV;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MiCVApp extends Application {
	private RootController rootController;
	private static Stage primaryStage;
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MiCVApp.primaryStage = primaryStage;
		Image icono = new Image("dad/javafx/resources/cv64x64.png"); 
		rootController=new RootController();
		primaryStage.setTitle("MiCV");
		primaryStage.getIcons().add(icono);
		primaryStage.setScene(new Scene(rootController.getView(), 640,  480));
		primaryStage.show();
		
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	

}
