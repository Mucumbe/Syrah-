package mz.co.syrah.gestao.domain.service;

import java.util.Optional;

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

	private OrcamentoRepository  repository;
	
	private DepartamentoRepository  departamentoRepository;
	
	private FuncionarioRepository funcionarioRepository;
	
	public Orcamento  guardar(Orcamento orcamento) {
	
	try {
		Optional<Departamento> departamento=departamentoRepository.findById(orcamento.getDepartamento().getId());
		Optional<Funcionario> funcionario=funcionarioRepository.findById(orcamento.getId());
		orcamento.setDepartamento(departamento.get());
		orcamento.setFuncionario(funcionario.get());
		orcamento=repository.save(orcamento);
		return orcamento;
	} catch (Exception e) {
		throw new EntidadeNaoEncontradaExcepion(
				String.format("Não existe Funcionario ou Deo Com Id %", departamentoId)
				);
	}
	
	
		
	}
}
