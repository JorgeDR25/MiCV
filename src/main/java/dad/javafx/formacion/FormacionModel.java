package dad.javafx.formacion;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class FormacionModel {
	
	private ListProperty<Titulo> titulos;
	
	public FormacionModel() {
		titulos=new SimpleListProperty<Titulo>(this,"titulos",FXCollections.observableArrayList());
	}

	public final ListProperty<Titulo> titulosProperty() {
		return this.titulos;
	}
	
	
	public final ObservableList<Titulo> getTitulos() {
		return this.titulosProperty().get();
	}
	

	public final void setTitulos(final ObservableList<Titulo> titulos) {
		this.titulosProperty().set(titulos);
	}
	

}
