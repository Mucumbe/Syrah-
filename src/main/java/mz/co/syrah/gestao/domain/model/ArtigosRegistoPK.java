package mz.co.syrah.gestao.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Embeddable
public class ArtigosRegistoPK  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@ManyToOne
	private CategoriaEntrada categoriaEntrada;
	
	
	@ManyToOne
	private Registo registo;

}
