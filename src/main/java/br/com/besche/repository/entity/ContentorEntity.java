package br.com.besche.repository.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "contentor")
@NamedQueries({ @NamedQuery(name = "ContentorEntity.findAll", query = "SELECT c FROM ContentorEntity c") })
public class ContentorEntity {
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	@OneToMany(mappedBy = "contentor")
	private List<DocumentoEntity> documentos;

	public ContentorEntity() {
	}

	public ContentorEntity(Long id, String nome, List<DocumentoEntity> documentos) {
		super();
		this.id = id;
		this.nome = nome;
		this.documentos = documentos;
	}

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
	
	public List<DocumentoEntity> getDocumentos() {
		return documentos;
	}
	
	public void setDocumentos(List<DocumentoEntity> documentos) {
		this.documentos = documentos;
	}

}
