package dad.holaMundoFxml;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Modelo {
	
	private StringProperty nombre;
	private StringProperty saludo;
	
	public Modelo() {
		nombre=new SimpleStringProperty(this,"nombre");
		saludo=new SimpleStringProperty(this,"saludo");
	}

	public final StringProperty nombreProperty() {
		return this.nombre;
	}
	

	public final String getNombre() {
		return this.nombreProperty().get();
	}
	

	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}
	

	public final StringProperty saludoProperty() {
		return this.saludo;
	}
	

	public final String getSaludo() {
		return this.saludoProperty().get();
	}
	

	public final void setSaludo(final String saludo) {
		this.saludoProperty().set(saludo);
	}
	
	
	

}
