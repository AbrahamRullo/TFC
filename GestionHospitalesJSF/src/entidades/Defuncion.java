package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DEFUNCION database table.
 * 
 */
@Entity
@NamedQuery(name="Defuncion.findAll", query="SELECT d FROM Defuncion d")
public class Defuncion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String paciente;

	@Column(name="DIAS_HOSPITALIZADO")
	private BigDecimal diasHospitalizado;

	//bi-directional one-to-one association to Ingreso
	@OneToOne
	@JoinColumn(name="PACIENTE")
	private Ingreso ingreso;

	public Defuncion() {
	}

	public String getPaciente() {
		return this.paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public BigDecimal getDiasHospitalizado() {
		return this.diasHospitalizado;
	}

	public void setDiasHospitalizado(BigDecimal diasHospitalizado) {
		this.diasHospitalizado = diasHospitalizado;
	}

	public Ingreso getIngreso() {
		return this.ingreso;
	}

	public void setIngreso(Ingreso ingreso) {
		this.ingreso = ingreso;
	}

}