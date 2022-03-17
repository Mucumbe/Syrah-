package mz.co.syrah.gestao.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.co.syrah.gestao.domain.exception.EntidadeNaoEncontradaExcepion;
import mz.co.syrah.gestao.domain.model.Departamento;
import mz.co.syrah.gestao.domain.model.Funcionario;
import mz.co.syrah.gestao.domain.model.Orcamento;
import mz.co.syrah.gestao.domain.repository.DepartamentoRepository;
import mz.co.syrah.gestao.domain.repository.FuncionarioRepository;
import mz.co.syrah.gestao.domain.repository.OrcamentoRepository;

@Service
public class OrcamentoService {

	@Autowired
	private OrcamentoRepository  repository;
	
	@Autowired
	private DepartamentoRepository  departamentoRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public Orcamento  guardar(Orcamento orcamento) {
		
		Double actualizaOrcamento=orcamento.getValor();
		
		Funcionario funcionario=funcionarioRepository.findById(orcamento.getFuncionario().getId()).orElseThrow(
				() -> new EntidadeNaoEncontradaExcepion(
						String.format("Não existe Funcionario Com Id %",orcamento.getFuncionario().getId() )));
		Optional<Departamento> departamento=departamentoRepository.findById(funcionario.getDepartamento().getId());
		
		actualizaOrcamento+=departamento.get().getOrcamento();
		departamento.get().setOrcamento(actualizaOrcamento);
		departamentoRepository.save(departamento.get());
		orcamento.setFuncionario(funcionario);
		repository.save(orcamento);
		
		
		
		return orcamento;
	
	
	
		
	}
}
