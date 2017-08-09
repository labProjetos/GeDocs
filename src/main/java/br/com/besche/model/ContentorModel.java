package br.com.besche.model;

import java.io.Serializable;
import java.util.List;

public class ContentorModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private List<DocumentoModel> documentos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<DocumentoModel> getDocumentos() {
		return documentos;
	}
	
	public void setDocumentos(List<DocumentoModel> documentos) {
		this.documentos = documentos;
	}

}
