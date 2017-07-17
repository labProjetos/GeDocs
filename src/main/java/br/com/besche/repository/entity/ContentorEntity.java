package br.com.besche.repository.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ContentorEntity {

	@Id
	@GeneratedValue
	private int id;
	
	private String nome;
		
	//um contetor terá muitos documentos;
	@OneToMany
	private List<DocumentoEntity > documentos;
	
	
	public ContentorEntity() {
		
	}

	public ContentorEntity(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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

	public List<DocumentoEntity> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DocumentoEntity> documentos) {
		this.documentos = documentos;
	}
	
	
	

}
