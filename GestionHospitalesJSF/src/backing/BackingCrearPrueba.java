package backing;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entidades.Ingreso;
import entidades.Paciente;
import entidades.Pcr;
import servicios.PacienteService;



@Named
@RequestScoped
public class BackingCrearPrueba implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private PacienteService pacienteservice;
	Paciente paciente = new Paciente();
	Pcr pcr = new Pcr();
	Ingreso ingreso = new Ingreso();
	String dni ="";
	String pIngreso ="";
	
	public BackingCrearPrueba() {
		// TODO Auto-generated constructor stub
	}
		
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public Pcr getPcr() {
		return pcr;
	}

	public void setPcr(Pcr pcr) {
		this.pcr = pcr;
	}
	
	public Ingreso getIngreso() {
		return ingreso;
	}

	public void setIngreso(Ingreso ingreso) {
		this.ingreso = ingreso;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getpIngreso() {
		return pIngreso;
	}

	public void setpIngreso(String pIngreso) {
		this.pIngreso = pIngreso;
	}

	public void nuevoPaciente() {
		try {
			paciente.setDni(dni);
			pacienteservice.crearPaciente(paciente);
			pcr.setPaciente(dni);
			pacienteservice.crearPcr(pcr);
			
			//if(pIngreso == "SI" || pIngreso == "si" || pIngreso == "Si" || pIngreso == "sI") {
				ingreso.setPaciente(dni);
				pacienteservice.crearIngreso(ingreso);
	    	//}
			
			
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
