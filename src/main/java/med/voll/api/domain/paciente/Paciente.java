package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import med.voll.api.domain.endereco.Endereco;

import java.util.Objects;

@Entity(name = "Paciente")
@Table(name = "pacientes")
public class Paciente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    @Embedded
    private Endereco endereco;
	private boolean ativo;
    public Paciente(DadosCadastroPaciente dados) {
    	this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }
    public Paciente() {
    }

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}
	public String getCpf() {
		return cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
		if (dados.nome() != null) {
			this.nome = dados.nome();
		}
		if (dados.telefone() != null) {
			this.telefone = dados.telefone();
		}
		if (dados.endereco() != null) {
			this.endereco.atualizarInformacoes(dados.endereco());
		}
		
	}
	public boolean isAtivo() {
		return ativo;
	}

	public void excluir() {
		this.ativo = false;
		
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Paciente paciente)) return false;
		return Objects.equals(getId(), paciente.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
