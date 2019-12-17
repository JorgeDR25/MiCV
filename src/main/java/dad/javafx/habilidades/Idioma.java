package dad.javafx.habilidades;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlType
public class Idioma {
	
	private StringProperty certificacion;
	
	public Idioma() {
		certificacion=new SimpleStringProperty(this,"certificacion");
		
	}

	public final StringProperty certificacionProperty() {
		return this.certificacion;
	}
	
	@XmlAttribute
	public final String getCertificacion() {
		return this.certificacionProperty().get();
	}
	

	public final void setCertificacion(final String certificacion) {
		this.certificacionProperty().set(certificacion);
	}


	
	
	
	
}
