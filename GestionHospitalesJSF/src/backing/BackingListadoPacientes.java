package backing;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;


import entidades.Paciente;
import servicios.PacienteService;
import util.PaginacionHelper;

@Named
@RequestScoped
public class BackingListadoPacientes implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@EJB
	PacienteService pacienteservice;
	private List<Paciente> listadoPacientes;
	private int slctnrpag = 5;
	//private List<Long> autoraborrarMay;
	private boolean edit;
	private PaginacionHelper paginacion;
	
	/*****************************************************************************/
	@PostConstruct
	public void ini() {
		System.out.println("ini");
		if (paginacion == null) {
			paginacion = new PaginacionHelper(5, 0) {
// sobreescritura del metodo abastracto getItemsCount
// para poder utilizarlo desde distintos ejb
				@Override
				public long getItemsCount() {
					return pacienteservice.getTotal();
				}
			};
		}
		listadoPacientes = pacienteservice.pacientesEnRango(paginacion.getPagina() * paginacion.getNrpag(),
				paginacion.getNrpag());
	}
	/******************************************************************************/

	public BackingListadoPacientes() {
		// TODO Auto-generated constructor stub
	}
	public List<Paciente> getListadoPacientes() {
		return listadoPacientes;
	}
	public void setListadoPacientes(List<Paciente> listadoPacientes) {
		this.listadoPacientes = listadoPacientes;
	}
	public int getSlctnrpag() {
		return slctnrpag;
	}
	public void setSlctnrpag(int slctnrpag) {
		this.slctnrpag = slctnrpag;
	}
	public boolean isEdit() {
		return edit;
	}
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	public PaginacionHelper getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(PaginacionHelper paginacion) {
		this.paginacion = paginacion;
	}
	
	/*************************************************************************/
	public int getTotalAutoresCandidatos() {
		return (listadoPacientes != null) ? listadoPacientes.size() : 0;
	}

	/***************************************************************************/
	public long getTotalAutores() {
		System.out.println("total");
		return pacienteservice.getTotal();
	}

	/********************************************************************/
	public void paginaAnterior() {
		System.out.println("anterior");
		paginacion.getPaginaAnterior();
		listadoPacientes = pacienteservice.pacientesEnRango(paginacion.getPagina() * paginacion.getNrpag(),
				paginacion.getNrpag());
	}

	/*********************************************************************/
	public void paginaSiguiente() {
		paginacion.getPaginaSiguiente();
		listadoPacientes = pacienteservice.pacientesEnRango(paginacion.getPagina() * paginacion.getNrpag(),
				paginacion.getNrpag());
	}

		
	public void resetPaginacion() {
		/*
		 * Procedimiento que recalcula el número de página en función de como se sube y
		 * baja el numero de registros por página. A este procedimiento se le llama
		 * mediante peticion ajax asociada al cuadro combinado que permite seleccionar
		 * los registros por pagina. EL valor seleccionado esta asociado a la propiedad
		 * slctnrpag de nuestro backing bean.
		 */
		int nuevapagina = (paginacion.getPrimerElementoPagina() / slctnrpag);
		paginacion.setNrpag(slctnrpag);
		paginacion.setPagina(nuevapagina);
		listadoPacientes = pacienteservice.pacientesEnRango(paginacion.getPagina() * paginacion.getNrpag(),
				paginacion.getNrpag());
	}
	/**************************************************************************/
	
	

}
