package backing;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entidades.Paciente;
import servicios.PacienteService;



@Named
@RequestScoped
public class BackingCrearPrueba implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private PacienteService pacienteservice;
	Paciente paciente = new Paciente();
	
	public BackingCrearPrueba() {
		// TODO Auto-generated constructor stub
	}
		
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}



	public void nuevoPaciente() {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
		try {
			pacienteservice.crearPaciente(paciente);
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
		paciente = new Paciente(); // reiniciar el contenedor del nuevo autor si trabajo con SessionScoped o bien
								// para eliminar
// datos del registro recien creado.
	}
	
	

}
