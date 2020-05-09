package entidades;

import java.io.Serializable;
import javax.persistence.*;



@Entity
@NamedQuery(name="Pcr.findAll", query="SELECT p FROM Pcr p")
public class Pcr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String paciente;

	private String positivo;

	//bi-directional one-to-one association to Ingreso
	@OneToOne(mappedBy="pcr")
	private Ingreso ingreso;

	//bi-directional one-to-one association to Paciente
	@OneToOne
	@JoinColumn(name="PACIENTE",insertable=false, updatable=false)
	private Paciente pacienteBean;

	public Pcr() {
	}

	public String getPaciente() {
		return this.paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public String getPositivo() {
		return this.positivo;
	}

	public void setPositivo(String positivo) {
		this.positivo = positivo;
	}

	public Ingreso getIngreso() {
		return this.ingreso;
	}

	public void setIngreso(Ingreso ingreso) {
		this.ingreso = ingreso;
	}

	public Paciente getPacienteBean() {
		return this.pacienteBean;
	}

	public void setPacienteBean(Paciente pacienteBean) {
		this.pacienteBean = pacienteBean;
	}

}