package mz.co.syrah.gestao.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Registo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private long id;
	private Date data;
	private int numeroDocumento;
	
	
	@ManyToOne
	private Funcionario funcionario;

	@ManyToOne
	private SerieDocumento serieDocumento;
	
	@OneToMany(mappedBy = "id.registo")
	private Set<ArtigosRegisto> artigos= new HashSet<>();
}
