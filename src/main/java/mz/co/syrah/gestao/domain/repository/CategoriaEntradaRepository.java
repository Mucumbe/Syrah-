package mz.co.syrah.gestao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mz.co.syrah.gestao.domain.model.CategoriaEntrada;

@Repository
public interface CategoriaEntradaRepository extends JpaRepository<CategoriaEntrada,Long> {

}
