package dad.javafx.contacto;

import javax.xml.bind.annotation.XmlAttribute;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Email {
	
	private StringProperty email;
	
	public Email() {
		email=new SimpleStringProperty(this,"email");
	}

	public final StringProperty emailProperty() {
		return this.email;
	}
	
	@XmlAttribute(name="direccion")
	public final String getEmail() {
		return this.emailProperty().get();
	}
	

	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}
	
	
	
}
