package br.com.besche.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "contentor")
@NamedQueries({ @NamedQuery(name = "ContentorEntity.findAll", query = "SELECT p FROM ContentorEntity p") })
public class ContentorEntity {
	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	/*
	 * @OneToMany // um contetor tem muitos documentos; private
	 * List<DocumentoEntity> documentos;
	 */

	public ContentorEntity() {
	}

	public ContentorEntity(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/*
	 * public List<DocumentoEntity> getDocumentos() { return documentos; }
	 * 
	 * public void setDocumentos(List<DocumentoEntity> documentos) {
	 * this.documentos = documentos; }
	 */

}
