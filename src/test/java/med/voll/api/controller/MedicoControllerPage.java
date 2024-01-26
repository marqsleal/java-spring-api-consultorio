package med.voll.api.controller;

import med.voll.api.domain.endereco.DadosEndereco;

public class MedicoControllerPage {
	
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
