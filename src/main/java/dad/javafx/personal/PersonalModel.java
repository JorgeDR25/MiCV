package dad.javafx.personal;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import dad.javafx.miCV.LocalDateAdapter;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlType
public class PersonalModel {

	private StringProperty identificacion;
	private StringProperty nombre;
	private StringProperty apellidos;
	private ObjectProperty<LocalDate> fechaNacimiento;
	private StringProperty direccion;
	private StringProperty codigoPostal;
	private StringProperty localidad;
	private StringProperty pais;
	private ListProperty<Nacionalidad> nacionalidades;
	
	private ListProperty<String> paises;
	private ListProperty<String> nacionalidadesDisponibles;

	public PersonalModel() {
		identificacion=new SimpleStringProperty(this,"identificacion");
		nombre=new SimpleStringProperty(this,"nombre");
		apellidos=new SimpleStringProperty(this,"apellidos");
		fechaNacimiento=new SimpleObjectProperty<LocalDate>(this,"fechaNacimiento");
		direccion=new SimpleStringProperty(this,"direccion");
		codigoPostal=new SimpleStringProperty(this,"codigoPostal");
		localidad=new SimpleStringProperty(this,"localidad");
		pais=new SimpleStringProperty(this,"pais");
		nacionalidades=new SimpleListProperty<>(this,"nacionalidades",FXCollections.observableArrayList());
		paises=new SimpleListProperty<>(this,"paises",FXCollections.observableArrayList());
		nacionalidadesDisponibles=new SimpleListProperty<>(this,"nacionalidadesDisponibles",FXCollections.observableArrayList());
	}

	public final StringProperty identificacionProperty() {
		return this.identificacion;
	}
	
	@XmlAttribute
	public final String getIdentificacion() {
		return this.identificacionProperty().get();
	}
	

	public final void setIdentificacion(final String identificacion) {
		this.identificacionProperty().set(identificacion);
	}
	

	public final StringProperty nombreProperty() {
		return this.nombre;
	}
	
	@XmlElement
	public final String getNombre() {
		return this.nombreProperty().get();
	}
	

	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}
	

	public final StringProperty apellidosProperty() {
		return this.apellidos;
	}
	
	@XmlElement
	public final String getApellidos() {
		return this.apellidosProperty().get();
	}
	

	public final void setApellidos(final String apellidos) {
		this.apellidosProperty().set(apellidos);
	}
	

	public final ObjectProperty<LocalDate> fechaNacimientoProperty() {
		return this.fechaNacimiento;
	}
	
	@XmlElement
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public final LocalDate getFechaNacimiento() {
		return this.fechaNacimientoProperty().get();
	}
	

	public final void setFechaNacimiento(final LocalDate fechaNacimiento) {
		this.fechaNacimientoProperty().set(fechaNacimiento);
	}
	

	public final StringProperty direccionProperty() {
		return this.direccion;
	}
	
	@XmlElement
	public final String getDireccion() {
		return this.direccionProperty().get();
	}
	

	public final void setDireccion(final String direccion) {
		this.direccionProperty().set(direccion);
	}
	

	public final StringProperty codigoPostalProperty() {
		return this.codigoPostal;
	}
	
	@XmlElement
	public final String getCodigoPostal() {
		return this.codigoPostalProperty().get();
	}
	

	public final void setCodigoPostal(final String codigoPostal) {
		this.codigoPostalProperty().set(codigoPostal);
	}
	

	public final StringProperty localidadProperty() {
		return this.localidad;
	}
	
	@XmlElement
	public final String getLocalidad() {
		return this.localidadProperty().get();
	}
	

	public final void setLocalidad(final String localidad) {
		this.localidadProperty().set(localidad);
	}
	

	public final StringProperty paisProperty() {
		return this.pais;
	}
	
	@XmlElement
	public final String getPais() {
		return this.paisProperty().get();
	}
	

	public final void setPais(final String pais) {
		this.paisProperty().set(pais);
	}
	

	public final ListProperty<Nacionalidad> nacionalidadesProperty() {
		return this.nacionalidades;
	}
	
	@XmlElement(name="nacionalidades")
	public final ObservableList<Nacionalidad> getNacionalidades() {
		return this.nacionalidadesProperty().get();
	}
	

	public final void setNacionalidades(final ObservableList<Nacionalidad> nacionalidades) {
		this.nacionalidadesProperty().set(nacionalidades);
	}

	public final ListProperty<String> paisesProperty() {
		return this.paises;
	}
	
	
	public final ObservableList<String> getPaises() {
		return this.paisesProperty().get();
	}
	

	public final void setPaises(final ObservableList<String> paises) {
		this.paisesProperty().set(paises);
	}

	public final ListProperty<String> nacionalidadesDisponiblesProperty() {
		return this.nacionalidadesDisponibles;
	}
	

	public final ObservableList<String> getNacionalidadesDisponibles() {
		return this.nacionalidadesDisponiblesProperty().get();
	}
	

	public final void setNacionalidadesDisponibles(final ObservableList<String> nacionalidadesDisponibles) {
		this.nacionalidadesDisponiblesProperty().set(nacionalidadesDisponibles);
	}
	
	
	
	
	
	
}
