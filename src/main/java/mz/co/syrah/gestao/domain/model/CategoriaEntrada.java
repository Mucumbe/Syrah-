package mz.co.syrah.gestao.domain.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CategoriaEntrada {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private long id;
	private String nome;
	private int existencia;
	

	
	@ManyToOne
	private Fornecedor fornecedor;
	
	
	@OneToMany(mappedBy = "id.categoriaEntrada")
	private Set<ArtigosRegisto> artigos= new HashSet<>();
	
	public List<Registo> getResgistos(){
		List<Registo> lista= new ArrayList<>();
		for (ArtigosRegisto x: artigos) {
			lista.add(x.getRegisto());
		}
		return lista;
	}

}
