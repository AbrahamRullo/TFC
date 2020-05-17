package servicios;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Ingreso;
import entidades.Paciente;
import entidades.Pcr;
import entidades.Alta;
import entidades.Defuncion;


@Stateless
public class PacienteService {
	@PersistenceContext(unitName = "GestionHospitalesJSF")
	private EntityManager em;

    public PacienteService() {
        // TODO Auto-generated constructor stub
    }
    
    public Paciente crearPaciente(Paciente p)  {
    	Paciente paciente= new Paciente();
    	paciente.setDni(p.getDni().toUpperCase());
    	paciente.setNombre(p.getNombre().toUpperCase());
    	paciente.setEdad(p.getEdad());
    	paciente.setTelefono(p.getTelefono());
    	paciente.setTelefono2(p.getTelefono2());
    	paciente.setLocalidad(p.getLocalidad());
    	paciente.setProvincia(p.getProvincia());
    	paciente.setComunidadAutonoma(p.getComunidadAutonoma());
    	
    	//if(p.getPatalogiaPrevia() == "SI" || p.getPatalogiaPrevia() == "si" || p.getPatalogiaPrevia() == "Si" || p.getPatalogiaPrevia() == "sI" || p.getPatalogiaPrevia() == "NO" || p.getPatalogiaPrevia() == "No" || p.getPatalogiaPrevia() == "nO" || p.getPatalogiaPrevia() == "no") {
    		paciente.setPatalogiaPrevia(p.getPatalogiaPrevia().toUpperCase());
    		em.persist(paciente);
//    	}else {
//    		System.out.println("No has escrito correctamente la respuesta de las patologias");
//    	}
    	
    	
		return paciente;
		
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
    
    public Pcr crearPcr(Pcr p)  {
    	Pcr pcr = new Pcr();
    	String positivo= p.getPositivo();
    	pcr.setPaciente(p.getPaciente());
    	System.out.println(pcr.getPaciente()+" "+ positivo);
    	
    	//if(p.getPositivo() == "SI" || p.getPositivo() == "si" || p.getPositivo() == "Si" || p.getPositivo() == "sI" || p.getPositivo() == "NO" || p.getPositivo() == "No" || p.getPositivo() == "nO" || p.getPositivo() == "no") {

    	if(positivo.compareToIgnoreCase("si")==0 || positivo.compareToIgnoreCase("no")==0 ) {
    		pcr.setPositivo(p.getPositivo().toUpperCase());
    		em.persist(pcr);
    		System.out.println("PCR CREADO");
    	}else {
    		
    		System.out.println("No has escrito correctamente el resultado "+" "+positivo + pcr.getPositivo());
    	}
    	
    	return pcr;
		
	}
    
    public Ingreso crearIngreso(Ingreso i)  {
    	System.out.println("Estoy en crearIngreso");
    	Ingreso ing = new Ingreso();
    	//Date actual= new Date(actual.getTime());

    	Date actual = new Date(Calendar.getInstance().getTime().getTime());
    	System.out.println(actual+" sdasdafdfafafadsd");
    	ing.setPaciente(i.getPaciente());
    	
    	ing.setEstado(i.getEstado().toUpperCase());
    	ing.setFechaIngreso(actual);
    	em.persist(ing);
    	
    	return ing;
		
	}
    
    @SuppressWarnings("unchecked")
	public List<Paciente> pacientesEnRango(int primerResultado, int maxResultados) {
		Query consulta=em.createQuery("select p from Paciente p");
    	consulta.setFirstResult(primerResultado);
    	consulta.setMaxResults(maxResultados);
    	List<Paciente>listaPacientes= consulta.getResultList();
		
		  for(Paciente a:listaPacientes){ em.refresh(a); }
		 
		return listaPacientes;
	}
    
    @SuppressWarnings("unchecked")
	public List<Paciente> pacientesListado() {
		Query consulta=em.createQuery("select p from Paciente p");
    	List<Paciente>listaPacientes= consulta.getResultList();
		
		  for(Paciente a:listaPacientes){ em.refresh(a); }
		 
		return listaPacientes;
	}
	
	public long getTotal() {
		Query consulta = em.createQuery("select count(p) from Paciente p");
		return (Long) consulta.getSingleResult();
	}
	
	public void refrescar(Long paciente) {
		em.refresh(paciente);
	}
    
	@SuppressWarnings("unchecked")
	public List<Paciente> pacientesPorNombre(String paciente) {
		Query consulta = em.createNamedQuery("Paciente.pacientesPorNombre");
		List<Paciente> listaPacientes = consulta.setParameter("nombrepaciente", "%" + paciente + "%").getResultList();
		for (Paciente s : listaPacientes) {
			em.refresh(s);
		}
		return listaPacientes;
	}
	
	public Alta crearAlta(String a)  {
    	Alta alta= new Alta();
    	Ingreso i =em.find(Ingreso.class, a);
    	System.out.println(i.getPaciente());
    	System.out.println(i.getEstado());
    	
    		Date fechaInicial= (Date) i.getFechaIngreso();
    		Date actual = new Date(Calendar.getInstance().getTime().getTime());
     
    		Long dias=(Long) ((actual.getTime()-fechaInicial.getTime())/86400000);
     
    	
    	
    	if(i!=null) {
    		alta.setPaciente(a);
    		alta.setIngreso(i);
    		alta.setDiasHospitalizado(dias+1);
    		em.persist(alta);
    	}
    	
    	
    	
		return alta;
		
		
	}

}
