package mz.co.syrah.gestao.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import mz.co.syrah.gestao.domain.exception.EntidadeEmUsoException;
import mz.co.syrah.gestao.domain.exception.EntidadeNaoEncontradaExcepion;
import mz.co.syrah.gestao.domain.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository repository;

	public void apagar(Long id) {

		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Departamento com Id %d nao pode ser eliminado Porque esta em uso", id));
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExcepion(String.format("Nao existe departamento com ID %d",id));
		}

	}
}
