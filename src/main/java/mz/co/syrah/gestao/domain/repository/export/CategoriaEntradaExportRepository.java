package mz.co.syrah.gestao.domain.repository.export;

import java.io.ByteArrayInputStream;
import java.util.List;
import mz.co.syrah.gestao.domain.model.CategoriaEntrada;



public interface CategoriaEntradaExportRepository {

	ByteArrayInputStream export(List<CategoriaEntrada> categoriaEntradas);
}
