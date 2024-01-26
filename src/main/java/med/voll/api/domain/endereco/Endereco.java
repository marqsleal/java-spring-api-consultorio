package med.voll.api.domain.endereco;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;
    public Endereco(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
    }
    public Endereco() {
    }
	public String getLogradouro() {
		return logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public String getCep() {
		return cep;
	}
	public String getNumero() {
		return numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public String getCidade() {
		return cidade;
	}
	public String getUf() {
		return uf;
	}
	public void atualizarInformacoes(DadosEndereco dados) {
		if (dados.logradouro() != null) {
			this.logradouro = dados.logradouro();
		}
		if (dados.bairro() != null) {
			this.bairro = dados.bairro();
		}
		if (dados.cep() != null) {
			this.cep = dados.cep();
		}
		if (dados.uf() != null) {
			this.uf = dados.uf();
		}
		if (dados.cidade() != null) {
			this.cidade = dados.cidade();
		}
		if (dados.numero() != null) {
			this.numero = dados.numero();
		}
		if (dados.complemento() != null) {
			this.complemento = dados.complemento();
		}

	}
}