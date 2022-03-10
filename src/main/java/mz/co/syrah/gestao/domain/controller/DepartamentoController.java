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

import mz.co.syrah.gestao.domain.exception.EntidadeEmUsoException;
import mz.co.syrah.gestao.domain.exception.EntidadeNaoEncontradaExcepion;
import mz.co.syrah.gestao.domain.model.Departamento;
import mz.co.syrah.gestao.domain.repository.DepartamentoRepository;
import mz.co.syrah.gestao.domain.service.DepartamentoService;

@RestController
@RequestMapping("/departamntos")
public class DepartamentoController {

	@Autowired
	private DepartamentoRepository repository;

	@Autowired
	private DepartamentoService service;

	@GetMapping
	public List<Departamento> listar() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Departamento> porId(@PathVariable Long id) {
		Optional<Departamento> departaento = repository.findById(id);
		if (departaento.isPresent()) {
			return ResponseEntity.ok(departaento.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Departamento> guardar(@RequestBody Departamento departamento) {

		departamento = repository.save(departamento);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(departamento);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Departamento> editar(@PathVariable Long id, @RequestBody Departamento departamento) {
		Optional<Departamento> departamentoActual = repository.findById(id);

		if (departamentoActual.isPresent()) {
			BeanUtils.copyProperties(departamento, departamentoActual.get(), "id");
			departamento = repository.save(departamentoActual.get());
			return ResponseEntity.ok(departamento);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> apagar(@PathVariable Long id) {

		try {
			service.apagar(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (EntidadeNaoEncontradaExcepion e) {
			return ResponseEntity.notFound().build();
		}

	}
}
