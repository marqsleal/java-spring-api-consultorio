package med.voll.api.domain.medico;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class MedicoRepositoryTest {

	private MedicoRepository medicoRepository;

	private TestEntityManager em;

	public MedicoRepositoryTest(MedicoRepository medicoRepository, TestEntityManager em) {
		this.medicoRepository = medicoRepository;
		this.em = em;
	}

	@Test
	@DisplayName("Deveria devolver NULL quando unico medico cadastrado nao esta disponivel na data")
	void escolherMedicoAleatorioLivreNaDataCenario1() {
		var dados = new MedicoRepositoryPage(em);
		var proximaSegundaAs10 = LocalDate.now()
				.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
				.atTime(10, 0);
				var medico = dados.cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
		var paciente = dados.cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");
		dados.cadastrarConsulta(medico, paciente, proximaSegundaAs10);
		
		var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
		
		assertThat(medicoLivre).isNull();		
	}
	
	@Test
	@DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
	void escolherMedicoAleatorioLivreNaDataCenario2() {
		var dados = new MedicoRepositoryPage(em);
		var proximaSegundaAs10 = LocalDate.now()
				.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
				.atTime(10, 0);		
		var medico = dados.cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
		
		var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
				
		assertThat(medicoLivre).isEqualTo(medico);		
	}

}
