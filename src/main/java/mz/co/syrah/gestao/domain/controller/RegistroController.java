package mz.co.syrah.gestao.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mz.co.syrah.gestao.domain.model.Registo;
import mz.co.syrah.gestao.domain.repository.RegistoRepository;

@RestController
@RequestMapping("/registos")
public class RegistroController {

	@Autowired
	private RegistoRepository registoRepository;
	
	@GetMapping
	public List<Registo> lista(){
		
		return registoRepository.findAll();
	}
}
