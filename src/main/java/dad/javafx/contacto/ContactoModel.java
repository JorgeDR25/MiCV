package dad.javafx.contacto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlType
public class ContactoModel {

		private ListProperty<Telefono> telefonos;
		private ListProperty<Email> direccionesCorreo;
		private ListProperty<Web> webs;
		
		
		public ContactoModel() {
			telefonos=new SimpleListProperty<Telefono>(this,"telefonos",FXCollections.observableArrayList());
			direccionesCorreo=new SimpleListProperty<Email>(this,"direccionesCorreo",FXCollections.observableArrayList());
			webs=new SimpleListProperty<Web>(this,"web",FXCollections.observableArrayList());
		}


		public final ListProperty<Telefono> telefonosProperty() {
			return this.telefonos;
		}
		

		@XmlElement
		public final ObservableList<Telefono> getTelefonos() {
			return this.telefonosProperty().get();
		}
		


		public final void setTelefonos(final ObservableList<Telefono> telefonos) {
			this.telefonosProperty().set(telefonos);
		}
		


		public final ListProperty<Email> direccionesCorreoProperty() {
			return this.direccionesCorreo;
		}
		

		@XmlElement
		public final ObservableList<Email> getDireccionesCorreo() {
			return this.direccionesCorreoProperty().get();
		}
		


		public final void setDireccionesCorreo(final ObservableList<Email> direccionesCorreo) {
			this.direccionesCorreoProperty().set(direccionesCorreo);
		}
		


		public final ListProperty<Web> websProperty() {
			return this.webs;
		}
		

		@XmlElement
		public final ObservableList<Web> getWebs() {
			return this.websProperty().get();
		}
		


		public final void setWebs(final ObservableList<Web> webs) {
			this.websProperty().set(webs);
		}
		
		
}
