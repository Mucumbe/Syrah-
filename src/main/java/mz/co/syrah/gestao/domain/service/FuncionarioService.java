package mz.co.syrah.gestao.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mz.co.syrah.gestao.domain.exception.EntidadeEmUsoException;
import mz.co.syrah.gestao.domain.exception.EntidadeNaoEncontradaExcepion;
import mz.co.syrah.gestao.domain.model.Departamento;
import mz.co.syrah.gestao.domain.model.Funcionario;
import mz.co.syrah.gestao.domain.repository.DepartamentoRepository;
import mz.co.syrah.gestao.domain.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	public Funcionario guardar(Funcionario funcionario) {

		Long departamentoId = funcionario.getDepartamento().getId();
		System.err.println(departamentoId);
		
		Departamento departamento = departamentoRepository.findById(departamentoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepion(
						String.format("Não existe departamento Com Id %", departamentoId)));
				funcionario.setDepartamento(departamento);
				funcionario=repository.save(funcionario);
				return funcionario;
	}
	
	public void apagar(Long id) {

		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Funcionario com id %d não pode ser eliminado porque esta em uso", id));
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExcepion(String.format("Nao Existe Funcionario com o Id %d", id));
		}
		
		
	}
}
