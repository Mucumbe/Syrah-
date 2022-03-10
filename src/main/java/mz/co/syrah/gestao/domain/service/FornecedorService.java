package mz.co.syrah.gestao.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mz.co.syrah.gestao.domain.exception.EntidadeEmUsoException;
import mz.co.syrah.gestao.domain.exception.EntidadeNaoEncontradaExcepion;
import mz.co.syrah.gestao.domain.repository.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository repository;

	public void apagar(Long id) {

		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Fornecedor com id %d não pode ser eliminado porque esta em uso", id));
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExcepion(String.format("Nao Existe Fornecedor com o Id %d", id));
		}
		
		
	}
}
