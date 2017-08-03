/*package br.com.besche.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class IndiceDocumentoEntity {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String indice;
	private String conteudoDoIndice;
	
	@ManyToOne
	private DocumentoEntity documento;

	public IndiceDocumentoEntity() {}

	public IndiceDocumentoEntity(int id, String indice, String conteudoDoIndice) {
		super();
		this.id = id;
		this.indice = indice;
		this.conteudoDoIndice = conteudoDoIndice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIndice() {
		return indice;
	}

	public void setIndice(String indice) {
		this.indice = indice;
	}

	public String getConteudoDoIndice() {
		return conteudoDoIndice;
	}

	public void setConteudoDoIndice(String conteudoDoIndice) {
		this.conteudoDoIndice = conteudoDoIndice;
	}

	public DocumentoEntity getDocumento() {
		return documento;
	}

	public void setDocumento(DocumentoEntity documento) {
		this.documento = documento;
	}
	
}
*/