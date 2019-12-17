package dad.javafx.miCV;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import dad.javafx.contacto.ContactoModel;
import dad.javafx.experiencia.Experiencia;
import dad.javafx.formacion.Titulo;
import dad.javafx.habilidades.Conocimiento;
import dad.javafx.personal.PersonalModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlRootElement
@XmlType
public class CV {
	private ObjectProperty<PersonalModel> personal;
	private ListProperty<Conocimiento> habilidades;
	private ListProperty<Titulo> formacion;
	private ListProperty<Experiencia> experiencias;
	private ObjectProperty<ContactoModel> contacto;


	
	public CV() {
		personal=new SimpleObjectProperty<>(this,"personal",new PersonalModel());//new PersonalModel() se pone con los object para evitar posibles problemas
		habilidades=new SimpleListProperty<>(this,"habilidades",FXCollections.observableArrayList());
		formacion=new SimpleListProperty<>(this,"formacion",FXCollections.observableArrayList());
		experiencias=new SimpleListProperty<>(this,"experiencias",FXCollections.observableArrayList());
		contacto=new SimpleObjectProperty<>(this,"contacto",new ContactoModel());
	}



	public final ObjectProperty<PersonalModel> personalProperty() {
		return this.personal;
	}
	


	@XmlElement
	public final PersonalModel getPersonal() {
		return this.personalProperty().get();
	}
	



	public final void setPersonal(final PersonalModel personal) {
		this.personalProperty().set(personal);
	}
	



	public final ListProperty<Conocimiento> habilidadesProperty() {
		return this.habilidades;
	}
	


	
	public final ObservableList<Conocimiento> getHabilidades() {
		return this.habilidadesProperty().get();
	}
	



	public final void setHabilidades(final ObservableList<Conocimiento> habilidades) {
		this.habilidadesProperty().set(habilidades);
	}
	



	public final ListProperty<Titulo> formacionProperty() {
		return this.formacion;
	}
	


	
	public final ObservableList<Titulo> getFormacion() {
		return this.formacionProperty().get();
	}
	



	public final void setFormacion(final ObservableList<Titulo> formacion) {
		this.formacionProperty().set(formacion);
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
	



	public final ObjectProperty<ContactoModel> contactoProperty() {
		return this.contacto;
	}
	


	@XmlElement
	public final ContactoModel getContacto() {
		return this.contactoProperty().get();
	}
	



	public final void setContacto(final ContactoModel contacto) {
		this.contactoProperty().set(contacto);
	}
	
	//metodos para guardar y abrir ficheros xml
	public void save(File file) {
		try {
			JAXBContext context=JAXBContext.newInstance(CV.class);
			Marshaller marshaller=context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(this, file);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public CV load(File file) throws JAXBException {
		
		JAXBContext context=JAXBContext.newInstance(CV.class);
		Unmarshaller unmarshaller=context.createUnmarshaller();
		return (CV) unmarshaller.unmarshal(file);
	}
	
	

}
