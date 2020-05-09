package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ALTA database table.
 * 
 */
@Entity
@NamedQuery(name="Alta.findAll", query="SELECT a FROM Alta a")
public class Alta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String paciente;

	@Column(name="DIAS_HOSPITALIZADO")
	private BigDecimal diasHospitalizado;

	//bi-directional one-to-one association to Ingreso
	@OneToOne
	@JoinColumn(name="PACIENTE", insertable=false, updatable=false)
	private Ingreso ingreso;

	public Alta() {
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