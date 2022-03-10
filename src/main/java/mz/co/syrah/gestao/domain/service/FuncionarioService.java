package mz.co.syrah.gestao.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.co.syrah.gestao.domain.exception.EntidadeNaoEncontradaExcepion;
import mz.co.syrah.gestao.domain.model.Departamento;
import mz.co.syrah.gestao.domain.model.Funcionario;
import mz.co.syrah.gestao.domain.repository.DepartamentoRepository;
import mz.co.syrah.gestao.domain.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	private DepartamentoRepository departamentoRepository;

	public Funcionario guardar(Funcionario funcionario) {

		Long departamentoId = funcionario.getDepartamento().getId();

		Departamento departamento = departamentoRepository.findById(departamentoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepion(
						String.format("Não existe departamento Com Id %", departamentoId)));
				funcionario.setDepartamento(departamento);
				funcionario=repository.save(funcionario);
				return funcionario;
	}
}
