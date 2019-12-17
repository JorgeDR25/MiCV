package dad.javafx.experiencia;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ExperienciaModel {
	
	private ListProperty<Experiencia> experiencias;
	
	public ExperienciaModel() {
		experiencias=new SimpleListProperty<Experiencia>(this,"experiencias",FXCollections.observableArrayList());
	}

	public final ListProperty<Experiencia> experienciasProperty() {
		return this.experiencias;
	}
	
	
	public final ObservableList<Experiencia> getExperiencias() {
		return this.experienciasProperty().get();
	}
	

	public final void setExperiencias(final ObservableList<Experiencia> experiencias) {
		this.experienciasProperty().set(experiencias);
	}
	

}
