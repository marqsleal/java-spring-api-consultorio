package med.voll.api.domain.medico;

import jakarta.persistence.*;
import med.voll.api.domain.endereco.Endereco;

import java.util.Objects;


@Entity
@Table(name="medicos")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private boolean ativo;

	public Medico (DadosCadastroMedico dados) {
    	this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.endereco = new Endereco(dados.endereco());
        this.especialidade = dados.especialidade();
    }
    
    public Medico() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}
	public String getCrm() {
		return crm;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
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
	public void excluir() {
		this.ativo = false;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Medico medico)) return false;
		return Objects.equals(getId(), medico.getId());
	}
	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}