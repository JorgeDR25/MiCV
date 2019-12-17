package dad.javafx.miCV;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBException;

import dad.javafx.contacto.ContactoController;
import dad.javafx.experiencia.ExperienciaController;
import dad.javafx.formacion.FormacionController;
import dad.javafx.habilidades.HabilidadesController;
import dad.javafx.personal.PersonalController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class RootController implements Initializable {
	//objetos para comprobar que no hay fichero abierto que no hay cambios,etc..
	private File ficheroAbierto;
		
	private CV inicial=new CV();

	//controllers
	private PersonalController personalController=new PersonalController();
	
	private ContactoController<?> contactoController=new ContactoController<Object>();
	
	private FormacionController formacionController=new FormacionController();
	
	private ExperienciaController experienciaController=new ExperienciaController();
	
	private HabilidadesController habilidadesController=new HabilidadesController();
	//model
	private CV model=new CV();
	//view
	@FXML
	private BorderPane view;
	
	@FXML
	private Menu archivoMenu;
	
	@FXML
	private MenuItem  nuevoMenuItem,abrirMenuItem,guardarMenuItem,guardarComoMenuItem,salirMenuItem;
	
	@FXML
	private Menu ayudaMenu;
	
	@FXML
	private MenuItem acercaMenuItem;
	
	@FXML
	private Tab personalTab,contactoTab,formacionTab,experienciaTab,conocimientosTab;

	public RootController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RootView.fxml"));
		loader.setController(this);
		loader.load();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		formacionController.getModel().titulosProperty().bindBidirectional(model.formacionProperty());
		
		
		
		personalController.getModel().nombreProperty().bindBidirectional(model.personalProperty().get().nombreProperty());
		personalController.getModel().apellidosProperty().bindBidirectional(model.personalProperty().get().apellidosProperty());
		personalController.getModel().fechaNacimientoProperty().bindBidirectional(model.personalProperty().get().fechaNacimientoProperty());
		personalController.getModel().direccionProperty().bindBidirectional(model.personalProperty().get().direccionProperty());
		personalController.getModel().codigoPostalProperty().bindBidirectional(model.personalProperty().get().codigoPostalProperty());
		personalController.getModel().localidadProperty().bindBidirectional(model.personalProperty().get().localidadProperty());
	
		model.personalProperty().get().paisProperty().bind(personalController.getModel().paisProperty());
		model.personalProperty().get().nacionalidadesProperty().bind(personalController.getModel().nacionalidadesProperty());
		personalController.getModel().identificacionProperty().bindBidirectional(model.personalProperty().get().identificacionProperty());
		
		  

	   
		
		habilidadesController.getModel().conocimientoProperty().bindBidirectional(model.habilidadesProperty());
		experienciaController.getModel().experienciasProperty().bindBidirectional(model.experienciasProperty());
		
		contactoController.getModel().direccionesCorreoProperty().bindBidirectional(model.contactoProperty().get().direccionesCorreoProperty());
		contactoController.getModel().websProperty().bindBidirectional(model.contactoProperty().get().websProperty());
		contactoController.getModel().telefonosProperty().bindBidirectional(model.contactoProperty().get().telefonosProperty());
		
		personalTab.setContent(personalController.getView());
		contactoTab.setContent(contactoController.getView());
		formacionTab.setContent(formacionController.getView());
		experienciaTab.setContent(experienciaController.getView());
		conocimientosTab.setContent(habilidadesController.getView());
		nuevoMenuItem.setGraphic(new ImageView("dad/javafx/resources/nuevo.gif"));
		abrirMenuItem.setGraphic(new ImageView("dad/javafx/resources/abrir.gif"));
		guardarMenuItem.setGraphic(new ImageView("dad/javafx/resources/guardar.gif"));
		
		
		
		
	}
	
	@FXML
	public void onAbrir() {
		
		try {
			FileChooser chooser=new FileChooser();
			chooser.getExtensionFilters().addAll(new ExtensionFilter("xml files","*.cv"));
			
			chooser.setTitle("Abrir fichero CV");
			
			
			File fichero = chooser.showOpenDialog(MiCVApp.getPrimaryStage());
			ficheroAbierto=fichero;
			
				model=model.load(fichero);
				contactoController.getModel().setWebs(model.getContacto().getWebs());
				contactoController.getModel().setDireccionesCorreo(model.getContacto().getDireccionesCorreo());
				contactoController.getModel().setTelefonos(model.getContacto().getTelefonos());
				
				experienciaController.getModel().setExperiencias(model.getExperiencias());
				
				formacionController.getModel().setTitulos(model.getFormacion());
				
				habilidadesController.getModel().setConocimiento(model.getHabilidades());
				
				personalController.getModel().setIdentificacion(model.getPersonal().getIdentificacion());
				personalController.getModel().setNombre(model.getPersonal().getNombre());
				personalController.getModel().setApellidos(model.getPersonal().getApellidos());
				personalController.getModel().setCodigoPostal(model.getPersonal().getCodigoPostal());
				personalController.getModel().setDireccion(model.getPersonal().getDireccion());
				personalController.getModel().setFechaNacimiento(model.getPersonal().getFechaNacimiento());
				personalController.getModel().setLocalidad(model.getPersonal().getLocalidad());
				personalController.getModel().setNacionalidades(model.getPersonal().getNacionalidades());
			
				personalController.getModel().paisProperty().bind(model.personalProperty().get().paisProperty());
				
			
				
				personalController.paisActual(model.getPersonal().getPais());
				
				inicial.setContacto(model.getContacto());
				
				inicial.setExperiencias(model.getExperiencias());
				
				inicial.setFormacion(model.getFormacion());
				
				inicial.setHabilidades(model.getHabilidades());
				
				inicial.setPersonal(model.getPersonal());
		} catch (JAXBException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error ");
			alert.setHeaderText("Ha ocurrido un error");
			alert.setContentText("El fichero seleccionado no es valido o no ha elegido fichero");

			alert.showAndWait();
		}
			
			
		
		
	}
	@FXML
	public void onGuardar() {
		if(ficheroAbierto!=null) {
			model.save(ficheroAbierto);
		}
		else {
			onGuardarComo();
		}
	}
	@FXML
	public void onGuardarComo() {
		
		FileChooser chooser=new FileChooser();
		chooser.getExtensionFilters().addAll(new ExtensionFilter("xml files","*.cv"));
		
		chooser.setTitle("Guardar fichero CV");
		
		
		File fichero = chooser.showSaveDialog(MiCVApp.getPrimaryStage());
		ficheroAbierto=fichero;
		model.save(ficheroAbierto);
	}
	
	@FXML
	public void onSalir() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Salir");
		alert.setHeaderText("Va a salir del programa");
		alert.setContentText("¿Seguro que quiere salir?");

		Optional<ButtonType> result =    alert.showAndWait();
		if (result.get() == ButtonType.OK){
		System.exit(0);
		}
	}
	@FXML 
	public void onNuevo() {
		
		
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Apertura nuevo cv");
			alert.setHeaderText("Si hay cambios sin guardar estos se perderan");
			alert.setContentText("¿Aun asi quiere continuar?");

			Optional<ButtonType> result = (Optional<ButtonType>) alert.showAndWait();
			if (result.get() == ButtonType.OK){
			   model=new CV();
			    
		
				ficheroAbierto=null;
			    
				contactoController.getModel().setWebs(model.getContacto().getWebs());
				contactoController.getModel().setDireccionesCorreo(model.getContacto().getDireccionesCorreo());
				contactoController.getModel().setTelefonos(model.getContacto().getTelefonos());
				
				experienciaController.getModel().setExperiencias(model.getExperiencias());
				
				formacionController.getModel().setTitulos(model.getFormacion());
				
				habilidadesController.getModel().setConocimiento(model.getHabilidades());
				
				personalController.getModel().setIdentificacion(model.getPersonal().getIdentificacion());
				personalController.getModel().setNombre(model.getPersonal().getNombre());
				personalController.getModel().setApellidos(model.getPersonal().getApellidos());
				personalController.getModel().setCodigoPostal(model.getPersonal().getCodigoPostal());
				personalController.getModel().setDireccion(model.getPersonal().getDireccion());
				personalController.getModel().setFechaNacimiento(model.getPersonal().getFechaNacimiento());
				personalController.getModel().setLocalidad(model.getPersonal().getLocalidad());
				personalController.getModel().setNacionalidades(model.getPersonal().getNacionalidades());
				personalController.getModel().paisProperty().bind(model.personalProperty().get().paisProperty());
				
				personalController.paisActual(model.getPersonal().getPais());
				
				inicial.setContacto(model.getContacto());
				
				inicial.setExperiencias(model.getExperiencias());
				
				inicial.setFormacion(model.getFormacion());
				
				inicial.setHabilidades(model.getHabilidades());
				
				inicial.setPersonal(model.getPersonal());
			    
			    
			    
			} else {
				Alert alertCancel = new Alert(AlertType.INFORMATION);
				alertCancel.setTitle("Nuevo cancelado");
				alertCancel.setHeaderText(null);
				alertCancel.setContentText("Has cancelado la creacion de un nuevo cv");

				alertCancel.showAndWait();
			
			}
		
	}
	
	public BorderPane getView() {
		return view;
	}
	public CV getModel() {
		return model;
	}
}
