package mz.co.syrah.gestao.domain.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import mz.co.syrah.gestao.domain.model.CategoriaEntrada;
import mz.co.syrah.gestao.domain.repository.CategoriaEntradaRepository;
import mz.co.syrah.gestao.domain.service.CategoriaEntradaService;
import mz.co.syrah.gestao.domain.service.export.CategoriaEntradaExportService;

@RestController
@RequestMapping("/categoriasentradas")
public class CategoriaEntradaController {

	@Autowired
	private CategoriaEntradaRepository repository;

	@Autowired
	private CategoriaEntradaService service;
	
	@Autowired
	private CategoriaEntradaExportService categoriaEntradaExportService;

	@GetMapping
	public List<CategoriaEntrada> listar() {

		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaEntrada> porId(@PathVariable Long id) {

		Optional<CategoriaEntrada> categoriaEntrada = repository.findById(id);
		if (categoriaEntrada.isPresent()) {

			return ResponseEntity.ok(categoriaEntrada.get());
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody CategoriaEntrada entrada) {

		try {
			entrada = service.guardar(entrada);
			return ResponseEntity.status(HttpStatus.CREATED).body(entrada);
		} catch (EntidadeNaoEncontradaExcepion e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody CategoriaEntrada entrada) {

		Optional<CategoriaEntrada> entradaActual = repository.findById(id);

		if (entradaActual.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		try {
			BeanUtils.copyProperties(entrada, entradaActual.get(), "id");
			entrada = service.guardar(entradaActual.get());
			return ResponseEntity.ok(entrada);

		} catch (EntidadeNaoEncontradaExcepion e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CategoriaEntrada> apagar(@PathVariable Long id) {

		Optional<CategoriaEntrada> entrada = repository.findById(id);
		if (entrada.isPresent()) {
			try {
				repository.deleteById(id);
				return ResponseEntity.noContent().build();
			} catch (DataIntegrityViolationException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
			
		}

		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
       
		
		List<CategoriaEntrada> categoriaEntradas = repository.findAll();
        ByteArrayInputStream byteArrayInputStream = categoriaEntradaExportService.export(categoriaEntradas);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=inventario.xlsx");
        IOUtils.copy(byteArrayInputStream, response.getOutputStream());
		
		
	  
    }  

}
