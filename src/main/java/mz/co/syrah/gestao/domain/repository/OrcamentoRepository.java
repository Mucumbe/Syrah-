package mz.co.syrah.gestao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mz.co.syrah.gestao.domain.model.Orcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento,Long> {

}
