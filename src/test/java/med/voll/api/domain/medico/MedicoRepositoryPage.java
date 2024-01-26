package med.voll.api.domain.medico;

import java.time.LocalDateTime;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.Paciente;

public class MedicoRepositoryPage {
	
	private TestEntityManager em;
	
	public MedicoRepositoryPage(TestEntityManager em) {
		this.em = em;
	}
	
	 void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
	    em.persist(new Consulta(null, medico, paciente, data, null));
	}

	Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
	    var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
	    em.persist(medico);
	    return medico;
	}

	Paciente cadastrarPaciente(String nome, String email, String cpf) {
	    var paciente = new Paciente(dadosPaciente(nome, email, cpf));
	    em.persist(paciente);
	    return paciente;
	}

	private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
	    return new DadosCadastroMedico(
	            nome,
	            email,
	            "61999999999",
	            crm,
	            especialidade,
	            dadosEndereco()
	    );
	}

	DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
	    return new DadosCadastroPaciente(
	            nome,
	            email,
	            "61999999999",
	            cpf,
	            dadosEndereco()
	    );
	}

	DadosEndereco dadosEndereco() {
	    return new DadosEndereco(
	            "rua xpto",
	            "bairro",
	            "00000000",
	            "Brasilia",
	            "DF",
	            null,
	            null
	    );
	}

}
