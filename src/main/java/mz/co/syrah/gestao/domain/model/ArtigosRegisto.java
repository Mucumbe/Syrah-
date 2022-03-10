package mz.co.syrah.gestao.domain.model;


import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ArtigosRegisto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@EqualsAndHashCode.Include
	@EmbeddedId
	private ArtigosRegistoPK id= new ArtigosRegistoPK();
	
	private String descricao;
	private int quantidade;
	private double valor;

	public ArtigosRegisto() {
		
	}
	
	
	public ArtigosRegisto( int quantidade, double valor) {
		super();
		
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public Registo getRegisto() {
		return id.getRegisto();
	}
	
	public CategoriaEntrada getCategoriaEntrada() {
		return id.getCategoriaEntrada();
	}

	
	
}
