package dad.javafx.contacto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlType
public class Telefono {
	
	private StringProperty numero;
	private ObjectProperty<TipoTelefono> tipo;
	
	public Telefono() {
		
		numero=new SimpleStringProperty(this,"numero");
		tipo=new SimpleObjectProperty<TipoTelefono>(this,"tipo");
	}

	public final StringProperty numeroProperty() {
		return this.numero;
	}
	
	@XmlAttribute
	public final String getNumero() {
		return this.numeroProperty().get();
	}
	

	public final void setNumero(final String numero) {
		this.numeroProperty().set(numero);
	}
	

	public final ObjectProperty<TipoTelefono> tipoProperty() {
		return this.tipo;
	}
	
	@XmlAttribute
	public final TipoTelefono getTipo() {
		return this.tipoProperty().get();
	}
	

	public final void setTipo(final TipoTelefono tipo) {
		this.tipoProperty().set(tipo);
	}
	
	
}
