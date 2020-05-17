package backing;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import entidades.Alta;
import servicios.PacienteService;



@Named
@ViewScoped
public class BackingDarAlta implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private PacienteService pacienteService;
	String alta ="";

	public BackingDarAlta() {
		// TODO Auto-generated constructor stub
	}
	
	public String getAlta() {
		return alta;
	}

	public void setAlta(String alta) {
		this.alta = alta;
	}

	public void nuevoAlta() {
		try {
			pacienteService.crearAlta(alta);
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle archivomensajes = ResourceBundle.getBundle("resources.application",
					context.getViewRoot().getLocale());
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							archivomensajes.getString("generico.registroCreadoConExito"),
							archivomensajes.getString("generico.registroCreadoConExito")));
			System.out.println("Antes de llamar a prepararCrearAutor por tanto debe haber nueva instancia");
		}catch( Exception ex){
			String mensaje= ex.getCause().getCause().getMessage();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje));
		}
		
	}

}
