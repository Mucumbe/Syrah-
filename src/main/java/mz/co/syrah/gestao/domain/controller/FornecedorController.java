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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mz.co.syrah.gestao.domain.exception.EntidadeEmUsoException;
import mz.co.syrah.gestao.domain.exception.EntidadeNaoEncontradaExcepion;
import mz.co.syrah.gestao.domain.model.Fornecedor;
import mz.co.syrah.gestao.domain.repository.FornecedorRepository;
import mz.co.syrah.gestao.domain.service.FornecedorService;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

	@Autowired
	private FornecedorRepository repository;
	
	@Autowired
	private FornecedorService service; 

	@GetMapping
	public List<Fornecedor> listar() {

		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Fornecedor> porId(@PathVariable Long id) {
		Optional<Fornecedor> fornecedor = repository.findById(id);
		if (fornecedor.isPresent()) {
			return ResponseEntity.ok(fornecedor.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Fornecedor> guardar(@RequestBody Fornecedor fornecedor) {
		fornecedor=repository.save(fornecedor);
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedor);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Fornecedor> editar(@RequestBody Fornecedor fornecedor,@PathVariable Long id){
		Optional<Fornecedor> fornecedoractual= repository.findById(id);
		
		if (fornecedoractual.isPresent()) {
			
			BeanUtils.copyProperties(fornecedor, fornecedoractual.get(),"id");
			fornecedor=repository.save(fornecedoractual.get());
			return ResponseEntity.ok(fornecedor);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> apagar(@PathVariable Long id){
		
		try {
			service.apagar(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}catch (EntidadeNaoEncontradaExcepion e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
