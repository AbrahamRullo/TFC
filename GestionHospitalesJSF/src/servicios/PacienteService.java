package servicios;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Paciente;


@Stateless
public class PacienteService {
	@PersistenceContext(unitName = "GestionHospitalesJSF")
	private EntityManager em;

    public PacienteService() {
        // TODO Auto-generated constructor stub
    }
    
    public Paciente crearPaciente(Paciente p)  {
		em.persist(p);
		return p;
		
//		if(a.getNombre().contains("XXX")) {
//			FacesContext context= FacesContext.getCurrentInstance();
//			ResourceBundle archivomensajes = ResourceBundle.getBundle("resources.application",
//					context.getViewRoot().getLocale());
//			
//				String mensaje= archivomensajes.getString("excepciontresequis");
//				TresEquisNombreException ex= new TresEquisNombreException(mensaje);
//				throw ex;
//		}
		
	}

}
