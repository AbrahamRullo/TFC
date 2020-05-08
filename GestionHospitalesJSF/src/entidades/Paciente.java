package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PACIENTE database table.
 * 
 */
@Entity
@NamedQuery(name="Paciente.findAll", query="SELECT p FROM Paciente p")
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dni;

	@Column(name="COMUNIDAD_AUTONOMA")
	private String comunidadAutonoma;

	private BigDecimal edad;

	private String localidad;

	private String nombre;

	@Column(name="PATALOGIA_PREVIA")
	private String patalogiaPrevia;

	private String provincia;

	private BigDecimal telefono;

	private BigDecimal telefono2;

	//bi-directional one-to-one association to Pcr
	@OneToOne(mappedBy="pacienteBean")
	private Pcr pcr;

	public Paciente() {
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getComunidadAutonoma() {
		return this.comunidadAutonoma;
	}

	public void setComunidadAutonoma(String comunidadAutonoma) {
		this.comunidadAutonoma = comunidadAutonoma;
	}

	public BigDecimal getEdad() {
		return this.edad;
	}

	public void setEdad(BigDecimal edad) {
		this.edad = edad;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPatalogiaPrevia() {
		return this.patalogiaPrevia;
	}

	public void setPatalogiaPrevia(String patalogiaPrevia) {
		this.patalogiaPrevia = patalogiaPrevia;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public BigDecimal getTelefono() {
		return this.telefono;
	}

	public void setTelefono(BigDecimal telefono) {
		this.telefono = telefono;
	}

	public BigDecimal getTelefono2() {
		return this.telefono2;
	}

	public void setTelefono2(BigDecimal telefono2) {
		this.telefono2 = telefono2;
	}

	public Pcr getPcr() {
		return this.pcr;
	}

	public void setPcr(Pcr pcr) {
		this.pcr = pcr;
	}

}