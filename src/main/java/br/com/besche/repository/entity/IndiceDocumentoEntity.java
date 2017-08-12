package br.com.besche.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "indice_documento")
@NamedQueries({
		@NamedQuery(name = "IndiceDocumentoEntity.findAll", query = "SELECT id FROM IndiceDocumentoEntity id") })
public class IndiceDocumentoEntity {
	@Id
	@GeneratedValue
	private Long id;
	private String conteudo;
	@ManyToOne
	@JoinColumn(name = "documento_id")
	private DocumentoEntity documento;
	@ManyToOne
	@JoinColumn(name = "indice_id")
	private IndiceEntity indice;

	public IndiceDocumentoEntity() {
	}

	public IndiceDocumentoEntity(Long id, String conteudo, DocumentoEntity documento, IndiceEntity indice) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.documento = documento;
		this.indice = indice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public DocumentoEntity getDocumento() {
		return documento;
	}

	public void setDocumentoEntity(DocumentoEntity documento) {
		this.documento = documento;
	}

	public IndiceEntity getIndice() {
		return indice;
	}

	public void setIndiceEntity(IndiceEntity indice) {
		this.indice = indice;
	}

}
