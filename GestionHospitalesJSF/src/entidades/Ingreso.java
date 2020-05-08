package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the INGRESO database table.
 * 
 */
@Entity
@NamedQuery(name="Ingreso.findAll", query="SELECT i FROM Ingreso i")
public class Ingreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String paciente;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_INGRESO")
	private Date fechaIngreso;

	//bi-directional one-to-one association to Alta
	@OneToOne(mappedBy="ingreso")
	private Alta alta;

	//bi-directional one-to-one association to Defuncion
	@OneToOne(mappedBy="ingreso")
	private Defuncion defuncion;

	//bi-directional one-to-one association to Pcr
	@OneToOne
	@JoinColumn(name="PACIENTE")
	private Pcr pcr;

	public Ingreso() {
	}

	public String getPaciente() {
		return this.paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Alta getAlta() {
		return this.alta;
	}

	public void setAlta(Alta alta) {
		this.alta = alta;
	}

	public Defuncion getDefuncion() {
		return this.defuncion;
	}

	public void setDefuncion(Defuncion defuncion) {
		this.defuncion = defuncion;
	}

	public Pcr getPcr() {
		return this.pcr;
	}

	public void setPcr(Pcr pcr) {
		this.pcr = pcr;
	}

}