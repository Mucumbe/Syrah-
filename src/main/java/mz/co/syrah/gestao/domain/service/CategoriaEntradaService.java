package mz.co.syrah.gestao.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.co.syrah.gestao.domain.exception.EntidadeNaoEncontradaExcepion;
import mz.co.syrah.gestao.domain.model.CategoriaEntrada;
import mz.co.syrah.gestao.domain.model.Fornecedor;
import mz.co.syrah.gestao.domain.repository.CategoriaEntradaRepository;
import mz.co.syrah.gestao.domain.repository.FornecedorRepository;

@Service
public class CategoriaEntradaService {

	@Autowired
	private CategoriaEntradaRepository entradaRepository;
	@Autowired
	private FornecedorRepository fornecedorRepository;

	public CategoriaEntrada guardar(CategoriaEntrada entrada) {

		long fornecedorId = entrada.getFornecedor().getId();

		Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepion(
						String.format("Não Existe Fornecedor com Id %d", fornecedorId)));
		
		entrada.setFornecedor(fornecedor);
		entrada=entradaRepository.save(entrada);
		return entrada;

	}
}
