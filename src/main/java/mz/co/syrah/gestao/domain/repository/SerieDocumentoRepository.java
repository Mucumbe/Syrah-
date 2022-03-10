package mz.co.syrah.gestao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mz.co.syrah.gestao.domain.model.SerieDocumento;


@Repository
public interface SerieDocumentoRepository  extends JpaRepository<SerieDocumento, Long>{

}
