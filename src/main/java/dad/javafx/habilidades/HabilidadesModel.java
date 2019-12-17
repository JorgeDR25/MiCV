package dad.javafx.habilidades;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlType(name="habilidades")
public class HabilidadesModel {
	
	private ListProperty<Conocimiento> conocimiento;

	
	public HabilidadesModel() {
		conocimiento=new SimpleListProperty<Conocimiento>(this,"conocimiento",FXCollections.observableArrayList());
		
	}

	public final ListProperty<Conocimiento> conocimientoProperty() {
		return this.conocimiento;
	}
	
	@XmlElement
	public final ObservableList<Conocimiento> getConocimiento() {
		return this.conocimientoProperty().get();
	}
	

	public final void setConocimiento(final ObservableList<Conocimiento> conocimiento) {
		this.conocimientoProperty().set(conocimiento);
	}
	

	

}
