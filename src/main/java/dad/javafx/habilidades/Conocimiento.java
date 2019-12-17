package dad.javafx.habilidades;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Conocimiento {

	private StringProperty denominacion;
	private ObjectProperty<Nivel> nivel;
	private ObjectProperty<Idioma> idioma;

	public Conocimiento() {
		denominacion = new SimpleStringProperty(this, "denominacion");
		nivel = new SimpleObjectProperty<>(this, "nivel");
		idioma = new SimpleObjectProperty<>(this, "idioma");
	}

	public final ObjectProperty<Nivel> nivelProperty() {
		return this.nivel;
	}
	
	@XmlAttribute
	public final Nivel getNivel() {
		return this.nivelProperty().get();
	}

	public final void setNivel(final Nivel nivel) {
		this.nivelProperty().set(nivel);
	}

	public final ObjectProperty<Idioma> idiomaProperty() {
		return this.idioma;
	}
	
	@XmlElement
	public final Idioma getIdioma() {
		return this.idiomaProperty().get();
	}

	public final void setIdioma(final Idioma idioma) {
		this.idiomaProperty().set(idioma);
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

}
