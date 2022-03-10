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
import mz.co.syrah.gestao.domain.model.SerieDocumento;
import mz.co.syrah.gestao.domain.repository.SerieDocumentoRepository;
import mz.co.syrah.gestao.domain.service.SerieDocumentosService;

@RestController
@RequestMapping("/seriedocumentos")
public class SerieDocumentosController {

	@Autowired
	private SerieDocumentoRepository repository;
	
	@Autowired
	private SerieDocumentosService service; 

	@GetMapping
	public List<SerieDocumento> listar() {

		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<SerieDocumento> porId(@PathVariable Long id) {
		Optional<SerieDocumento> serieDocumento = repository.findById(id);
		if (serieDocumento.isPresent()) {
			return ResponseEntity.ok(serieDocumento.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<SerieDocumento> guardar(@RequestBody SerieDocumento serieDocumento) {

		return ResponseEntity.status(HttpStatus.CREATED).body(serieDocumento);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SerieDocumento> editar(@RequestBody SerieDocumento serieDocumento,@PathVariable Long id){
		Optional<SerieDocumento> serieDocumentoctual= repository.findById(id);
		
		if (serieDocumentoctual.isPresent()) {
			
			BeanUtils.copyProperties(serieDocumento, serieDocumentoctual.get(),"id");
			serieDocumento=repository.save(serieDocumentoctual.get());
			return ResponseEntity.ok(serieDocumento);
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
