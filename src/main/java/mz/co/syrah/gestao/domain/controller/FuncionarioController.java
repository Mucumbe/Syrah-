package mz.co.syrah.gestao.domain.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mz.co.syrah.gestao.domain.exception.EntidadeNaoEncontradaExcepion;
import mz.co.syrah.gestao.domain.model.Funcionario;
import mz.co.syrah.gestao.domain.repository.FuncionarioRepository;
import mz.co.syrah.gestao.domain.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository repository;

	@Autowired
	private FuncionarioService service;

	@GetMapping
	public List<Funcionario> listar() {

		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> porId(@PathVariable Long id) {
		Optional<Funcionario> funcionario = repository.findById(id);

		if (funcionario.isPresent()) {
			return ResponseEntity.ok(funcionario.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Funcionario funcionario) {

		try {
			funcionario = service.guardar(funcionario);
			return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
		} catch (EntidadeNaoEncontradaExcepion e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> Editar(@PathVariable Long id, @RequestBody Funcionario funcionario) {

		Optional<Funcionario> funcionariosalvar = repository.findById(id);
		if (funcionariosalvar.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		BeanUtils.copyProperties(funcionario, funcionariosalvar.get(), "id");

		try {
			funcionario = service.guardar(funcionariosalvar.get());
			return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
		} catch (EntidadeNaoEncontradaExcepion e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Funcionario> apagar(@PathVariable Long id){
		
		Optional<Funcionario> funcionario =repository.findById(id);
		if (funcionario.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
