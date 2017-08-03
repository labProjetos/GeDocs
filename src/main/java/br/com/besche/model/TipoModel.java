package br.com.besche.model;

import java.io.Serializable;
import java.util.List;

public class TipoModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private int temporalidade;
	private List<IndiceModel> indices;

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

	public List<IndiceModel> getIndices() {
		return indices;
	}

	public void setIndices(List<IndiceModel> indicesModel) {
		this.indices = indicesModel;
	}
	
}
