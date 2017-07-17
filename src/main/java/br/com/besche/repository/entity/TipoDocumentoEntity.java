package br.com.besche.repository.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TipoDocumentoEntity {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String nome;
	private int temporalidade;
	
	//Um tipo de documento tem vários índices
	@OneToMany
	private List<IndiceEntity> indices;


	public TipoDocumentoEntity() {
		
	}
	
	
	public TipoDocumentoEntity(int id, String nome, int temporalidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.temporalidade = temporalidade;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getTemporalidade() {
		return temporalidade;
	}


	public void setTemporalidade(int temporalidade) {
		this.temporalidade = temporalidade;
	}
	
	
	
	
}
