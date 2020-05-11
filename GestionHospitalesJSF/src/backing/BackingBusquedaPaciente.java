package backing;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import entidades.Paciente;
import servicios.PacienteService;

@Named
@ViewScoped
public class BackingBusquedaPaciente  implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@EJB
	private PacienteService pacienteService;
	private String inicialesBuscarPaciente;
// recogera el resultado de las consultas y hace de
// backing para el objeto h:datatable
	private List<Paciente> listadoPacientes;
	
	public BackingBusquedaPaciente() {
		// TODO Auto-generated constructor stub
	}

	public String getInicialesBuscarPaciente() {
		return inicialesBuscarPaciente;
	}

	public void setInicialesBuscarPaciente(String inicialesBuscarPaciente) {
		this.inicialesBuscarPaciente = inicialesBuscarPaciente;
	}

	public List<Paciente> getListadoPacientes() {
		return listadoPacientes;
	}

	public void setListadoPacientes(List<Paciente> listadoPacientes) {
		this.listadoPacientes = listadoPacientes;
	}
	
	public void pacientesPorNombreAjax() {
		listadoPacientes = pacienteService.pacientesPorNombre(inicialesBuscarPaciente);
	}

	public int getTotalPacientesCandidatos() {
		return (listadoPacientes != null) ? listadoPacientes.size() : 0;
	}


}
