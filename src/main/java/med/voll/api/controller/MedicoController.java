package med.voll.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.DadosAtualizacaoMedico;
import med.voll.api.domain.medico.DadosCadastroMedico;
import med.voll.api.domain.medico.DadosDetalhamentoMedico;
import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {
	private MedicoRepository repository;
	public MedicoController(MedicoRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
		Medico medico = new Medico(); 
		medico = repository.save(new Medico(dados));		
		
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listar(
			@PageableDefault(size=10, sort = {"nome"}) Pageable paginacao){
		
		Page<DadosListagemMedico> page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
		return ResponseEntity.ok(page);
		
	}
	
	@PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoMedico dados) {
		Medico medico = new Medico(); 
		medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    	
    }
	
	@DeleteMapping("/{id}")	
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		Medico medico = new Medico(); 
		medico = repository.getReferenceById(id);
		medico.excluir();
		
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		Medico medico = new Medico(); 
		medico = repository.getReferenceById(id);		
		
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
		
	}

}