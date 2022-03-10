package mz.co.syrah.gestao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mz.co.syrah.gestao.domain.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {

}
