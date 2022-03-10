package mz.co.syrah.gestao.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class SerieDocumento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private long id;
	private String documento;
	private String descricao;
	private int ultimoNumero;
	private boolean movimentaSaida;
	private boolean movimentaEntrada;
	private boolean debito;
	private boolean credito;
}
