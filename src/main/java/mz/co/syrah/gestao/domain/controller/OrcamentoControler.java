package mz.co.syrah.gestao.domain.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mz.co.syrah.gestao.domain.exception.EntidadeNaoEncontradaExcepion;
import mz.co.syrah.gestao.domain.model.Orcamento;
import mz.co.syrah.gestao.domain.repository.OrcamentoRepository;
import mz.co.syrah.gestao.domain.service.OrcamentoService;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoControler {

	@Autowired
	OrcamentoRepository repository;
	
	@Autowired
	OrcamentoService service;

	@GetMapping
	public List<Orcamento> listar() {

		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Orcamento> porId(@PathVariable Long id){
		Optional<Orcamento> orcamento=repository.findById(id);
		if (orcamento.isPresent()) {
			return ResponseEntity.ok(orcamento.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Orcamento orcamento){
		
		try {
			orcamento= service.guardar(orcamento);
			return  ResponseEntity.status(HttpStatus.CREATED).body(orcamento);
		} catch (EntidadeNaoEncontradaExcepion e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
