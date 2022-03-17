package mz.co.syrah.gestao.domain.repository.export;

import java.io.ByteArrayInputStream;
import java.util.List;
import mz.co.syrah.gestao.domain.model.CategoriaEntrada;
import mz.co.syrah.gestao.domain.model.Departamento;



public interface DepartamentoExport {

	ByteArrayInputStream export(List<Departamento> departamentos);
}
