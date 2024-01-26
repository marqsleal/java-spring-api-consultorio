package med.voll.api.domain.consulta.validacoes.agendamento;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta{
	
	public void validar(DadosAgendamentoConsulta dados) {

		var dataConsulta = dados.data();
		var dataAGora = LocalDateTime.now();
		var diferencaEmMinutos = Duration.between(dataAGora, dataConsulta).toMinutes();

		if (diferencaEmMinutos < 30) {
			throw new ValidacaoException("Consulta consulta deve ser agendada com antecedência mínima de 30 minutos");
		}
	}

}
