package dad.javafx.contacto;

import javax.xml.bind.annotation.XmlAttribute;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Web {
	
	private StringProperty web;
	
	public Web() {
		web=new SimpleStringProperty(this,"web");
	}

	public final StringProperty webProperty() {
		return this.web;
	}
	
	@XmlAttribute(name="url")
	public final String getWeb() {
		return this.webProperty().get();
	}
	

	public final void setWeb(final String web) {
		this.webProperty().set(web);
	}
	
	@Override
	public String toString() {
		return getWeb();
	}
	
}
