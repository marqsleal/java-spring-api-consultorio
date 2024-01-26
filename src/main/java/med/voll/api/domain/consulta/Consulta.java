package med.voll.api.domain.consulta;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

@Table(name = "consultas")
@Entity(name = "Consulta")
public class Consulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medico_id")
	private Medico medico;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	private LocalDateTime data;

	@Column(name = "motivo_cancelamento")
	@Enumerated(EnumType.STRING)
	private MotivoCancelamento motivoCancelamento;
	
	public Consulta() {
		
	}
	
	public Consulta(Long id, Medico medico, Paciente paciente, LocalDateTime data,
			MotivoCancelamento motivoCancelamento) {
		super();
		this.id = id;
		this.medico = medico;
		this.paciente = paciente;
		this.data = data;
		this.motivoCancelamento = motivoCancelamento;
	}

	public Long getId() {
		return id;
	}

	public Medico getMedico() {
		return medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void cancelar(MotivoCancelamento motivo) {
	    this.motivoCancelamento = motivo;
	}

	public MotivoCancelamento getMotivoCancelamento() {
		return motivoCancelamento;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Consulta consulta)) return false;
		return Objects.equals(getId(), consulta.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
