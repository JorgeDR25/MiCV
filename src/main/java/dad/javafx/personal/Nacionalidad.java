package dad.javafx.personal;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlType
public class Nacionalidad {
	private StringProperty denominacion;

	public Nacionalidad(String denominacion) {
		this.denominacion = new SimpleStringProperty(this, "denominacion", denominacion);
	}
	
	public Nacionalidad() {
		this(null);
	}

	public final StringProperty denominacionProperty() {
		return this.denominacion;
	}

	@XmlAttribute
	public final String getDenominacion() {
		return this.denominacionProperty().get();
	}

	public final void setDenominacion(final String denominacion) {
		this.denominacionProperty().set(denominacion);
	}

	@Override
	public String toString() {
		return getDenominacion();
	}
	

}
